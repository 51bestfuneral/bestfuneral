package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.Contract;

@Repository
public interface ContractDAO extends PagingAndSortingRepository<Contract, Integer>{

}
