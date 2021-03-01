package com.mj.employee.example.model.event;

import com.mj.core.springboot.model.event.Event;
import com.mj.core.springboot.model.event.EventHeader;
import com.mj.employee.example.model.schema.EmployeeSchema;

/**
 * @Project employee-example-ms
 * @Author Montaser Sobaih
 * @Date 29-09-2020
 */

public class EmployeeEvent extends Event<EmployeeSchema> {

    public EmployeeEvent(EventHeader header, EmployeeSchema body) {
        super(header, body);
    }
}
