package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

//    Optional<Employee> findByEmail(String email); // returns Optional<Employee>

	  boolean existsByEmail(String email);

	    boolean existsByUsername(String username);
}
