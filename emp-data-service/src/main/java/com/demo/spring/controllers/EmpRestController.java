package com.demo.spring.controllers;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	@PostMapping(path="save",produces = MediaType.TEXT_PLAIN_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createEmp(@RequestBody Emp e) {
		
		if(empRepository.existsById(e.getEmpId())) {
			return ResponseEntity.ok("Emp Already exists");
		}else {
			empRepository.save(e);
			return ResponseEntity.ok("Emp Saved..");
		}
	}
	
	@PutMapping(path="update",produces = MediaType.TEXT_PLAIN_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateEmp(@RequestBody Emp e) {
		
		if(empRepository.existsById(e.getEmpId())) {
			empRepository.save(e);
			return ResponseEntity.ok("Emp updated");
		}else {
			
			return ResponseEntity.ok("Emp not found..");
		}
	}
	
	//delete to Do..
	
	@PatchMapping(path="updatesal/{id}/{amount}")
	public ResponseEntity<String> updateSalary(@PathVariable("id") Integer empId,@PathVariable("amount")double salary){
		
		if(empRepository.existsById(empId)) {
			empRepository.updateSalary(empId,salary);
			return ResponseEntity.ok("Emp Salary updated");
		}else {
			
			return ResponseEntity.ok("Emp not found..");
		}
	}
	
}
