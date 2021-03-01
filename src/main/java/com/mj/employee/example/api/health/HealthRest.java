package com.mj.employee.example.api.health;

import com.mj.employee.example.model.response.HealthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController("/healthz")
public interface HealthRest {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    HealthResponse healthz();
}
