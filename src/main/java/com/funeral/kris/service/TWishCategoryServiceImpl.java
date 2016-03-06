package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.bean.WishCateBean;
import com.funeral.kris.dao.TWishCategoryDAO;
import com.funeral.kris.model.TWishCategory;
import com.funeral.kris.model.TWishCategorySub;
import com.funeral.kris.service.TWishCategorySubServiceImpl.SortByAge;

@Service
@Transactional
public class TWishCategoryServiceImpl implements TWishCategoryService {

	@Autowired
	private TWishCategoryDAO tWishCategoryDAO;
	@Autowired
	private TWishCategoryDAO tWishCategorySubDAO;

	@Autowired
	private TWishCategorySubService tWishCategorySubService;
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addResource(TWishCategory tWishCategory) {

		tWishCategoryDAO.save(tWishCategory);

	}

	@Override
	public void updateResource(TWishCategory tWishCategory) {
		tWishCategoryDAO.save(tWishCategory);

	}

	@Override
	public TWishCategory getResource(int id) {
		// TODO Auto-generated method stub
		return tWishCategoryDAO.findOne(id);
	}

	@Override
	public void deleteResource(int id) {
		tWishCategoryDAO.delete(id);

	}

	@Override
	public List<TWishCategory> getResources() {

		List wishCategoryList = new ArrayList();

		Iterator<TWishCategory> iterator = tWishCategoryDAO.findAll().iterator();

		while (iterator.hasNext()) {

			System.out.println("-----------------------------");

			wishCategoryList.add(iterator.next());

		}
		return wishCategoryList;

	}

	@Override
	public List<WishCateBean> listWishCates() {

		List<TWishCategory> wishCategoryList = getResources();

		List<WishCateBean> wishCateBeanList = new ArrayList();

		Iterator iterator = wishCategoryList.iterator();

		while (iterator.hasNext()) {

			TWishCategory tWishCategory = (TWishCategory) iterator.next();

			WishCateBean wishCateBean = new WishCateBean();

			wishCateBean.setCateId(tWishCategory.getCateId().intValue());

			wishCateBean.setCateName(tWishCategory.getCateName());

			List subwishList = tWishCategorySubService.loadByCateId(tWishCategory.getCateId().intValue());


			
			wishCateBean.setSubwishList(subwishList);

			wishCateBean.setSubCateSize(subwishList.size());
			
			
			int height=60*subwishList.size();
			
			String style= " width: 100%;height:" +height+"px;list-style-type:none;border-top: 1px solid #ECE7E7;"+ "line-height :"+ height+"px;margin-bottom: 0;";
			
			String fontStyle="line-height :"+ height+"px;margin-bottom: 0;";
			wishCateBean.setFontStyle(fontStyle);
			wishCateBean.setStyle(style);
			wishCateBeanList.add(wishCateBean);

		}

		return wishCateBeanList;
	}
	
	

}
