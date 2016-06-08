package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.ContactInfo;

public interface ContactInfoService {

	public void addResource(ContactInfo contactInfo);
	public void updateResource(ContactInfo contactInfo);
	public List<ContactInfo> getByUserId(int userId);
	public ContactInfo getResource(int id);
	public void deleteResource(int id);
	public List<ContactInfo> getResources();
	public ContactInfo getUsingContacter(int userId);
	public ContactInfo getContacterByWishOrderId(int wishOrderId);

	public ContactInfo getUsingContacterByWishOrderId(int wishOrderId);

	
	
}
