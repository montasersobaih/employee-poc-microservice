package com.mj.employee.example.service.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mj.core.springboot.service.handler.KafkaStreamListener;
import com.mj.employee.example.channel.ChannelsNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmployeeListener implements KafkaStreamListener<Object> {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    @StreamListener(ChannelsNames.INCOMING_EMPLOYEE_EVENT)
    public void listener(@Payload Object o) {
        log.info("Receive new event");

        try {
            log.info("A new data received =====> {}", new ObjectMapper().writeValueAsString(o));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

//        KafkaPublisher.send(generalChannel, eventFromShared);
    }
}
