package com.mj.employee.example.api.employee;

import com.mj.employee.example.model.Name;
import com.mj.employee.example.model.command.AddEmployeeCommand;
import com.mj.employee.example.model.command.GetEmployeeCommand;
import com.mj.employee.example.model.command.UpdateEmployeeCommand;
import com.mj.employee.example.model.dto.EmployeeDTO;
import com.mj.employee.example.model.dto.EmployeesDTO;
import com.mj.employee.example.model.dto.NewEmployeeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/employees")
public interface EmployeeRest extends EmployeeRestValidation {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Override
    EmployeesDTO getEmployees(GetEmployeeCommand command);

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    ResponseEntity<EmployeeDTO> getEmployee(
            @PathVariable("id") String id
    );

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    ResponseEntity<NewEmployeeDTO> addEmployee(
            @RequestBody AddEmployeeCommand command
    );

    @PutMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    ResponseEntity<NewEmployeeDTO> updateEmployee(
            @PathVariable("id") String id,
            @RequestBody UpdateEmployeeCommand command
    );

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    ResponseEntity<Void> deleteEmployees(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) Name name,
            @RequestParam(required = false) String email,
            @RequestParam(name = "birth-date", required = false) LocalDate birthDate,
            @RequestParam(required = false) String speciality,
            @RequestParam(required = false) String nationality,
            @RequestParam(required = false) Double salary,
            @RequestParam(name = "date-from", required = false) LocalDate dateFrom,
            @RequestParam(name = "date-to", required = false) LocalDate dateTo
    );

    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    ResponseEntity<Void> deleteEmployee(
            @PathVariable("id") String id
    );
}
