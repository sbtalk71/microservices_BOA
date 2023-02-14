package com.demo.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("hr")
public class HrRestController {

	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping(path="emp_details",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getEmpDetails(@RequestParam(name="empId",required = true) int id) {
		
		HttpHeaders headers= new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		HttpEntity<Void> reqData=new HttpEntity<>(headers);
		
		ResponseEntity response=restTemplate.exchange("http://localhost:8081/emp/find/"+id, HttpMethod.GET, reqData, String.class);
		return response;
	}
}
