package com.week2.webMVC.controllers;

import com.week2.webMVC.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import javax.xml.xpath.XPath;
import java.time.LocalDate;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getMessage(@PathVariable Long employeeId){
        return new EmployeeDTO(employeeId, "Rahul Mahato", "rahul@gmail.com", 23, LocalDate.of(2023, 7, 6),true);
    }

    @GetMapping
    public String getMessage2(@RequestParam(required = false) Integer age){
        return "Hello, my age is : " + age;
    }

    @PostMapping
    public String getMessage3(@RequestBody String message){
        return message;
    }

    @PostMapping(path = "/newEmp/{employeeId}")
    public EmployeeDTO getEmployee(@RequestBody EmployeeDTO newEmployee, @PathVariable Long employeeId){
        newEmployee.setId(employeeId);
        return newEmployee;
    }
}
