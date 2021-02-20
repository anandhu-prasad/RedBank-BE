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
	public String firstPage() {

		return "Hello World";
	}

}