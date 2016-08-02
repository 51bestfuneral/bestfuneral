package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.TCemeteryGraveZone;



@Repository
public interface TCemeteryGraveZoneDAO  extends PagingAndSortingRepository<TCemeteryGraveZone, Integer>{

}	
