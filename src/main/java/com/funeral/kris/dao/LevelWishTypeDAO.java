package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.LevelWishType;

@Repository
public interface LevelWishTypeDAO extends PagingAndSortingRepository<LevelWishType, Integer>{

}
