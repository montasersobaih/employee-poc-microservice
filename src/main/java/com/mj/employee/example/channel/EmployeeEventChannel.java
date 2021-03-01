package com.mj.employee.example.channel;

import com.mj.core.springboot.channel.KafkaChannel;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface EmployeeEventChannel extends KafkaChannel {

    @Output(ChannelsNames.OUTGOING_EMPLOYEE_EVENT)
    @Override
    MessageChannel messageChannel();

    @Input(ChannelsNames.INCOMING_EMPLOYEE_EVENT)
    @Override
    SubscribableChannel subscribableChannel();
}
