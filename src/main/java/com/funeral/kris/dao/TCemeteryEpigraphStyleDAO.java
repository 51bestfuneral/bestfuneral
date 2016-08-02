package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.TCemeteryEpigraphStyle;



	@Repository
	public interface TCemeteryEpigraphStyleDAO  extends PagingAndSortingRepository<TCemeteryEpigraphStyle, Integer>{

	}	
	
	