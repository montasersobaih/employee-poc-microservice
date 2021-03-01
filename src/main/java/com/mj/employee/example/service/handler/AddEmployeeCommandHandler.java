package com.mj.employee.example.service.handler;

import com.mj.core.springboot.command.CommandHandler;
import com.mj.core.springboot.model.event.EventHeader;
import com.mj.core.springboot.service.event.EventSender;
import com.mj.core.springboot.utils.ConverterUtils;
import com.mj.employee.example.enumeration.EmployeeEventAction;
import com.mj.employee.example.model.command.AddEmployeeCommand;
import com.mj.employee.example.model.entity.EmployeeEntity;
import com.mj.employee.example.model.event.EmployeeEvent;
import com.mj.employee.example.model.response.NewEmployeeResponse;
import com.mj.employee.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class AddEmployeeCommandHandler implements CommandHandler<AddEmployeeCommand, NewEmployeeResponse> {

    private final EmployeeRepository repository;

    private final EventSender event;

    @Autowired
    public AddEmployeeCommandHandler(EmployeeRepository repository, EventSender event) {
        this.repository = repository;
        this.event = event;
    }

    @Override
    public NewEmployeeResponse handle(AddEmployeeCommand command) {
        EmployeeEntity entity = ConverterUtils.convert(command, EmployeeEntity.class);
        entity.setCreatedBy(getClass().getSimpleName());
        entity.setCreationDate(LocalDateTime.now());

        Optional<EmployeeEntity> opSave = repository.save(entity);

        if (opSave.isPresent()) {
            EventHeader header = new EventHeader("employee-example-ms", EmployeeEventAction.ADD_NEW_EMPLOYEE);
            event.send(new EmployeeEvent(header, opSave.get()));
        }

        return opSave.map(EmployeeEntity::getId).map(NewEmployeeResponse::new).orElse(null);
    }
}
