package com.funeral.kris.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.WishlistDetailDAO;
import com.funeral.kris.model.WishlistDetail;
import com.funeral.kris.util.SqlHelper;

@Service
@Transactional
public class WishlistDetailServiceImpl implements WishlistDetailService {

	@Autowired
	private WishlistDetailDAO WishlistDetailDAO;
	@Autowired
	private EntityManager em;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void addResource(WishlistDetail wishlistDetail) {

		BigDecimal selectedCost = new BigDecimal(wishlistDetail.getCount()).multiply(wishlistDetail.getPrice());

		wishlistDetail.setSelectedPrice(selectedCost);

		WishlistDetailDAO.save(wishlistDetail);
	}

	public void updateResource(WishlistDetail wishlistDetail) {
		BigDecimal selectedCost = new BigDecimal(wishlistDetail.getCount()).multiply(wishlistDetail.getPrice());

		wishlistDetail.setSelectedPrice(selectedCost);
		WishlistDetailDAO.save(wishlistDetail);
	}

	public WishlistDetail getResource(int id) {
		return WishlistDetailDAO.findOne(id);
	}

	public void deleteResource(int id) {
		WishlistDetailDAO.delete(id);
	}

	public List<WishlistDetail> getResources(HttpServletRequest request) {
		String a = null;

		try {
			a = SqlHelper.getSqlFromRequest("WishlistDetail", request);
		} catch (Exception e) {

		}
		Query query = em.createQuery(a);
		List<WishlistDetail> WishlistDetails = query.getResultList();
		return WishlistDetails;
	}

	public void deleteAllResources(String condition) {
		String a = null;
		a = "delete from wishlist_details where 1 = 1";
		if (condition != null && !condition.equals("")) {
			a = a + " and " + condition;
		}
		jdbcTemplate.update(a);
	}

	@Override
	public List<WishlistDetail> getResource() {

		Iterable<WishlistDetail> iterator = WishlistDetailDAO.findAll();
		List<WishlistDetail> list = new ArrayList<WishlistDetail>();

		Iterator<WishlistDetail> iter = iterator.iterator();
		while (iter.hasNext()) {
			WishlistDetail wishlistDetail = iter.next();
			list.add(wishlistDetail);
		}

		return list;

	}

	@Override
	public List<WishlistDetail> getResourceByWishListId(int wishListId) {

		List<WishlistDetail> list = new ArrayList<WishlistDetail>();

		List<WishlistDetail> fullList = this.getResource();

		if (fullList == null) {
			return list;
		} else {
			Iterator<WishlistDetail> iter = fullList.iterator();
			while (iter.hasNext()) {
				WishlistDetail wishlistDetail = iter.next();

				if (wishlistDetail.getWishlistId() == wishListId) {

					list.add(wishlistDetail);

				}

			}

		}

		return list;

	}

	@Override
	public List<WishlistDetail> getRecommendWishlistDetailByWishListId(int wishListId) {

		List<WishlistDetail> list = new ArrayList<WishlistDetail>();

		List<WishlistDetail> fullList = this.getResource();

		if (fullList == null) {
			return list;
		} else {
			Iterator<WishlistDetail> iter = fullList.iterator();
			while (iter.hasNext()) {
				WishlistDetail wishlistDetail = iter.next();

				if (wishlistDetail.getWishlistId() == wishListId &&wishlistDetail.getRecommend()!=null &&wishlistDetail.getRecommend().equals(1)) {

					list.add(wishlistDetail);

				}

			}

		}

		return list;

	}

	@Override
	public List<WishlistDetail> getSelectedWishlistDetailByWishListId(int wishListId) {
	
		 List<WishlistDetail>  wishListDetailList= 	this.getResourceByWishListId(wishListId);
		 
		 List<WishlistDetail>   list=new ArrayList();
		 
		 
		 if(wishListDetailList!=null&&wishListDetailList.size()>0){
			 
			 Iterator  iterator= wishListDetailList.iterator();
			 
			 while(iterator.hasNext()){
				 
				 WishlistDetail  detail=	 (WishlistDetail) iterator.next();
				 
				 
				 if(detail.getSelected()!=null&&detail.getSelected().intValue()==1){
					 
					 list.add(detail) ;
					 
				 }
				 
				 
				 
			 }
			 
			 
			 
		 }
		 
		 
		 
		 
		 
		return list;
		
		
		
		
		
	}
	@Override
	public List<WishlistDetail> getDirectWishlistDetailByWishListId(int wishListId) {
		
		List<WishlistDetail>  wishListDetailList= 	this.getResourceByWishListId(wishListId);
		
		List<WishlistDetail>   list=new ArrayList();
		
		
		if(wishListDetailList!=null&&wishListDetailList.size()>0){
			
			Iterator  iterator= wishListDetailList.iterator();
			
			while(iterator.hasNext()){
				
				WishlistDetail  detail=	 (WishlistDetail) iterator.next();
				
				
				if(detail.getSourceId()!=null&&detail.getSourceId().intValue()==2){
					
					list.add(detail) ;
					
				}
				
				
				
			}
			
			
			
		}
		
		
		
		
		
		return list;
		
		
		
		
		
	}
}
