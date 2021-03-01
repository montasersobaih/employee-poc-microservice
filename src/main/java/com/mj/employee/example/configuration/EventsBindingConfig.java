package com.mj.employee.example.configuration;

import com.mj.employee.example.channel.EmployeeEventChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(EmployeeEventChannel.class)
public class EventsBindingConfig {
}
