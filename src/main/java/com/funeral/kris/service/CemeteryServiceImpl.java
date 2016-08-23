package com.funeral.kris.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import com.funeral.kris.bean.CemeteryPriceBean;
import com.funeral.kris.model.*;
import com.funeral.kris.util.SqlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.CemeteryDAO;

@SuppressWarnings("JpaQlInspection")
@Service
@Transactional
public class CemeteryServiceImpl implements CemeteryService {

	@Autowired
	private CemeteryDAO CemeteryDAO;
	@Autowired
	private EntityManager em;
	
	public void addResource(Cemetery cemetery) {
		CemeteryDAO.save(cemetery);
	}

	public void updateResource(Cemetery cemetery) {
		CemeteryDAO.save(cemetery);
	}

	public Cemetery getResource(int id) {
		return CemeteryDAO.findOne(id);
	}

	public void deleteResource(int id) {
		CemeteryDAO.delete(id);
	}

	public List<Cemetery> getResources(HttpServletRequest request) {
		String a = null;
		try {
			a = SqlHelper.getSqlFromRequest("Cemetery", request);
		} catch (Exception e) {

		}
		Query query = em.createQuery(a);
		List<Cemetery> cemeteryList = query.getResultList();
		return cemeteryList;
	}

	public List<Cemetery> getResourcesByDistrict(String district) {
		return CemeteryDAO.findByDistrict(district);
	}

	public List<CemeteryPriceBean> getCemeteryPrices(int cemeteryId) {
		List<CemeteryPriceBean> prices = new ArrayList<CemeteryPriceBean>();
		String sql = "select a.description, b.style, c.price" +
				"       from TCemeteryGraveStyle a,TCemeteryEpigraphStyle b,TCemeteryPrice c" +
				"      where c.graveStyleId = a.id" +
				"        and c.epigraphStyleId = b.id " +
				"        and c.cemeteryId="+cemeteryId;
		List list =  em.createQuery(sql).getResultList();
		for (int i=0;i<list.size();i++) {
			Object[] obj=(Object[])list.get(i);
			CemeteryPriceBean bean = new CemeteryPriceBean();
			bean.setEpigraphStyle((String)obj[0]);
			bean.setGraveStyle((String)obj[1]);
			bean.setPrice(((BigDecimal)obj[2]).longValue());
			prices.add(bean);
		}
		return prices;
	}
}
