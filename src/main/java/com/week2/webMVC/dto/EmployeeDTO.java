package com.week2.webMVC.dto;

import com.week2.webMVC.annotation.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 10, message = "Number of characters should be in range : [3,10]")
    private String name;

    @Email(message = "Not a valid email ")
    private String email;

    @Max(value = 80, message = "Age cannot be greater than 80")
    @Min(value = 18, message = "Age cannot be less than 18")
    private Integer age;

    @NotBlank(message = "Role of an employee cannot be blank")
//    @Pattern(regexp = "^(ADMIN|USER)$", message = "Role of Employee can either be USER or ADMIN")
//    custom annotation - to validate the role should be either ADMIN or USER
    @EmployeeRoleValidation
    private String role;

    @NotNull
    @Positive(message = "salary of employee should be positive")
    @Digits(integer = 6, fraction = 2)
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "50000.00")
    private Integer salary;

    private LocalDate dateOfJoining;
    private Boolean isActive;
}
