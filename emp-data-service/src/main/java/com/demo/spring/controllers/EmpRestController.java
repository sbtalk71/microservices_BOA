package com.demo.spring.controllers;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.entity.Emp;
import com.demo.spring.repo.EmpRepository;

@RestController
@RequestMapping("emp")
public class EmpRestController {
	@Autowired
	private EmpRepository empRepository;

	@GetMapping(path="find/{empId}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public Emp findOneEmp(@PathVariable("empId") int id) {
		Optional<Emp> empOp=empRepository.findById(id);
		if(empOp.isPresent()) {
			return empOp.get();
		}else {
			throw new RuntimeException("Emp Not Found");
		}
	}
	
	@GetMapping(path="/",produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Emp> findAllEmps(){
		return empRepository.findAll();
	}
	
	
}
