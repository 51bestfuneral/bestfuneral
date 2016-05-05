package com.funeral.kris.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.funeral.kris.model.TWishCategorySub;

public class TestPush {
	
	
@SuppressWarnings("unchecked")
public void test(){
	
	
	TWishCategorySub  sub1=new TWishCategorySub();
	TWishCategorySub  sub2=new TWishCategorySub();
	TWishCategorySub  sub3=new TWishCategorySub();
	
	
	sub1.setWishId(1l);
	sub2.setWishId(2l);
	sub3.setWishId(3l);
	
	List<TWishCategorySub> list=new ArrayList<TWishCategorySub>();
	
	SortByAge  sort=new SortByAge();
	
	
	list.add(sub2);
	list.add(sub3);
	list.add(sub1);
	
	Collections.sort(list, sort);

	
	Iterator iterator=	list.iterator();
	
	
	while(iterator.hasNext()){
		
		TWishCategorySub sub=	(TWishCategorySub) iterator.next();
	System.out.println(sub.getWishId());
	
	
	}
	
	
}
	
class SortByAge implements Comparator {
	public int compare(Object o1, Object o2) {
		TWishCategorySub s1 = (TWishCategorySub) o1;
		TWishCategorySub s2 = (TWishCategorySub) o2;
		
		System.out.println("   si "+s1.getWishId()+"  s2  "+s2.getWishId());
		return s1.getWishId().compareTo(s2.getWishId());
	   
    }
}

}
