package com.funeral.kris.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funeral.kris.bean.WishCateBean;
import com.funeral.kris.model.TWishCategorySub;
import com.funeral.kris.service.TWishCategoryService;

@Controller
@RequestMapping(value = "/displayWishType")
public class DisplayWishTypeContorller {
	@Autowired
	private TWishCategoryService WishCategoryService;

	@ResponseBody
	@RequestMapping(value = "/listWishCategory", method = RequestMethod.GET, produces = "application/json")
	public List<WishCateBean> listAll() {

		List<WishCateBean> wishCategoryList = WishCategoryService.listWishCates();
		return wishCategoryList;
	}
	@ResponseBody
	@RequestMapping(value = "/listWishCategorySub", method = RequestMethod.GET, produces = "application/json")
	public List<TWishCategorySub> listAllWishCategorySub() {
		
		List<TWishCategorySub> wishCateSubBeanList = new ArrayList();
		
		List<WishCateBean> wishCategoryList = WishCategoryService.listWishCates();
		
		Iterator iterator=wishCategoryList.iterator();
		
		
		while(iterator!=null&&iterator.hasNext()){
			
			WishCateBean bean=(WishCateBean) iterator.next();
			
			
			Iterator iteratorSub=bean.getSubwishList().iterator();
			
			while(iteratorSub.hasNext()){
				
				wishCateSubBeanList.add((TWishCategorySub) iteratorSub.next());

			}
			
			
		}

		
		
		
		
		return wishCateSubBeanList;
	}

}
