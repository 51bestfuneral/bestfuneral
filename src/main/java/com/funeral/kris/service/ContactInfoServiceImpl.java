package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.ContactInfoDAO;
import com.funeral.kris.model.ContactInfo;

@Service
@Transactional
public class ContactInfoServiceImpl implements ContactInfoService {
	@Autowired
	private ContactInfoDAO contactInfoDAO;

	@Override
	public void addResource(ContactInfo contactInfo) {
		contactInfoDAO.save(contactInfo);
	}

	@Override
	public void updateResource(ContactInfo contactInfo) {
		contactInfoDAO.save(contactInfo);

	}

	@Override
	public List<ContactInfo> getByUserId(int userId) {
		List<ContactInfo> list = this.getResources();
		
		
		List<ContactInfo> contactList = new ArrayList();

		if (list == null) {
			return contactList;
		}

		Iterator<ContactInfo> iterator = list.iterator();

		while (iterator.hasNext()) {

			ContactInfo contactInfo = iterator.next();

			if (contactInfo.getUserId().intValue() == userId) {

				contactList.add(contactInfo);
			}

		}

		return contactList;
	}

	public ContactInfoDAO getContactInfoDAO() {
		return contactInfoDAO;
	}

	public void setContactInfoDAO(ContactInfoDAO contactInfoDAO) {
		this.contactInfoDAO = contactInfoDAO;
	}

	@Override
	public ContactInfo getResource(int id) {

		return contactInfoDAO.findOne(id);
	}

	@Override
	public void deleteResource(int id) {
		contactInfoDAO.delete(id);
	}

	@Override
	public List<ContactInfo> getResources() {

		List<ContactInfo> list = new ArrayList<ContactInfo>();
		Iterable<ContactInfo> iter = contactInfoDAO.findAll();
		if (iter == null) {

			return list;
		}

		Iterator<ContactInfo> iterator = iter.iterator();
		while (iterator.hasNext()) {
			ContactInfo contactInfo = iterator.next();
			list.add(contactInfo);
		}
		return list;
	}

}
