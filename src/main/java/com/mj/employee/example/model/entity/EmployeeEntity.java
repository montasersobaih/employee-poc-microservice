package com.mj.employee.example.model.entity;

import com.mj.employee.example.model.schema.EmployeeSchema;
import com.mj.employee.example.repository.EmployeeRepository;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = EmployeeRepository.COLLECTION_NAME)
public class EmployeeEntity extends EmployeeSchema {

}
