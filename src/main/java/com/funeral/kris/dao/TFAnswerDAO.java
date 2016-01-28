package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.TFAnswer;
@Repository
public interface TFAnswerDAO extends PagingAndSortingRepository<TFAnswer, Long>{

}
