package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.WishType;

@Repository
public interface WishTypeDAO extends PagingAndSortingRepository<WishType, Integer>{

}
