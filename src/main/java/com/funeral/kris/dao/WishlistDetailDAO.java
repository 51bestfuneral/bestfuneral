package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.WishlistDetail;

@Repository
public interface WishlistDetailDAO extends PagingAndSortingRepository<WishlistDetail, Integer>{

}