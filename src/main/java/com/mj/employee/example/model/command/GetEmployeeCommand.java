package com.mj.employee.example.model.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mj.core.springboot.command.Command;
import com.mj.core.springboot.model.QueryParam;
import com.mj.employee.example.model.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

/**
 * @Project employee-example-ms
 * @Author Montaser Sobaih
 * @Date 29-09-2020
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetEmployeeCommand implements Command, QueryParam {

    private String id;

    private Name name;

    @Pattern(regexp = "(\\w\\d?[\\.\\_\\-]?\\w\\d?)+\\@\\w+(\\.[\\w|\\d])+", message = "Invalid email format")
    private String email;

    @Past(message = "Birth date should be from the past")
    private LocalDate birthDate;

    @Pattern(regexp = "\\w+(\\s\\w)*", message = "Invalid speciality format")
    private String speciality;

    @Pattern(regexp = "\\w", message = "Invalid nationality format")
    private String nationality;

    @Positive(message = "Salary must be more than zero")
    private double salary;

    @JsonIgnore
    private PageRequest pageRequest;

    public GetEmployeeCommand(String id) {
        this.setId(id);
    }
}

