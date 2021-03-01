package com.mj.employee.example.model.dto;

import com.mj.core.springboot.model.dto.GeneralDTO;
import com.mj.core.springboot.model.dto.HateoasDTO;
import com.mj.core.springboot.model.dto.MetadataDTO;
import com.mj.employee.example.model.response.EmployeeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesDTO extends GeneralDTO {

    private EmployeesData data;

    public EmployeesDTO(MetadataDTO metadata, Collection<HateoasDTO> links, List<EmployeeResponse> data) {
        super(metadata, links);
        this.data = new EmployeesData(data);
    }

    public EmployeesDTO(MetadataDTO metadata, Collection<HateoasDTO> links, EmployeeResponse data) {
        super(metadata, links);
        this.data = new EmployeesData(data);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmployeesData {

        private List<EmployeeResponse> employees;

        EmployeesData(EmployeeResponse employee) {
            this.employees = new ArrayList<>();
            if (Objects.nonNull(employee)) {
                this.employees.add(employee);
            }
        }
    }
}
