package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.CartDetail;

@Repository
public interface  CartDetailDAO extends PagingAndSortingRepository<CartDetail, Integer> {

}
