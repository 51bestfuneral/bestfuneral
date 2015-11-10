package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.Wishlist;

@Repository
public interface WishlistDAO extends PagingAndSortingRepository<Wishlist, Integer>{

}