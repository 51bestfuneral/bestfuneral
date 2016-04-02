package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.Answer;

@Repository
public interface AnswerDAO extends PagingAndSortingRepository<Answer, Integer>{

}
