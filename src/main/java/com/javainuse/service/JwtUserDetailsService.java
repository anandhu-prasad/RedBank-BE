package com.javainuse.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.javainuse.config.JwtTokenUtil;
import com.javainuse.model.*;
import com.javainuse.repositories.*;
import com.javainuse.responses.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
	private ProfileIndRepository profileIndRepository;

	@Autowired
	private ProfileHosRepository profileHosRepository;

	@Autowired
	private ProfileBbRepository profileBbRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private InventoryHosRepository inventoryHosRepository;

	@Autowired
	private InventoryBbRepository inventoryBbRepository;

//	@Autowired
//	private UserDao userDao;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	List<String> bloodGroups = new ArrayList<>(Arrays.asList("B+", "A+", "O+", "AB+", "B-", "A-", "O-", "AB-"));
	List<String> components = new ArrayList<>(Arrays.asList("Blood", "Plasma","Platelets"));

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


		ProfileInd profileInd = profileIndRepository.findByEmail(username);

		ProfileHos profileHos = profileHosRepository.findByEmail(username);

		ProfileBb profileBb = profileBbRepository.findByEmail(username);


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
	
	public ResponseEntity<AuthResponse> saveInd(ProfileIndDTO user) throws Exception{

		//? CHECK IF THE NEWLY REGISTERED USER IS USING A PREVIOUSLY REGISTERED PHONE(S) OR EMAIL.


		//TODO TO BE CHANGED TO USER SPECIFIC ENTITIES.
		ProfileInd profileInd = profileIndRepository.findByEmail(user.getEmail());
		ProfileHos profileHos = profileHosRepository.findByEmail(user.getEmail());
		ProfileBb profileBb = profileBbRepository.findByEmail(user.getEmail());

		if(profileInd != null || profileHos != null || profileBb != null){

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("error", "Email is already taken");
			return ResponseEntity.notFound().headers(responseHeaders).build();

		}

		else{
			Date dob = new SimpleDateFormat("yyyy/MM/dd").parse(user.getDob());
			System.out.println("DOB: " + dob);

			ProfileInd newIndUser = new ProfileInd(user.getName(), user.getBloodGroup(), user.getEmail(), dob, user.getPhone(), user.getAddress(), user.getState(), user.getDistrict(), user.getPincode(), new Timestamp(System.currentTimeMillis()), bcryptEncoder.encode(user.getPassword()));
        	ProfileInd newProfileInd = profileIndRepository.save(newIndUser);

			//? GENERATE TOKEN HERE

			final String token = jwtTokenUtil.generateUserToken(newProfileInd.getUserId(), newProfileInd.getEmail(), 1);


        	AuthResponse authResponse = new AuthResponse(token,  newProfileInd.getUserId(), 1);
        	HttpHeaders responseHeaders = new HttpHeaders();
        	responseHeaders.set("success", "true");

        	return ResponseEntity.ok().headers(responseHeaders).body(authResponse);

		}
	}



	public ResponseEntity<AuthResponse> saveHos(ProfileHosDTO user) throws Exception{

		//? CHECK IF THE NEWLY REGISTERED USER IS USING A PREVIOUSLY REGISTERED PHONE(S) OR EMAIL.


		//TODO TO BE CHANGED TO USER SPECIFIC ENTITIES.
		ProfileInd profileInd = profileIndRepository.findByEmail(user.getEmail());
		ProfileHos profileHos = profileHosRepository.findByEmail(user.getEmail());
		ProfileBb profileBb = profileBbRepository.findByEmail(user.getEmail());

		if(profileInd != null || profileHos != null || profileBb != null){

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("error", "Email is already taken");
			return ResponseEntity.notFound().headers(responseHeaders).build();

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

			ProfileHos newProfileHos = profileHosRepository.save(newHosUser);

			//? GENERATE TOKEN HERE

			final String token = jwtTokenUtil.generateUserToken(newProfileHos.getUserId(), newProfileHos.getEmail(), 2);

			//? INITIALIZE THE INVENTORY.

			for (String component : components) {
				inventoryHosRepository.save(new InventoryHos(newProfileHos.getUserId(), component));
			}

			//? SETTING THE HEADERS AND RESPONSE BODY.

			AuthResponse authResponse = new AuthResponse(token,  newProfileHos.getUserId(), 2);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("success", "true");

			return ResponseEntity.ok().headers(responseHeaders).body(authResponse);

		}
	}


	public ResponseEntity<AuthResponse> saveBb(ProfileBbDTO user) throws Exception{

		//? CHECK IF THE NEWLY REGISTERED USER IS USING A PREVIOUSLY REGISTERED PHONE(S) OR EMAIL.


		//TODO TO BE CHANGED TO USER SPECIFIC ENTITIES.
		ProfileInd profileInd = profileIndRepository.findByEmail(user.getEmail());
		ProfileHos profileHos = profileHosRepository.findByEmail(user.getEmail());
		ProfileBb profileBb = profileBbRepository.findByEmail(user.getEmail());

		if(profileInd != null || profileHos != null || profileBb != null){

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("error", "Email is already taken");
			return ResponseEntity.notFound().headers(responseHeaders).build();

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

			ProfileBb newProfileBb = profileBbRepository.save(newBbUser);

			//? GENERATE TOKEN HERE

			final String token = jwtTokenUtil.generateUserToken(newProfileBb.getUserId(), newProfileBb.getEmail(), 3);

			//? INITIALIZE THE INVENTORY.
			for (String component : components) {
				inventoryBbRepository.save(new InventoryBb(newProfileBb.getUserId(), component));
			}

			//? SETTING THE HEADERS AND RESPONSE BODY.

			AuthResponse authResponse = new AuthResponse(token,  newProfileBb.getUserId(), 3);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("success", "true");

			return ResponseEntity.ok().headers(responseHeaders).body(authResponse);

		}
	}

}