package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.TFeeCollection;

@Repository
public interface FeeCollectionDAO  extends PagingAndSortingRepository<TFeeCollection, Integer>{

}
