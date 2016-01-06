package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.TServiceCategory;
@Repository
public interface TServiceCategoryDAO  extends PagingAndSortingRepository<TServiceCategory, Integer>{

}