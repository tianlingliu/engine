package com.thinkbit.engine.repository;

import com.thinkbit.common.usural.operation.CrudOperation;
import com.thinkbit.engine.api.repository.bean.City;
import com.thinkbit.engine.api.repository.data.example.CityExample;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudOperation<City, CityExample, Long> {
}