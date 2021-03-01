package com.mj.employee.example.service.handler;

import com.mj.core.springboot.command.CommandHandler;
import com.mj.core.springboot.utils.ConverterUtils;
import com.mj.employee.example.model.command.GetEmployeeCommand;
import com.mj.employee.example.model.entity.EmployeeEntity;
import com.mj.employee.example.model.response.EmployeeResponse;
import com.mj.employee.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * @Project employee-example-ms
 * @Author Montaser Sobaih
 * @Date 29-09-2020
 */

@Component
public class GetEmployeeCommandHandler implements CommandHandler<GetEmployeeCommand, Page<EmployeeResponse>> {

    private final EmployeeRepository repository;

    @Autowired
    public GetEmployeeCommandHandler(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<EmployeeResponse> handle(GetEmployeeCommand command) {
        Page<EmployeeEntity> page = repository.findAll(command, command.getPageRequest());

        return page.map(entity -> ConverterUtils.convert(entity, EmployeeResponse.class));
    }
}
