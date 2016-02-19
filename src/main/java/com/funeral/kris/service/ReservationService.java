package com.funeral.kris.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.funeral.kris.model.Reservation;

public interface ReservationService {
	
	public void addResource(Reservation reservation);
	public void updateResource(Reservation reservation);
	public Reservation getResource(int id);
	public void deleteResource(int id);
	public List<Reservation> getResources(HttpServletRequest request);
	public List<Reservation> getResourcesByUserId(Integer userId);
	public boolean getDuplicate(Reservation reservation);
}
