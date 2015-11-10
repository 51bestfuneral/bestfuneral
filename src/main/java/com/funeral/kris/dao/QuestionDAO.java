package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.Question;

@Repository
public interface QuestionDAO extends PagingAndSortingRepository<Question, Integer>{

}
