package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.TaoConfig;

@Repository
public interface TaoConfigDAO extends PagingAndSortingRepository<TaoConfig, Integer>{

}
