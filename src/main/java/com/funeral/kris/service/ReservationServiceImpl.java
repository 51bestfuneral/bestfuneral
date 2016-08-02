package com.funeral.kris.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.ReservationDAO;
import com.funeral.kris.model.Reservation;
import com.funeral.kris.util.SqlHelper;
import com.ibm.icu.text.SimpleDateFormat;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationDAO ReservationDAO;
	@Autowired
	private EntityManager em;

	public void addResource(Reservation reservation) {
		ReservationDAO.save(reservation);		
	}

	public void updateResource(Reservation reservation) {
		ReservationDAO.save(reservation);
	}

	public Reservation getResource(int id) {
		return ReservationDAO.findOne(id);
	}

	public void deleteResource(int id) {
		ReservationDAO.delete(id);
	}

	public List<Reservation> getResources(HttpServletRequest request) {
		String a = null;
		try {
		    a = SqlHelper.getSqlFromRequest("Reservation", request);
		}
		catch (Exception e) {
			
		}
		Query query = em.createQuery(a);
		List<Reservation> reservations = query.getResultList();
		return reservations;
	}

	public List<Reservation> getResourcesByUserId(Integer userId) {
		String a = "select r from Reservation r where r.userId = "+userId;
		Query query = em.createQuery(a);
		List<Reservation> reservations = query.getResultList();
		return reservations;
	}

	public boolean getDuplicate(Reservation reservation) {
		String a = "select r from Reservation r where r.userId = "+reservation.getUserId()
		    + " and r.cemeterId = "+reservation.getCemeterId()
		    + " and r.reservDte = '"+reservation.getReservDte()+"'";
		Query query = em.createQuery(a);
		List<Reservation> reservations = query.getResultList();
		if (reservations.size() > 0) {
			return true;
		}
		return false;
	}
}
