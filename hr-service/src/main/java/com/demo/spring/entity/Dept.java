package com.demo.spring.entity;

import java.util.ArrayList;
import java.util.List;

public class Dept {
	
	private Integer deptNo;
	private String deptName;
	private String manager;
	
	private List<Emp> empList=new ArrayList<>();

	public List<Emp> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Emp> empList) {
		this.empList = empList;
	}

	public Dept() {
		// TODO Auto-generated constructor stub
	}

	public Dept(Integer deptNo, String deptName, String manager) {
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.manager = manager;
	}

	public Integer getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(Integer deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

}
