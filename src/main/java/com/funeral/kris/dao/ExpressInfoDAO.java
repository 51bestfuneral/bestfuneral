package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.ExpressInfo;

@Repository
public interface ExpressInfoDAO extends PagingAndSortingRepository<ExpressInfo, Long>{

}
