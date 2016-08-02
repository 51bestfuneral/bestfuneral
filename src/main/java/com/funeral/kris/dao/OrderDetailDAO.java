package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.OrderDetail;

@Repository
public interface  OrderDetailDAO extends PagingAndSortingRepository<OrderDetail, Integer> {

}
