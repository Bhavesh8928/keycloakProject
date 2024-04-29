//package com.key.controller;
//
//import com.key.repository.EmployeeRepository;
//import org.springframework.beans.factory.annotation.*;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//
//@RestController
//@RequestMapping("/employees")
//public class EmployeeController {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @GetMapping("/home")
//    public String home() {
//        return "Welcome to Employee Management System";
//    }
//
//    @GetMapping
//    public ArrayList<String> getEmployees() {
//        ArrayList<String> employees = new ArrayList<>();
//        employees.add("John");
//        employees.add("Doe");
//        employees.add("Smith");
//        return employees;
//    }
//
//
//
//}
