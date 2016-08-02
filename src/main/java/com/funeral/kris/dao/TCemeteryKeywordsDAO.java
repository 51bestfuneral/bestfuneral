package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.TCemeteryKeywords;




@Repository
public interface TCemeteryKeywordsDAO  extends PagingAndSortingRepository<TCemeteryKeywords, Integer>{

}	
