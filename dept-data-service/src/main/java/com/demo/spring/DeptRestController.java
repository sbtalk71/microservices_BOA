package com.demo.spring;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.entity.Dept;
import com.demo.spring.entity.DeptRepository;

@RestController
@RequestMapping("dept")
public class DeptRestController {

	@Autowired
	DeptRepository deptRepository;

	@GetMapping(path="find/{dno}",produces = "application/json")
	public ResponseEntity findDept(@PathVariable("dno") int id) {
		
		Optional<Dept> deptOp=deptRepository.findById(id);
		if(deptOp.isPresent()) {
			return ResponseEntity.ok(deptOp.get());
		}else {
			return ResponseEntity.ok("Dept not found");
		}
	}
}
