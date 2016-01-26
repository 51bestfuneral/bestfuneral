package com.funeral.kris.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.funeral.kris.model.Wish;

public interface WishService {
	
	public void addResource(Wish wish);
	public void updateResource(Wish wish);
	public Wish getResource(int id);
	public void deleteResource(int id);
	public List<Wish> getResources(HttpServletRequest request);

}
