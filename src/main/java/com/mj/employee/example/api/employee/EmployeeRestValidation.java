package com.mj.employee.example.api.employee;

import com.mj.employee.example.model.Name;
import com.mj.employee.example.model.command.AddEmployeeCommand;
import com.mj.employee.example.model.command.GetEmployeeCommand;
import com.mj.employee.example.model.command.UpdateEmployeeCommand;
import com.mj.employee.example.model.dto.EmployeeDTO;
import com.mj.employee.example.model.dto.EmployeesDTO;
import com.mj.employee.example.model.dto.NewEmployeeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Validated
public interface EmployeeRestValidation {

    EmployeesDTO getEmployees(GetEmployeeCommand command);

    ResponseEntity<EmployeeDTO> getEmployee(
            String id
    );

    ResponseEntity<NewEmployeeDTO> addEmployee(
            @NotNull(message = "Employee object must be not null")
            @Valid AddEmployeeCommand command
    );

    ResponseEntity<NewEmployeeDTO> updateEmployee(
            String id,
            @NotNull(message = "Employee object must be not null")
            @Valid UpdateEmployeeCommand command
    );

    ResponseEntity<Void> deleteEmployees(
            String id,
            Name name,
            String email,
            LocalDate birthDate,
            String speciality,
            String nationality,
            Double salary,
            LocalDate dateFrom,
            LocalDate dateTo
    );

    ResponseEntity<Void> deleteEmployee(
            String id
    );
}
