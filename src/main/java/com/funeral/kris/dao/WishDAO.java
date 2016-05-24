package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.Wish;

@Repository
public interface WishDAO extends PagingAndSortingRepository<Wish, Integer>{

}
