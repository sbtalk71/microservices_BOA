package com.demo.spring.repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.demo.spring.entity.Emp;

public interface EmpRepository extends JpaRepository<Emp, Integer> {

	
	@Modifying
	@Transactional
	@Query("UPDATE Emp e set e.salary=:salary where e.empId=:empId")
	public int updateSalary(Integer empId, double salary);

	@Query("select e from Emp e where e.deptNo=:dno")
	public List<Emp> findEmpsInDept(int dno);

}
