package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.TFQuestion;
@Repository
public interface TFQuestionDAO extends PagingAndSortingRepository<TFQuestion, Long>{

}
