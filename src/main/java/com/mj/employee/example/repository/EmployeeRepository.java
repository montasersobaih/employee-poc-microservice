package com.mj.employee.example.repository;

import com.mj.core.springboot.model.QueryParam;
import com.mj.core.springboot.utils.FieldExtractor;
import com.mj.core.springboot.utils.LocalDateUtil;
import com.mj.core.springboot.utils.MongoUtil;
import com.mj.employee.example.model.entity.EmployeeEntity;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Map;
import java.util.Optional;

public interface EmployeeRepository {

    String COLLECTION_NAME = "employee";

    default Page<EmployeeEntity> findAll(QueryParam param) {
        return findAll(param, null);
    }

    Page<EmployeeEntity> findAll(QueryParam param, PageRequest pageRequest);

    Optional<EmployeeEntity> find(QueryParam param);

    Optional<EmployeeEntity> findById(String employeeId);

    Optional<EmployeeEntity> save(EmployeeEntity entity);

    boolean deleteAll(QueryParam param);

    default Query prepareQuery(QueryParam param, boolean ignoreNulls) {
        Query query = new Query();
        Criteria dateCriteria = new Criteria("creationDate");

        Map<String, Object> queryParams = param.getFields(ignoreNulls);
        for (Map.Entry<String, Object> queryParam : queryParams.entrySet()) {
            String paramName = queryParam.getKey();
            Object paramValue = queryParam.getValue();
            switch (paramName) {
                case "name":
                    Map<String, Object> objectParams = FieldExtractor.getFields(paramValue, ignoreNulls);
                    for (Map.Entry<String, Object> objectParam : objectParams.entrySet()) {
                        String objectParamKey = String.join(".", paramName, objectParam.getKey());
                        Object objectParamValue = objectParam.getValue();
                        query.addCriteria(Criteria.where(objectParamKey).is(objectParamValue));
                    }
                    break;
                case "dateFrom":
                    dateCriteria = dateCriteria.gte(LocalDateUtil.toDateTime(paramValue));
                    break;
                case "dateTo":
                    dateCriteria = dateCriteria.lte(LocalDateUtil.toDateTime(paramValue));
                    break;
                default:
                    query.addCriteria(Criteria.where(paramName).is(paramValue));
                    break;
            }
        }

        Document document = dateCriteria.getCriteriaObject();
        if (MongoUtil.haveValue(document, dateCriteria.getKey())) {
            query.addCriteria(dateCriteria);
        }

        return query;
    }
}
