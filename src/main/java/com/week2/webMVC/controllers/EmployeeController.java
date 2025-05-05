package com.week2.webMVC.controllers;

import com.week2.webMVC.dto.EmployeeDTO;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class EmployeeController {

    @GetMapping(path = "/employee/{employeeId}")
    public EmployeeDTO getMessage(@PathVariable Long employeeId){
        return new EmployeeDTO(employeeId, "Rahul Mahato", "rahul@gmail.com", 23, LocalDate.of(2023, 7, 6),true);
    }
}
