package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.constants.WishConstants;
import com.funeral.kris.dao.WishOrderDAO;
import com.funeral.kris.model.WishOrder;
import com.funeral.kris.util.SqlHelper;

@Service
@Transactional
public class WishOrderServiceImpl implements WishOrderService {

	@Autowired
	private WishOrderDAO WishOrderDAO;
	@Autowired
	private EntityManager em;

	public void addResource(WishOrder wishOrder) {
		WishOrderDAO.save(wishOrder);
	}

	public void updateResource(WishOrder wishOrder) {
		WishOrderDAO.save(wishOrder);
	}

	public WishOrder getResource(int id) {
		return WishOrderDAO.findOne(id);
	}

	public void deleteResource(int id) {
		WishOrderDAO.delete(id);
	}

	public List<WishOrder> getResources(HttpServletRequest request) {
		String a = null;
		try {
			a = SqlHelper.getSqlFromRequest("WishOrder", request);
		} catch (Exception e) {

		}
		Query query = em.createQuery(a);
		List<WishOrder> wishOrders = query.getResultList();
		return wishOrders;
	}

	@Override
	public WishOrder getLatestOpenWishOrderForSet(int userId) {

		
//		public  static int wishorder_status_init=1;
//		public  static int wishorder_status_pendingPay=2;
//		public  static int wishorder_status_paied=3;
//		public  static int wishorder_status_rejected=4;
//		public  static int wishorder_status_closed=5;
		
		List<WishOrder> wishOrderList = this.getResource();

		if (wishOrderList == null || wishOrderList.size() == 0) {
			System.out.println(this.getClass() + "  no pending wish list!");

			return null;
		}

		List<WishOrder> subOrderList = new ArrayList<WishOrder>();

		Iterator iterator = wishOrderList.iterator();

		while (iterator.hasNext()) {
			WishOrder wishOrder = (WishOrder) iterator.next();

			if (wishOrder.getSourceId().intValue() == WishConstants.order_source_set
					&& wishOrder.getUserId().intValue() == userId
					&& (wishOrder.getStatusId().intValue() == WishConstants.wishorder_status_init || wishOrder
							.getStatusId().intValue() == WishConstants.wishorder_status_pendingPay)) {
				subOrderList.add(wishOrder);
			}

		}

		if (subOrderList.size() == 0) {
			System.out.println(this.getClass()
					+ " getLatestOpenWishOrderForSet no pending wish list!");

			return null;
		}

		Collections.sort(subOrderList, new SortByAge());

		System.out.println(this.getClass() + " getLatestOpenWishOrderForSet ="
				+ subOrderList.get(subOrderList.size() - 1).getWishOrderId());

		return subOrderList.get(subOrderList.size() - 1);

	}

	class SortByAge implements Comparator {
		public int compare(Object o1, Object o2) {
			WishOrder s1 = (WishOrder) o1;
			WishOrder s2 = (WishOrder) o2;
			return s1.getWishOrderId().compareTo(s2.getWishOrderId());

		}
	}

	@Override
	public List<WishOrder> getResource() {

		Iterable iterable = WishOrderDAO.findAll();

		System.out.println(this.getClass() + " getResource  iterable= "
				+ iterable);

		if (iterable == null) {
			return null;
		}

		System.out.println(this.getClass() + " getResource  111= " + 111);

		List<WishOrder> wishOrderList = new ArrayList<WishOrder>();

		Iterator iterator = iterable.iterator();

		while (iterator.hasNext()) {
			WishOrder wishOrder = (com.funeral.kris.model.WishOrder) iterator
					.next();
			wishOrderList.add(wishOrder);
		}

		return wishOrderList;
	}

	@Override
	public List<WishOrder> getResourceByUserId(int userId) {
		List<WishOrder> wishOrderList = this.getResource();
		List<WishOrder> subWishOrderList = new ArrayList<WishOrder>();
		if (wishOrderList == null) {

			return null;
		}

		Iterator iterator = wishOrderList.iterator();

		while (iterator.hasNext()) {

			WishOrder wishOrder = (WishOrder) iterator.next();
			if (wishOrder.getUserId().intValue() == userId) {
				subWishOrderList.add(wishOrder);
			}
		}

		return subWishOrderList;
	}

	@Override
	public List<WishOrder> getOpenWishOrderListByPayWishOrderId(
			int payWishOrderId) {

		List<WishOrder> wishOrderList = this.getResource();

		List<WishOrder> subWishOrderList = new ArrayList<WishOrder>();

		if (wishOrderList != null) {

			Iterator iterator = wishOrderList.iterator();

			WishOrder wishOrder = (WishOrder) iterator.next();

			if (payWishOrderId==wishOrder.getPayWishOrderId()&&(wishOrder.getStatusId().intValue() == WishConstants.wishorder_status_pendingPay
					|| wishOrder.getStatusId().intValue() == WishConstants.wishorder_status_init))
				{subWishOrderList.add(wishOrder);}

		}
		return subWishOrderList;

	}

	@Override
	public WishOrder getWishOrderByOrderNo(String orderNo) {
		
		
		List<WishOrder>  wishOrderList=this.getResource();
		
		
		if(wishOrderList!=null){
			Iterator iterator=	wishOrderList.iterator();
			
			while(iterator.hasNext()){
				
				WishOrder wishOrder=		(WishOrder) iterator.next();
				
				if(orderNo.equals(wishOrder.getOrderNo())){
					
					return wishOrder;
				}
				
			}
		}
		return null;
	}
}
