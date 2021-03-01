package com.mj.employee.example.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mj.core.springboot.model.dto.GeneralDTO;
import com.mj.core.springboot.model.dto.HateoasDTO;
import com.mj.core.springboot.model.dto.MetadataDTO;
import com.mj.employee.example.model.response.EmployeeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * @Project employee-example-ms
 * @Author Montaser Sobaih
 * @Date 03-10-2020
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO extends GeneralDTO {

    @JsonIgnoreProperties("id")
    private EmployeeResponse data;

    public EmployeeDTO(MetadataDTO metadata, Collection<HateoasDTO> links, EmployeeResponse data) {
        super(metadata, links);
        this.data = data;
    }
}

