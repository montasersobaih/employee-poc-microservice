package com.mj.employee.example.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Project employee-example-ms
 * @Author Montaser Sobaih
 * @Date 03-10-2020
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeResponse {

    private boolean newRecord;

    private String id;
}
