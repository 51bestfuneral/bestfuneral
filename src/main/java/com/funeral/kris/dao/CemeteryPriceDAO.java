package com.funeral.kris.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.TCemeteryPrice;

@Repository
public interface CemeteryPriceDAO extends PagingAndSortingRepository<TCemeteryPrice, Integer>{

	
	public List<TCemeteryPrice> findListByGraveStyleId(int graveStyleId);
	
	public List<TCemeteryPrice> findListByEpigraphStyleId(int epigraphStyleId);
}
