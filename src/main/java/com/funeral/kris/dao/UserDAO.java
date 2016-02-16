package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.User;

@Repository
public interface UserDAO extends PagingAndSortingRepository<User, Integer>{

}
