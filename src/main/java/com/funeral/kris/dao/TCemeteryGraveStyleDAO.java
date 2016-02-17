package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.TCemeteryGraveStyle;




@Repository
public interface TCemeteryGraveStyleDAO  extends PagingAndSortingRepository<TCemeteryGraveStyle, Integer>{

}	

