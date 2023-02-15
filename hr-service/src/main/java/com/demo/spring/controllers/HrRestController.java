package com.demo.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.demo.spring.entity.Dept;
import com.demo.spring.entity.Emp;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

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
		
		ResponseEntity<String> response=restTemplate.exchange("http://emp-service/emp/find/"+id, HttpMethod.GET, reqData, String.class);
		return response;
	}
	
	@GetMapping(path="emplist",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity empList() {
		
		HttpHeaders headers= new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		HttpEntity<Void> reqData=new HttpEntity<>(headers);
		
		ResponseEntity<List<Emp>> response=restTemplate.exchange("http://emp-service/emp/", HttpMethod.GET, reqData,
				new ParameterizedTypeReference<List<Emp>>() {});
		return response;
	}
	
	@GetMapping(path="{dno}/emplist",produces = MediaType.APPLICATION_JSON_VALUE)
	@CircuitBreaker(name = "appBackend",fallbackMethod = "fallbackFindEmpInDept")
	public ResponseEntity findEmpInDept(@PathVariable("dno") int deptNo) {
		System.out.println("inside the method findEmpInDept");
		ResponseEntity<Dept> deptResponse=restTemplate.exchange("http://dept-service/dept/find/"+deptNo, HttpMethod.GET, null, Dept.class);
		Dept dept=deptResponse.getBody();
		
		ResponseEntity<List<Emp>> empResponse=restTemplate.exchange("http://emp-service/emp/list/"+deptNo,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Emp>>() {});
		
		dept.setEmpList(empResponse.getBody());
		
		return ResponseEntity.ok(dept);
		
	}
	
	public ResponseEntity fallbackFindEmpInDept(int deptNo,Exception ex) {
		return ResponseEntity.ok("Service is down, please try after some time..");
	}
}
