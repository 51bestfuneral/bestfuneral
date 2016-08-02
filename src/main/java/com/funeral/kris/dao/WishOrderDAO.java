package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.WishOrder;

@Repository
public interface WishOrderDAO extends PagingAndSortingRepository<WishOrder, Integer> {

}
