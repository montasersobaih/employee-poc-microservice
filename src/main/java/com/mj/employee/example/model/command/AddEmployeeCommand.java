package com.mj.employee.example.model.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mj.core.springboot.command.Command;
import com.mj.employee.example.model.schema.EmployeeSchema;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
//@AllArgsConstructor
public class AddEmployeeCommand extends EmployeeSchema implements Command {

    @JsonIgnore
    @Override
    public String getId() {
        return super.getId();
    }

    @JsonIgnore
    @Override
    public String getCreatedBy() {
        return super.getCreatedBy();
    }

    @JsonIgnore
    @Override
    public LocalDateTime getCreationDate() {
        return super.getCreationDate();
    }

    @JsonIgnore
    @Override
    public String getLastUpdatedBy() {
        return super.getLastUpdatedBy();
    }

    @JsonIgnore
    @Override
    public LocalDateTime getLastUpdatedDate() {
        return super.getLastUpdatedDate();
    }

    @JsonIgnore
    @Override
    public boolean isActive() {
        return super.isActive();
    }
}
