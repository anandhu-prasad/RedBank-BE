package com.javainuse.controller;

import com.javainuse.models.*;
import com.javainuse.repositories.ProfileBbRepo;
import com.javainuse.repositories.ProfileHosRepo;
import com.javainuse.repositories.ProfileIndRepo;
import com.javainuse.requests.JwtRequest;
import com.javainuse.requests.ProfileBbDTO;
import com.javainuse.requests.ProfileHosDTO;
import com.javainuse.requests.ProfileIndDTO;
import com.javainuse.responses.AuthResponse;
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

@RestController
@CrossOrigin
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

	@RequestMapping(value = "/registerind", method = RequestMethod.POST)
	public ResponseEntity<AuthResponse> saveUserInd(@RequestBody ProfileIndDTO user) throws Exception {
		return userDetailsService.saveInd(user);
	}

	@RequestMapping(value = "/registerhos", method = RequestMethod.POST)
	public ResponseEntity<AuthResponse> saveUserHos(@RequestBody ProfileHosDTO user) throws Exception {
		return userDetailsService.saveHos(user);
	}

	@RequestMapping(value = "/registerbb", method = RequestMethod.POST)
	public ResponseEntity<AuthResponse> saveUserBb(@RequestBody ProfileBbDTO user) throws Exception {
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
			return ResponseEntity.ok().headers(responseHeaders).body(new AuthResponse(token, profileInd.getUserId(), 1));
		}
		else if(profileHos != null){
			final String token = jwtTokenUtil.generateUserToken(profileHos.getUserId(), profileHos.getEmail(), 2);
			return ResponseEntity.ok().headers(responseHeaders).body(new AuthResponse(token, profileHos.getUserId(), 2));
		}
		else{
			final String token = jwtTokenUtil.generateUserToken(profileBb.getUserId(), profileBb.getEmail(), 3);
			return ResponseEntity.ok().headers(responseHeaders).body(new AuthResponse(token, profileBb.getUserId(), 3));
		}


		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//		final String token = jwtTokenUtil.generateToken(userDetails);


//		final String token = jwtTokenUtil.generateUserToken();

//		return ResponseEntity.ok(new JwtResponse(token));
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