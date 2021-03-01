package com.mj.employee.example.model.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mj.core.springboot.command.Command;
import com.mj.core.springboot.model.QueryParam;
import com.mj.employee.example.model.schema.EmployeeSchema;

public class UpdateEmployeeCommand extends EmployeeSchema implements Command, QueryParam {

    @JsonIgnore
    @Override
    public String getId() {
        return super.getId();
    }
}
