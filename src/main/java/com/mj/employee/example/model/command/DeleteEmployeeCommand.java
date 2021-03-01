package com.mj.employee.example.model.command;

import com.mj.core.springboot.command.Command;
import com.mj.core.springboot.model.QueryParam;
import com.mj.employee.example.model.Name;
import com.mj.employee.example.model.schema.EmployeeSchema;

import java.time.LocalDate;

public class DeleteEmployeeCommand extends EmployeeSchema implements Command, QueryParam {

    public DeleteEmployeeCommand(String id) {
        this.setId(id);
    }

    public DeleteEmployeeCommand(String id, Name name, String email, LocalDate birthDate, String speciality, String nationality, double salary) {
        super(id, name, email, birthDate, speciality, nationality, salary);
    }
}
