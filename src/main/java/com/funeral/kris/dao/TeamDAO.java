package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.Team;

@Repository
public interface TeamDAO extends PagingAndSortingRepository<Team, Integer>{

}
