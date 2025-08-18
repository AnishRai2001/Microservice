package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	  @Autowired
	    private PasswordEncoder passwordEncoder;

	@Override

	public Employee saveEmployee(Employee employee) {
		
		
		if(employeeRepository.existsByEmail(employee.getEmail())) {
			throw new IllegalArgumentException("email already exist");
		}
	  
		if(employeeRepository.existsByUsername(employee.getUsername())) {
			throw new IllegalArgumentException("Username already exist");
		}
		

	    // Encrypt password
	    employee.setPassword(passwordEncoder.encode(employee.getPassword()));

	    // Set default role if not provided
	    if (employee.getRole() == null || employee.getRole().isEmpty()) {
	        employee.setRole("ROLE_USER");
	    }

	    return employeeRepository.save(employee);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public Optional<Employee> findEmployeeById(int id) {
		// TODO Auto-generated method stub
		return employeeRepository.findById(id);
	}

	@Override
	public Employee UpdateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		Optional<Employee>existingEmployee=employeeRepository.findById(employee.getId());
		if(existingEmployee.isPresent()) {
			return employeeRepository.save(employee);
		}
		else {
			 throw new RuntimeException("Employee not found with ID: " + employee.getId());
		}
	}

	@Override
	public void deleteEmployee(int id) {
	    if (!employeeRepository.existsById(id)) {
	        throw new IllegalArgumentException("Employee with ID " + id + " does not exist");
	    }
	    employeeRepository.deleteById(id);
	}


	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}
	
}