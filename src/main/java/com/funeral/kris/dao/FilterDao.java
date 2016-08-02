package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.Filter;

@Repository
public interface FilterDao extends PagingAndSortingRepository<Filter, Integer>{

}
