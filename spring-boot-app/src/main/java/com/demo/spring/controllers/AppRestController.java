package com.demo.spring.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppRestController {

	// @RequestMapping(path="/greet",method = RequestMethod.GET,produces =
	// MediaType.TEXT_PLAIN_VALUE)
	@GetMapping(path = "/greet", produces = MediaType.TEXT_PLAIN_VALUE)
	public String greeter() {
		return "Hi from REST Service";
	}
}
