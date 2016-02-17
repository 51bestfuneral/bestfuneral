package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.TCemeteryMoreImgs;
@Repository
public interface TCemeteryMoreImgsDAO extends PagingAndSortingRepository<TCemeteryMoreImgs, Integer>{

}
