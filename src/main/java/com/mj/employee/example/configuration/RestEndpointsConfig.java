package com.mj.employee.example.configuration;

import com.mj.core.springboot.command.CommandExecutor;
import com.mj.employee.example.api.employee.EmployeeRest;
import com.mj.employee.example.api.employee.EmployeeRestImpl;
import com.mj.employee.example.api.health.HealthRest;
import com.mj.employee.example.api.health.HealthRestImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class RestEndpointsConfig {

    @Bean
    public HealthRest healthRest() {
        return new HealthRestImpl();
    }

    @Bean
    @Autowired
    public EmployeeRest employeeRest(CommandExecutor executor, HttpServletRequest servletRequest) {
        return new EmployeeRestImpl(executor, servletRequest);
    }
}
