package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.FuneralDetail;

@Repository
public interface FuneralDetailDAO extends PagingAndSortingRepository<FuneralDetail, Integer>{

}
