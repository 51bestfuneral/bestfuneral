package com.funeral.kris.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.ExpressInfo;

@Repository
public interface ExpressInfoDAO extends PagingAndSortingRepository<ExpressInfo, Long>{

	public List<ExpressInfo> findListByUserIdAndStatusId(int userId,int statusId);
	
	public List<ExpressInfo> findListByUserId(int userId);
	
	public List<ExpressInfo> findListByWishOrderId(int wishOrderId);
}
