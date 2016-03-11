package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.FuneralTemplate;

@Repository
public interface FuneralTemplateDAO extends PagingAndSortingRepository<FuneralTemplate, Integer>{

}
