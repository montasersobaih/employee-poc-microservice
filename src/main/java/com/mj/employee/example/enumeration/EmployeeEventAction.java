package com.mj.employee.example.enumeration;

import com.mj.core.springboot.model.event.EventAction;

/**
 * @Project employee-example-ms
 * @Author Montaser Sobaih
 * @Date 29-09-2020
 */

public enum EmployeeEventAction implements EventAction<String> {

    ADD_NEW_EMPLOYEE,
    UPDATE_EXIST_EMPLOYEE,
    DELETE_EMPLOYEES,
    DELETE_EMPLOYEE;

    @Override
    public String getAction() {
        return this.name();
    }
}
