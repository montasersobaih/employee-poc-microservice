package com.mj.employee.example.repository;

import com.mj.core.springboot.model.QueryParam;
import com.mj.core.springboot.utils.ConverterUtils;
import com.mj.core.springboot.utils.MongoUtil;
import com.mj.employee.example.exception.GeneralSaveException;
import com.mj.employee.example.model.entity.EmployeeEntity;
import com.mongodb.client.result.DeleteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class EmployeeMongoRepository implements EmployeeRepository {

    private final MongoOperations mongoOperations;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public EmployeeMongoRepository(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public Page<EmployeeEntity> findAll(QueryParam param, PageRequest pageRequest) {
        List<EmployeeEntity> employees = new LinkedList<>();

        Query query = prepareQuery(param, true);
        Pageable pageable = Pageable.unpaged();

        long totalElements = mongoOperations.count(query, EmployeeEntity.class);

        if (Objects.nonNull(pageRequest)) {
            int pageNumber = pageRequest.getPageNumber();
            int pageElements = pageRequest.getPageSize();

            Sort sort = Sort.by(Sort.Order.desc("creationDate"));

            query.with(sort);
            query.skip(pageNumber * pageElements);
            query.limit(pageElements);

            pageable = PageRequest.of(pageNumber, pageElements, sort);
        }

        try {
            log.info("Getting employees");
            employees = mongoOperations.find(query, EmployeeEntity.class);
        } catch (Exception ex) {
            log.error("Error happened during getting employees\t", ex);
        }

        return new PageImpl<>(employees, pageable, totalElements);
    }

    @Override
    public Optional<EmployeeEntity> find(QueryParam param) {
        Optional<EmployeeEntity> optional = Optional.empty();

        try {
            log.info("Getting employee");
            Query query = MongoUtil.createQuery(param, true);
            if (MongoUtil.isNotEmptyQuery(query)) {
                EmployeeEntity entity = mongoOperations.findOne(query, EmployeeEntity.class);
                if (Objects.nonNull(entity)) {
                    optional = Optional.of(ConverterUtils.convert(entity, EmployeeEntity.class));
                }
            }
        } catch (Exception ex) {
            log.error("Error happened during get general", ex);
        }

        return optional;
    }

    @Override
    public Optional<EmployeeEntity> findById(String employeeId) {
        Optional<EmployeeEntity> optional = Optional.empty();

        try {
            log.info("Getting employee by id");
            Query query = new Query(Criteria.where("id").is(employeeId));
            optional = Optional.ofNullable(mongoOperations.findOne(query, EmployeeEntity.class));
        } catch (Exception ex) {
            log.error("Error happened during getting employee by id", ex);
        }

        return optional;
    }

    @Override
    public Optional<EmployeeEntity> save(EmployeeEntity entity) {
        Optional<EmployeeEntity> optional = Optional.empty();

        try {
            log.info("Save new employee");
            if (Objects.nonNull(entity)) {
                optional = Optional.of(mongoOperations.save(entity));
            }
        } catch (Exception ex) {
            log.error("Error happened during saving employee", ex);
            throw new GeneralSaveException();
        }

        return optional;
    }

    @Override
    public boolean deleteAll(QueryParam param) {
        boolean isSucceeded = false;

        try {
            log.info("Delete employees");
            Query query = prepareQuery(param, true);
            DeleteResult result = mongoOperations.remove(query, EmployeeEntity.class);
            isSucceeded = result.getDeletedCount() != 0L;
        } catch (Exception ex) {
            log.error("Error happened during deleting employees", ex);
        }

        return isSucceeded;
    }
}
