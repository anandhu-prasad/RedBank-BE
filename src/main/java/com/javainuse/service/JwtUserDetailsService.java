package com.javainuse.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.javainuse.config.JwtTokenUtil;
import com.javainuse.models.*;
import com.javainuse.repositories.*;
import com.javainuse.requests.ProfileBbDTO;
import com.javainuse.requests.ProfileHosDTO;
import com.javainuse.requests.ProfileIndDTO;
import com.javainuse.requests.RefreshAuthTokenRespBody;
import com.javainuse.responses.AuthResponse;
import com.javainuse.responses.SuccessResponseBody;
import io.jsonwebtoken.Claims;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//import com.javainuse.repositories.UserDao;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private ProfileIndRepo profileIndRepo;

	@Autowired
	private ProfileHosRepo profileHosRepo;

	@Autowired
	private ProfileBbRepo profileBbRepo;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private InventoryHosRepo inventoryHosRepo;

	@Autowired
	private InventoryBbRepo inventoryBbRepo;

//	@Autowired
//	private UserDao userDao;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	List<String> bloodGroups = new ArrayList<>(Arrays.asList("B+", "A+", "O+", "AB+", "B-", "A-", "O-", "AB-"));
	List<String> components = new ArrayList<>(Arrays.asList("Blood", "Plasma","Platelets"));

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


		ProfileInd profileInd = profileIndRepo.findByEmail(username);

		ProfileHos profileHos = profileHosRepo.findByEmail(username);

		ProfileBb profileBb = profileBbRepo.findByEmail(username);


		if (profileInd == null && profileHos == null && profileBb == null ) {
			throw new UsernameNotFoundException("No account exists with " + username);
		}

		if(profileInd != null){
			return new org.springframework.security.core.userdetails.User(profileInd.getEmail(), profileInd.getPassword(),
					new ArrayList<>());
		}
		else if(profileHos != null){
			return new org.springframework.security.core.userdetails.User(profileHos.getEmail(), profileHos.getPassword(),
					new ArrayList<>());
		}
		else{
			return new org.springframework.security.core.userdetails.User(profileBb.getEmail(), profileBb.getPassword(),
					new ArrayList<>());
		}

	}
	
	public ResponseEntity<?> saveInd(ProfileIndDTO user) throws Exception{

		//? CHECK IF THE NEWLY REGISTERED USER IS USING A PREVIOUSLY REGISTERED PHONE(S) OR EMAIL.


		//TODO TO BE CHANGED TO USER SPECIFIC ENTITIES.
		ProfileInd profileInd = profileIndRepo.findByEmail(user.getEmail());
		ProfileHos profileHos = profileHosRepo.findByEmail(user.getEmail());
		ProfileBb profileBb = profileBbRepo.findByEmail(user.getEmail());

		if(profileInd != null || profileHos != null || profileBb != null){
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("error", "Email is already taken");
			return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(false));
		}

		else{
			Date dob = new SimpleDateFormat("dd/MM/yy").parse(user.getDob());

			ProfileInd newIndUser = new ProfileInd(user.getName(), user.getBloodGroup(), user.getEmail(), dob, user.getPhone(), user.getAddress(), user.getState(), user.getDistrict(), user.getPincode(), new Timestamp(System.currentTimeMillis()),
					bcryptEncoder.encode(user.getPassword()));
        	ProfileInd newProfileInd = profileIndRepo.save(newIndUser);

			//? GENERATE TOKEN HERE

			final String token = jwtTokenUtil.generateUserToken(newProfileInd.getUserId(), newProfileInd.getEmail(), 1);
			final Date authTokenExpiry = jwtTokenUtil.getExpirationDateFromToken(token);
			final String refreshToken = jwtTokenUtil.generateRefreshToken(newProfileInd.getUserId(), newProfileInd.getEmail(), 1);
			final Date refreshTokenExpiry = jwtTokenUtil.getExpirationDateFromToken(token);

			AuthResponse authResponse = new AuthResponse(token,  newProfileInd.getUserId(), 1, authTokenExpiry, refreshToken, refreshTokenExpiry);
        	HttpHeaders responseHeaders = new HttpHeaders();
        	responseHeaders.set("success", "true");
        	return ResponseEntity.ok().headers(responseHeaders).body(authResponse);

		}
	}



	public ResponseEntity<?> saveHos(ProfileHosDTO user) throws Exception{

		//? CHECK IF THE NEWLY REGISTERED USER IS USING A PREVIOUSLY REGISTERED PHONE(S) OR EMAIL.


		//TODO TO BE CHANGED TO USER SPECIFIC ENTITIES.
		ProfileInd profileInd = profileIndRepo.findByEmail(user.getEmail());
		ProfileHos profileHos = profileHosRepo.findByEmail(user.getEmail());
		ProfileBb profileBb = profileBbRepo.findByEmail(user.getEmail());

		if(profileInd != null || profileHos != null || profileBb != null){

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("error", "Email is already taken");
			return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(false));

		}
		else{

			ProfileHos newHosUser = new ProfileHos(user.getName(), user.getEmail(), user.getLicenseNumber(), user.getAddress(), user.getState(), user.getDistrict(), user.getPincode(), bcryptEncoder.encode(user.getPassword()), new Timestamp(System.currentTimeMillis()));

			//? NOW TO SET THE PHONE NUMBERS.

			if(user.getPhone().size() >= 1){
				newHosUser.setPhone1(user.getPhone().get(0));
			}
			if(user.getPhone().size() >= 2){
				newHosUser.setPhone2(user.getPhone().get(1));
			}
			if(user.getPhone().size() >= 3){
				newHosUser.setPhone3(user.getPhone().get(2));
			}
			if(user.getPhone().size() >= 4){
				newHosUser.setPhone4(user.getPhone().get(3));
			}
			if(user.getPhone().size() >= 5){
				newHosUser.setPhone5(user.getPhone().get(4));
			}

			ProfileHos newProfileHos = profileHosRepo.save(newHosUser);

			//? GENERATE TOKEN HERE

			final String token = jwtTokenUtil.generateUserToken(newProfileHos.getUserId(), newProfileHos.getEmail(), 2);
			final Date authTokenExpiry = jwtTokenUtil.getExpirationDateFromToken(token);
			final String refreshToken = jwtTokenUtil.generateRefreshToken(newProfileHos.getUserId(), newProfileHos.getEmail(), 1);
			final Date refreshTokenExpiry = jwtTokenUtil.getExpirationDateFromToken(token);

			//? INITIALIZE THE INVENTORY.

			for (String component : components) {
				inventoryHosRepo.save(new InventoryHos(newProfileHos.getUserId(), component));
			}

			//? SETTING THE HEADERS AND RESPONSE BODY.

			AuthResponse authResponse = new AuthResponse(token,  newProfileHos.getUserId(), 2, authTokenExpiry, refreshToken, refreshTokenExpiry);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("success", "true");

			return ResponseEntity.ok().headers(responseHeaders).body(authResponse);

		}
	}


	public ResponseEntity<?> saveBb(ProfileBbDTO user) throws Exception{

		//? CHECK IF THE NEWLY REGISTERED USER IS USING A PREVIOUSLY REGISTERED PHONE(S) OR EMAIL.


		//TODO TO BE CHANGED TO USER SPECIFIC ENTITIES.
		ProfileInd profileInd = profileIndRepo.findByEmail(user.getEmail());
		ProfileHos profileHos = profileHosRepo.findByEmail(user.getEmail());
		ProfileBb profileBb = profileBbRepo.findByEmail(user.getEmail());

		if(profileInd != null || profileHos != null || profileBb != null){

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("error", "Email is already taken");
			return ResponseEntity.ok().headers(responseHeaders).body(new SuccessResponseBody(false));

		}

		else{

			ProfileBb newBbUser = new ProfileBb(user.getName(), user.getEmail(), user.getLicenseNumber(), user.getAddress(), user.getState(), user.getDistrict(), user.getPincode(), bcryptEncoder.encode(user.getPassword()), new Timestamp(System.currentTimeMillis()));

			//? NOW TO SET THE PHONE NUMBERS.

			if(user.getPhone().size() >= 1){
				newBbUser.setPhone1(user.getPhone().get(0));
			}
			if(user.getPhone().size() >= 2){
				newBbUser.setPhone2(user.getPhone().get(1));
			}
			if(user.getPhone().size() >= 3){
				newBbUser.setPhone3(user.getPhone().get(2));
			}
			if(user.getPhone().size() >= 4){
				newBbUser.setPhone4(user.getPhone().get(3));
			}
			if(user.getPhone().size() >= 5){
				newBbUser.setPhone5(user.getPhone().get(4));
			}

			ProfileBb newProfileBb = profileBbRepo.save(newBbUser);

			//? GENERATE TOKEN HERE

			final String token = jwtTokenUtil.generateUserToken(newProfileBb.getUserId(), newProfileBb.getEmail(), 3);
			final Date authTokenExpiry = jwtTokenUtil.getExpirationDateFromToken(token);
			final String refreshToken = jwtTokenUtil.generateRefreshToken(newProfileBb.getUserId(), newProfileBb.getEmail(), 1);
			final Date refreshTokenExpiry = jwtTokenUtil.getExpirationDateFromToken(token);

			//? INITIALIZE THE INVENTORY.
			for (String component : components) {
				inventoryBbRepo.save(new InventoryBb(newProfileBb.getUserId(), component));
			}

			//? SETTING THE HEADERS AND RESPONSE BODY.

			AuthResponse authResponse = new AuthResponse(token,  newProfileBb.getUserId(), 3, authTokenExpiry, refreshToken, refreshTokenExpiry);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("success", "true");

			return ResponseEntity.ok().headers(responseHeaders).body(authResponse);

		}
	}

	public ResponseEntity<?> refreshAuthentication(String expiredAuthToken, String refreshToken){
		HttpHeaders responseHeaders = new HttpHeaders();

		//? USE THE EXPIRED AUTH TOKEN IF NEEDED.

		try{
			//VALIDATING REFRESH TOKEN

			if(jwtTokenUtil.validateRefreshToken(refreshToken.substring(7))){

				Claims claims = jwtTokenUtil.getAllClaimsFromToken(refreshToken.substring(7));
//				String userId = claims.get("userId").toString();
//				int userType = Integer.parseInt(claims.get("userType").toString());
//				String userEmail = jwtTokenUtil.getUsernameFromToken(refreshToken.substring(7));
//

				String newAuthToken = jwtTokenUtil.generateUserToken("IND18", "anandhup28@gmail.com", 1);
				System.out.println(newAuthToken);

				responseHeaders.set("success", "true");
				return ResponseEntity.ok().headers(responseHeaders).body(new RefreshAuthTokenRespBody(newAuthToken, jwtTokenUtil.getExpirationDateFromToken(newAuthToken)));
			}
			else{
				responseHeaders.set("error", "Bad request");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(responseHeaders).build();
			}
		}
		catch (Exception e){
			responseHeaders.set("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(responseHeaders).build();
		}
	}

}