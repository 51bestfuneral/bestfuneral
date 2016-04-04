package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.TCostCalculationTrace;
@Repository
public interface TCostCalculationTraceDAO  extends PagingAndSortingRepository<TCostCalculationTrace, Integer>{

}
