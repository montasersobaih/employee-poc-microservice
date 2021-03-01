package com.mj.employee.example.api.health;

import com.mj.employee.example.model.response.HealthResponse;

public class HealthRestImpl implements HealthRest {

    @Override
    public HealthResponse healthz() {
        return new HealthResponse("OK");
    }
}
