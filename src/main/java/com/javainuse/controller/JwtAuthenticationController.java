package com.javainuse.controller;

import com.javainuse.models.*;
import com.javainuse.repositories.ProfileBbRepo;
import com.javainuse.repositories.ProfileHosRepo;
import com.javainuse.repositories.ProfileIndRepo;
import com.javainuse.requests.*;
import com.javainuse.responses.AuthResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.javainuse.service.JwtUserDetailsService;


import com.javainuse.config.JwtTokenUtil;

import java.util.Date;

@RestController
@CrossOrigin
//This controller is for generating the JWT token for the user and also for registering the user
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private ProfileIndRepo profileIndRepo;

	@Autowired
	private ProfileHosRepo profileHosRepo;

	@Autowired
	private ProfileBbRepo profileBbRepo;

	////////////////////////////////////////////////////////////////////////////////////////////////

	@ApiOperation(value ="Individual registers")
	@RequestMapping(value = "/registerind", method = RequestMethod.POST)
	public ResponseEntity<?> saveUserInd(@RequestBody ProfileIndDTO user) throws Exception {
		System.out.println(user);
		return userDetailsService.saveInd(user);
	}
	@ApiOperation(value ="Hospital registers")
	@RequestMapping(value = "/registerhos", method = RequestMethod.POST)
	public ResponseEntity<?> saveUserHos(@RequestBody ProfileHosDTO user) throws Exception {
		return userDetailsService.saveHos(user);
	}
	@ApiOperation(value ="Blood Banks registers")
	@RequestMapping(value = "/registerbb", method = RequestMethod.POST)
	public ResponseEntity<?> saveUserBb(@RequestBody ProfileBbDTO user) throws Exception {
		return userDetailsService.saveBb(user);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getEmail());


		//////////////////////////////////////////////////ROOKIE CODE, TO BE TAKEN CARE OF////////////////////////////////////////////////////////////////////////


		ProfileInd profileInd = profileIndRepo.findByEmail(authenticationRequest.getEmail());

		ProfileHos profileHos = profileHosRepo.findByEmail(authenticationRequest.getEmail());

		ProfileBb profileBb = profileBbRepo.findByEmail(authenticationRequest.getEmail());


		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("success", "true");

		if(profileInd != null){
			final String token = jwtTokenUtil.generateUserToken(profileInd.getUserId(), profileInd.getEmail(), 1);
			final Date authTokenExpiry = jwtTokenUtil.getExpirationDateFromToken(token);
			final String refreshToken = jwtTokenUtil.generateRefreshToken(profileInd.getUserId(), profileInd.getEmail(), 1);
			final Date refreshTokenExpiry = jwtTokenUtil.getExpirationDateFromToken(token);

			return ResponseEntity.ok().headers(responseHeaders).body(new AuthResponse(token, profileInd.getUserId(), 1, authTokenExpiry, refreshToken, refreshTokenExpiry));
		}
		else if(profileHos != null){
			final String token = jwtTokenUtil.generateUserToken(profileHos.getUserId(), profileHos.getEmail(), 2);
			final Date authTokenExpiry = jwtTokenUtil.getExpirationDateFromToken(token);
			final String refreshToken = jwtTokenUtil.generateRefreshToken(profileHos.getUserId(), profileHos.getEmail(), 1);
			final Date refreshTokenExpiry = jwtTokenUtil.getExpirationDateFromToken(token);

			return ResponseEntity.ok().headers(responseHeaders).body(new AuthResponse(token, profileHos.getUserId(), 2, authTokenExpiry, refreshToken, refreshTokenExpiry));
		}
		else {
			final String token = jwtTokenUtil.generateUserToken(profileBb.getUserId(), profileBb.getEmail(), 3);
			final Date authTokenExpiry = jwtTokenUtil.getExpirationDateFromToken(token);
			final String refreshToken = jwtTokenUtil.generateRefreshToken(profileBb.getUserId(), profileBb.getEmail(), 1);
			final Date refreshTokenExpiry = jwtTokenUtil.getExpirationDateFromToken(token);

			return ResponseEntity.ok().headers(responseHeaders).body(new AuthResponse(token, profileBb.getUserId(), 3, authTokenExpiry, refreshToken, refreshTokenExpiry));
		}


		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	}

	@PostMapping("/refreshauth")
	private ResponseEntity<?> refreshAuthentication(@RequestBody RefreshAuthTokenReqBody refreshAuthTokenReqBody){
		return userDetailsService.refreshAuthentication(refreshAuthTokenReqBody.getExpiredAuthToken(), refreshAuthTokenReqBody.getRefreshToken());
	}

	private void authenticate(String email, String password) throws Exception {
		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}