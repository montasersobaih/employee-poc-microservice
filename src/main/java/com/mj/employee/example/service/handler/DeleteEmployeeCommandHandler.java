package com.mj.employee.example.service.handler;

import com.mj.core.springboot.command.CommandHandler;
import com.mj.core.springboot.model.event.EventHeader;
import com.mj.core.springboot.service.event.EventSender;
import com.mj.employee.example.enumeration.EmployeeEventAction;
import com.mj.employee.example.model.command.DeleteEmployeeCommand;
import com.mj.employee.example.model.event.EmployeeEvent;
import com.mj.employee.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteEmployeeCommandHandler implements CommandHandler<DeleteEmployeeCommand, Boolean> {

    private final EmployeeRepository repository;

    private final EventSender eventSender;

    @Autowired
    public DeleteEmployeeCommandHandler(EmployeeRepository repository, EventSender eventSender) {
        this.repository = repository;
        this.eventSender = eventSender;
    }

    @Override
    public Boolean handle(DeleteEmployeeCommand command) {
//        QueryParam param = ConverterUtils.convert(command, QueryParamImpl.class);
        boolean isSucceeded = repository.deleteAll(command);

        if (isSucceeded) {
            EventHeader header = new EventHeader("employee-example-ms", EmployeeEventAction.DELETE_EMPLOYEES);
            eventSender.send(new EmployeeEvent(header, null));
        }

        return isSucceeded;
    }
}
