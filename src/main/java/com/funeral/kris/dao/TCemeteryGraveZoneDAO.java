package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.TCemeteryGraveZone;

import java.util.List;


@Repository
public interface TCemeteryGraveZoneDAO  extends PagingAndSortingRepository<TCemeteryGraveZone, Integer>{
    List<TCemeteryGraveZone> findByCemeteryId(int id);
}	
