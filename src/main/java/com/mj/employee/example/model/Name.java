package com.mj.employee.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Project employee-example-ms
 * @Author Montaser Sobaih
 * @Date 28-09-2020
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Name {

    @NotNull(message = "First name is required")
    @NotBlank(message = "First name should not be blank")
    @NotEmpty(message = "First name should not be empty")
    @Pattern(regexp = "\\w+(\\s\\w)*", message = "Invalid name format")
    private String firstName;

    @NotNull(message = "Second name is required")
    @NotBlank(message = "Second name should not be blank")
    @NotEmpty(message = "Second name should not be empty")
    @Pattern(regexp = "\\w+(\\s\\w)*", message = "Invalid name format")
    private String secondName;

    @NotNull(message = "Third name is required")
    @NotBlank(message = "Third name should not be blank")
    @NotEmpty(message = "Third name should not be empty")
    @Pattern(regexp = "\\w+(\\s\\w)*", message = "Invalid name format")
    private String thirdName;

    @NotNull(message = "Fourth name is required")
    @NotBlank(message = "Fourth name should not be blank")
    @NotEmpty(message = "Fourth name should not be empty")
    @Pattern(regexp = "\\w+(\\s\\w)*", message = "Invalid name format")
    private String fourthName;
}
