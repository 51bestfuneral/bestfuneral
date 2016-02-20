package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.Option;

@Repository
public interface OptionDAO extends PagingAndSortingRepository<Option, Long>{

}
