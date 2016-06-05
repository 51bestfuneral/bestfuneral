package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.constants.WishConstants;
import com.funeral.kris.dao.OrderDetailDAO;
import com.funeral.kris.model.OrderDetail;
import com.funeral.kris.model.Wish;
import com.funeral.kris.util.SqlHelper;

@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	private OrderDetailDAO OrderDetailDAO;
	@Autowired
	private EntityManager em;
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private WishService wishService;

	public Map<Integer, Wish> wishsMap = null;

	private void initialWishMap() {
		HttpServletRequest fakeRequest = null;
		List<Wish> wishs = wishService.getResources();
		wishsMap = new HashMap<Integer, Wish>();
		for (Wish wish : wishs) {
			wishsMap.put(wish.getWishId(), wish);
		}

	}

	public void addResource(OrderDetail orderDetail) {
		OrderDetailDAO.save(orderDetail);
	}

	public void updateResource(OrderDetail orderDetail) {
		OrderDetailDAO.save(orderDetail);
	}

	public OrderDetail getResource(int id) {
		return OrderDetailDAO.findOne(id);
	}

	public void deleteResource(int id) {
		OrderDetailDAO.delete(id);
	}

	public void deleteResourceByOrderId(int id) {
		String a = "delete from t_order_detail where order_id = " + id;
		jdbcTemplate.execute(a);
	}

	public List<OrderDetail> getResources(HttpServletRequest request) {
		String a = null;
		try {
			a = SqlHelper.getSqlFromRequest("OrderDetail", request);
		} catch (Exception e) {

		}
		Query query = em.createQuery(a);
		List<OrderDetail> orderDetails = query.getResultList();
		return orderDetails;
	}

	@Override
	public List<OrderDetail> getResourcesByWishOrderId(int wishOrderId) {
		Iterable<OrderDetail> iterator = OrderDetailDAO.findAll();

		List<OrderDetail> list = new ArrayList<OrderDetail>();

		if (iterator != null) {
			Iterator it = iterator.iterator();

			while (it.hasNext()) {

				OrderDetail detail = (OrderDetail) it.next();

				if (detail.getOrderId().intValue() == wishOrderId) {
					list.add(detail);
				}
			}
		}

		return list;
	}

	@Override
	public List<OrderDetail> getOrderDetailFromWishList(int wishListId) {
		Iterable<OrderDetail> iterator = OrderDetailDAO.findAll();
		if (this.wishsMap == null) {

			this.initialWishMap();
		}

		List<OrderDetail> list = new ArrayList<OrderDetail>();

		if (iterator != null) {
			Iterator it = iterator.iterator();

			while (it.hasNext()) {

				OrderDetail detail = (OrderDetail) it.next();
				
				if (detail.getWishListId().intValue() == wishListId
						&& detail.getSourceId().intValue() == WishConstants.wish_source_set) {
					detail.setWishName(wishsMap.get(detail.getWishId()).getWishName());	
					
					list.add(detail);
				}
			}
		}

		return list;
	}

	@Override
	public List<OrderDetail> getResourcesByWishOrderIdForShoppingCart(
			int wishOrderId) {

		Iterable<OrderDetail> iterator = OrderDetailDAO.findAll();

		List<OrderDetail> list = new ArrayList<OrderDetail>();

		if (iterator != null) {
			Iterator it = iterator.iterator();

			while (it.hasNext()) {

				OrderDetail detail = (OrderDetail) it.next();

				if (detail.getOrderId().intValue() == wishOrderId
						&& detail.getSourceId().intValue() == WishConstants.wish_source_direct) {
					list.add(detail);
				}
			}
		}

		return list;

	}
}
