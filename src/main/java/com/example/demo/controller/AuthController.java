package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.responseStructure.ResponseStructure;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private com.example.demo.jwtConfig.JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<Employee>> register(@RequestBody Employee employee) {
        ResponseStructure<Employee> response = new ResponseStructure<>();
        try {
            Employee saved = employeeService.saveEmployee(employee);
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("Employee registered successfully");
            response.setData(saved);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("An unexpected error occurred");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseStructure<String>> login(@RequestBody Employee employee) {
        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(employee.getUsername(), employee.getPassword())
            );

            String role = auth.getAuthorities().stream()
                .findFirst()
                .map(a -> a.getAuthority())
                .orElse("ROLE_USER");

            String jwtToken = jwtService.generateToken(employee.getUsername(), role);

            ResponseStructure<String> response = new ResponseStructure<>();
            response.setMessage("Login successful");
            response.setStatus(HttpStatus.OK.value());
            response.setData(jwtToken);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            ResponseStructure<String> error = new ResponseStructure<>();
            error.setMessage("Invalid username or password");
            error.setStatus(HttpStatus.UNAUTHORIZED.value());
            error.setData(null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }
}
