package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.Reservation;

@Repository
public interface ReservationDAO extends PagingAndSortingRepository<Reservation, Integer> {

}
