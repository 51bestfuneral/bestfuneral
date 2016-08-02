package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.Message;

@Repository
public interface MessageDAO extends PagingAndSortingRepository<Message, Integer>{

}
