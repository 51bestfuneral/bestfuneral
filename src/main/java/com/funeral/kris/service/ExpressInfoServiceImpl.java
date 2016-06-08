package com.funeral.kris.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.ExpressInfoDAO;
import com.funeral.kris.model.ExpressInfo;

@Service
@Transactional
public class ExpressInfoServiceImpl implements ExpressInfoService {

	@Autowired
	private ExpressInfoDAO expressInfoDAO;

	@Override
	public void addResource(ExpressInfo expressInfo) {
		expressInfoDAO.save(expressInfo);
	}

	@Override
	public void updateResource(ExpressInfo expressInfo) {
		expressInfoDAO.save(expressInfo);

	}

	@Override
	public List<ExpressInfo> getByUserId(int userId) {

		List<ExpressInfo> list = expressInfoDAO.findListByUserId(userId);
		Iterable<ExpressInfo> iter = expressInfoDAO.findAll();
		if (iter == null) {

			return list;
		}

		Iterator<ExpressInfo> iterator = iter.iterator();
		while (iterator.hasNext()) {
			ExpressInfo expressInfo = iterator.next();
			if (expressInfo.getUserId().intValue() == userId)
				list.add(expressInfo);
		}
		return list;

	}

	@Override
	public List<ExpressInfo> getUncompledExpressInfoByUserId(int userId, int statusId) {

		List<ExpressInfo> list= expressInfoDAO.findListByUserIdAndStatusId(userId, statusId);
		if(list==null){
			return new ArrayList<ExpressInfo>();
		}
		return list;
		
	}

	@Override
	public List<ExpressInfo> getResources() {

		List<ExpressInfo> list = new ArrayList<ExpressInfo>();
		Iterable<ExpressInfo> iter = expressInfoDAO.findAll();
		if (iter == null) {

			return list;
		}

		Iterator<ExpressInfo> iterator = iter.iterator();
		while (iterator.hasNext()) {
			ExpressInfo expressInfo = iterator.next();
			list.add(expressInfo);
		}
		return list;

	}

	@Override
	public ExpressInfo getUsingExpressInfo(int userId) {

		List<ExpressInfo> list = expressInfoDAO.findListByUserIdAndStatusId(userId, 1);

		if (list != null && list.size() > 0) {

			Iterator iter = list.iterator();

			while (iter.hasNext()) {
				ExpressInfo info = (ExpressInfo) iter.next();
				if (info.getDeliveryMethod().intValue() != 3) {
					info.setExpressFee(BigDecimal.ZERO);
				}
				return info;

			}

		}

		return new ExpressInfo();
	}

	@Override
	public ExpressInfo getResource(long id) {
		// TODO Auto-generated method stub
		return expressInfoDAO.findOne(id);
	}

	@Override
	public void deleteResource(long id) {
		expressInfoDAO.delete(id);

	}

	@Override
	public ExpressInfo getExpressInfoByWishOrderId(int wishOrderId) {

		List<ExpressInfo> list = expressInfoDAO.findListByWishOrderId(wishOrderId);
		if (list != null && list.size() > 0) {
			Iterator iter = list.iterator();
			while (iter.hasNext()) {
				ExpressInfo info = (ExpressInfo) iter.next();
					if (info.getDeliveryMethod().intValue() != 3) {
						info.setExpressFee(BigDecimal.ZERO);
					}
					return info;
			}

		}

		return new ExpressInfo();

	}

}
