package com.mj.employee.example.model.dto;

import com.mj.core.springboot.model.dto.GeneralDTO;
import com.mj.core.springboot.model.dto.HateoasDTO;
import com.mj.core.springboot.model.dto.MetadataDTO;
import com.mj.employee.example.model.response.NewEmployeeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NewEmployeeDTO extends GeneralDTO {

    private NewEmployeeResponse data;

    public NewEmployeeDTO(MetadataDTO metadata, Collection<HateoasDTO> links, String employeeId) {
        super(metadata, links);
        this.data = new NewEmployeeResponse(employeeId);
    }

    public NewEmployeeDTO(MetadataDTO metadata, Collection<HateoasDTO> links, NewEmployeeResponse data) {
        super(metadata, links);
        this.data = data;
    }

}
