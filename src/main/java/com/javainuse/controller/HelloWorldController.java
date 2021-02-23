package com.javainuse.controller;

import com.javainuse.config.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@RequestMapping({ "/hello" })
//1. do this
	public String firstPage(@RequestHeader ("Authorization") String userToken) {


		Claims claims = jwtTokenUtil.getAllClaimsFromToken(userToken.substring(7));
		String userIdFromToken = claims.get("userId").toString();
		Integer userTypeFromToken = Integer.parseInt(claims.get("userType").toString());

		System.out.println(userIdFromToken);

		return "Hello World";
	}

}