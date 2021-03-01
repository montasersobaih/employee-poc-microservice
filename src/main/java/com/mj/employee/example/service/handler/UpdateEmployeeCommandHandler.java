package com.mj.employee.example.service.handler;

import com.mj.core.springboot.command.CommandHandler;
import com.mj.core.springboot.model.event.EventHeader;
import com.mj.core.springboot.service.event.EventSender;
import com.mj.core.springboot.utils.ConverterUtils;
import com.mj.core.springboot.utils.ObjectUtil;
import com.mj.employee.example.enumeration.EmployeeEventAction;
import com.mj.employee.example.model.command.UpdateEmployeeCommand;
import com.mj.employee.example.model.entity.EmployeeEntity;
import com.mj.employee.example.model.event.EmployeeEvent;
import com.mj.employee.example.model.response.UpdateEmployeeResponse;
import com.mj.employee.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class UpdateEmployeeCommandHandler implements CommandHandler<UpdateEmployeeCommand, UpdateEmployeeResponse> {

    private final EmployeeRepository repository;

    private final EventSender eventSender;

    @Autowired
    public UpdateEmployeeCommandHandler(EmployeeRepository repository, EventSender eventSender) {
        this.repository = repository;
        this.eventSender = eventSender;
    }

    @Override
    public UpdateEmployeeResponse handle(UpdateEmployeeCommand command) {
        Optional<EmployeeEntity> opEmp = repository.find(command);

        EmployeeEventAction eventAction;
        EmployeeEntity entity;
        if (opEmp.isPresent()) {
            entity = opEmp.get();

            String empId = entity.getId();
            ObjectUtil.copy(command, entity, false);
            entity.setId(empId);

            entity.setLastUpdatedBy(getClass().getSimpleName());
            entity.setLastUpdatedDate(LocalDateTime.now());

            eventAction = EmployeeEventAction.UPDATE_EXIST_EMPLOYEE;
        } else {
            entity = ConverterUtils.convert(command, EmployeeEntity.class);
            entity.setCreatedBy(getClass().getSimpleName());
            entity.setCreationDate(LocalDateTime.now());

            eventAction = EmployeeEventAction.ADD_NEW_EMPLOYEE;
        }

        Optional<EmployeeEntity> opResult = repository.save(entity);
        String empId = opResult.map(EmployeeEntity::getId).orElse(null);
//        String profileId = opResult.map(GeneralAggregation::getProfileId).orElse(null);

        if (opEmp.isPresent()) {
            EventHeader header = new EventHeader("employee-example-ms", eventAction);
            eventSender.send(new EmployeeEvent(header, opEmp.get()));
        }

        return new UpdateEmployeeResponse(!opEmp.isPresent(), empId);
    }
}
