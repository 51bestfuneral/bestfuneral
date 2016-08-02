package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.Order;

@Repository
public interface  OrderDAO extends PagingAndSortingRepository<Order, Integer> {

}
