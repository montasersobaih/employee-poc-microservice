package com.mj.employee.example.model.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.mj.employee.example.model.Name;
import com.mj.employee.example.model.Submission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Validated
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSchema extends Submission {

    @Id
    private String id;

    @Valid
    @JsonUnwrapped
    @NotNull(message = "Name is required")
    private Name name;

    @NotNull(message = "Email Address is required")
    @NotEmpty(message = "Email Address should not be empty")
    @NotBlank(message = "Email Address should not be blank")
    @Pattern(regexp = "(\\w\\d?[\\.\\_\\-]?\\w\\d?)+\\@\\w+(\\.[\\w|\\d])+", message = "Invalid email format")
    private String email;

    @Field("birth_date")
    @JsonProperty("birth_date")
    @NotNull(message = "Birth date is required")
    @NotEmpty(message = "Birth date should not be empty")
    @NotBlank(message = "Birth date should not be blank")
    @Past(message = "Birth date should be from the past")
    private LocalDate birthDate;

    @NotNull(message = "Speciality is required")
    @NotEmpty(message = "Speciality should not be empty")
    @NotBlank(message = "Speciality should not be blank")
    @Pattern(regexp = "\\w+(\\s\\w)*", message = "Invalid speciality format")
    private String speciality;

    @NotNull(message = "Nationality is required")
    @NotEmpty(message = "Nationality should not be empty")
    @NotBlank(message = "Nationality should not be blank")
    @Pattern(regexp = "\\w", message = "Invalid nationality format")
    private String nationality;

    @Positive(message = "Salary must be more than zero")
    private double salary;
}
