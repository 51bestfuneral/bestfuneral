package com.funeral.kris.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funeral.kris.busModel.ReservationJson;
import com.funeral.kris.model.Cemetery;
import com.funeral.kris.model.Reservation;
import com.funeral.kris.model.User;
import com.funeral.kris.service.CemeteryService;
import com.funeral.kris.service.ReservationService;

@Controller
@RequestMapping(value="/reservation")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	@Autowired
	private CemeteryService cemeteryService;

	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.GET, produces = "application/json")
	public List<ReservationJson> listOfReservations(HttpServletRequest request) {
		List<ReservationJson> reservationJsons = new ArrayList<ReservationJson>();
        User user = (User)request.getSession().getAttribute("user");
		List<Reservation> reservations = reservationService.getResourcesByUserId(user.getUsrId());
		for (Reservation reservation: reservations) {
			Cemetery c = cemeteryService.getResource(reservation.getCemeterId());
			ReservationJson reservationJson = new ReservationJson();
			reservationJson.setAddress(c.getAddress());
			reservationJson.setCemeteryDesc(c.getCemeteryDesc());
			reservationJson.setCemeteryId(c.getCemeteryId());
			reservationJson.setCemeteryName(c.getCemeteryName());
			reservationJson.setFeature(c.getFeature());
			reservationJson.setImgUrl(c.getDescImgUrl());
			reservationJson.setUserId(user.getUsrId());
			reservationJson.setUserName(user.getUserName());
			reservationJson.setPhoneNumber(reservation.getPhoneNumber());
			reservationJson.setReservDate(reservation.getReservDte());
			reservationJsons.add(reservationJson);
		}
		return reservationJsons;
	}

	@ResponseBody
	@RequestMapping(value="/add",method=RequestMethod.POST, produces = "application/json")
	public int addServation(@RequestBody Reservation reservation, HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
		reservation.setUserId(user.getUsrId());
		if (user == null) {
			return 1;
		}
		if (reservationService.getDuplicate(reservation)) {
			return 2;
		}
		reservationService.addResource(reservation);
		return 0;
	}
}
