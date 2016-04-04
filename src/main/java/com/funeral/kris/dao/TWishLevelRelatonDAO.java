package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.TWishLevelRelation;
@Repository

public interface TWishLevelRelatonDAO extends PagingAndSortingRepository<TWishLevelRelation, Integer> {

}
