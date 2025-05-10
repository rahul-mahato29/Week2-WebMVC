package com.week2.webMVC.controllers;

import com.week2.webMVC.dto.EmployeeDTO;
import com.week2.webMVC.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getMessage(@PathVariable Long employeeId){
        return new EmployeeDTO(employeeId, "Rahul Mahato", "rahul@gmail.com", 23, "ADMIN", 100000, LocalDate.of(2023, 7, 6),true);
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

//    Repository-Layer : not recommended to use "Repository" directly inside the controller
//    @PostMapping(path = "/{createEmp}")
//    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO newEmployee){
//        return employeeRepository.save(newEmployee);
//    }
//
//    @GetMapping(path = "/name/{empName}")
//    public List<EmployeeDTO> getEntity(@PathVariable(name = "empName") String name){
//        return employeeRepository.findByName(name);
//    }
//
//    @GetMapping(path = "allEmployee")
//    public List<EmployeeDTO> getAllEmployee(){
//        return employeeRepository.findAll();
//    }

    //service-layer
    @PostMapping(path = "/{createEmp}")
    public EmployeeDTO createNewEmployee(@RequestBody @Valid EmployeeDTO newEmployee){
        return employeeService.save(newEmployee);
    }

    @GetMapping(path = "/name/{empName}")
    public ResponseEntity<List<EmployeeDTO>> getEntity(@PathVariable(name = "empName") String name){
        return ResponseEntity.ok(employeeService.findByName(name));
    }

    @GetMapping(path = "allEmployee")
    public List<EmployeeDTO> getAllEmployee(){
        return employeeService.findAll();
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO empDetails, @PathVariable(name = "employeeId") Long Id){
        return ResponseEntity.ok(employeeService.updateEmployeeById(Id, empDetails));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable(name = "employeeId") Long Id){
        boolean gotDeleted = employeeService.deleteEmployeeById(Id);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates, @PathVariable(name = "employeeId") Long Id){
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(Id, updates);
        if(employeeDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }

    //Exception-Handling
    @GetMapping(path = "/ById/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long Id){
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(Id);
        return ResponseEntity.ok(employeeDTO);
    }

}
