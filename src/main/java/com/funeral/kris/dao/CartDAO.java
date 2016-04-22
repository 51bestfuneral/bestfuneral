package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.Cart;

@Repository
public interface CartDAO extends PagingAndSortingRepository<Cart, Integer> {

}
