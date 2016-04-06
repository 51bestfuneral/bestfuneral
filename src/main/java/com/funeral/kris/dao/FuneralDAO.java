package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.Funeral;

@Repository
public interface FuneralDAO extends PagingAndSortingRepository<Funeral, Integer>{

}
