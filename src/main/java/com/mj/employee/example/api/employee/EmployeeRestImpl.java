package com.mj.employee.example.api.employee;

import com.mj.core.springboot.command.CommandExecutor;
import com.mj.core.springboot.model.Pagination;
import com.mj.core.springboot.model.dto.HateoasDTO;
import com.mj.core.springboot.model.dto.MetadataDTO;
import com.mj.core.springboot.utils.HateoasUtils;
import com.mj.core.springboot.utils.MetadataUtils;
import com.mj.employee.example.model.Name;
import com.mj.employee.example.model.command.AddEmployeeCommand;
import com.mj.employee.example.model.command.DeleteEmployeeCommand;
import com.mj.employee.example.model.command.GetEmployeeCommand;
import com.mj.employee.example.model.command.UpdateEmployeeCommand;
import com.mj.employee.example.model.dto.EmployeeDTO;
import com.mj.employee.example.model.dto.EmployeesDTO;
import com.mj.employee.example.model.dto.NewEmployeeDTO;
import com.mj.employee.example.model.response.EmployeeResponse;
import com.mj.employee.example.model.response.NewEmployeeResponse;
import com.mj.employee.example.model.response.UpdateEmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Collection;

public class EmployeeRestImpl implements EmployeeRest {

    private final CommandExecutor executor;

    private final HttpServletRequest servletRequest;

    @Autowired
    public EmployeeRestImpl(CommandExecutor executor, HttpServletRequest servletRequest) {
        this.executor = executor;
        this.servletRequest = servletRequest;
    }

    @Override
    public EmployeesDTO getEmployees(GetEmployeeCommand command) {
//        GetEmployeeCommand command = new GetEmployeeCommand(
//                id, name, email, birthDate, speciality, nationality, salary, Pagination.createPageRequest(offset, limit)
//        );

        Page<EmployeeResponse> pageResult = executor.execute(command);

        MetadataDTO metadata = MetadataUtils.getParameters(servletRequest);
        if (pageResult.getPageable().isPaged()) {
            Pagination pagination = new Pagination(
                    pageResult.getTotalPages(), pageResult.getTotalElements(), pageResult.hasNext()
            );

            metadata.setPagination(pagination);
        }

//        Object[] params = Stream.<Object>of(dateFrom, dateTo, offset, limit).filter(Objects::nonNull).toArray();
        Collection<HateoasDTO> links = HateoasUtils.preparingHateoas(getClass(), null);

        return new EmployeesDTO(metadata, links, pageResult.getContent());
    }

    @Override
    public ResponseEntity<EmployeeDTO> getEmployee(String id) {
        Page<EmployeeResponse> pageResult = executor.execute(new GetEmployeeCommand(id));

        MetadataDTO metadata = MetadataUtils.getParameters(servletRequest);
        Collection<HateoasDTO> links = HateoasUtils.preparingHateoas(getClass(), id);

        EmployeeDTO dto = new EmployeeDTO(metadata, links, pageResult.get().findFirst().orElse(null));
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON_UTF8).body(dto);
    }

    @Override
    public ResponseEntity<NewEmployeeDTO> addEmployee(AddEmployeeCommand command) {
        NewEmployeeResponse response = executor.execute(command);

        MetadataDTO metadata = MetadataUtils.getParameters(servletRequest);
        Collection<HateoasDTO> links = HateoasUtils.preparingHateoas(getClass(), response.getId());

        NewEmployeeDTO dto = new NewEmployeeDTO(metadata, links, response);
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON_UTF8).body(dto);
    }

    @Override
    public ResponseEntity<NewEmployeeDTO> updateEmployee(String id, UpdateEmployeeCommand command) {
        command.setId(id);
        UpdateEmployeeResponse response = executor.execute(command);

        if (response.isNewRecord()) {
            MetadataDTO metadata = MetadataUtils.getParameters(servletRequest);
            Collection<HateoasDTO> links = HateoasUtils.preparingHateoas(getClass(), id, command);

            NewEmployeeDTO dto = new NewEmployeeDTO(metadata, links, response.getId());

            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON_UTF8).body(dto);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> deleteEmployees(
            String id,
            Name name,
            String email,
            LocalDate birthDate,
            String speciality,
            String nationality,
            Double salary,
            LocalDate dateFrom,
            LocalDate dateTo
    ) {
        DeleteEmployeeCommand command = new DeleteEmployeeCommand(
                id, name, email, birthDate, speciality, nationality, salary/*, dateFrom, dateTo*/
        );

        boolean isDone = executor.execute(command);
        return new ResponseEntity<>(isDone ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> deleteEmployee(String id) {
        DeleteEmployeeCommand command = new DeleteEmployeeCommand(null);

        boolean isDone = executor.execute(command);
        return new ResponseEntity<>(isDone ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
