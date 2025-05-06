package com.week2.webMVC.controllers;

import com.week2.webMVC.dto.EmployeeDTO;
import com.week2.webMVC.entities.EmployeeEntity;
import com.week2.webMVC.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import javax.xml.xpath.XPath;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

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

    //Repository-Layer
    @PostMapping(path = "/{createEmp}")
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity newEmployee){
        return employeeRepository.save(newEmployee);
    }

    @GetMapping(path = "/name/{empName}")
    public List<EmployeeEntity> getEntity(@PathVariable(name = "empName") String name){
        return employeeRepository.findByName(name);
    }

    @GetMapping(path = "allEmployee")
    public List<EmployeeEntity> getAllEmployee(){
        return employeeRepository.findAll();
    }
}
