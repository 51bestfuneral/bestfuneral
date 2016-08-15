package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.TCemeteryKeywords;

import java.util.List;


@Repository
public interface TCemeteryKeywordsDAO  extends PagingAndSortingRepository<TCemeteryKeywords, Integer>{
    List<TCemeteryKeywords> findByCemeteryId(int id);
}	
