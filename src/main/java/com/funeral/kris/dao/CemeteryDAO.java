package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.Cemetery;

import java.util.List;

@Repository
public interface CemeteryDAO extends PagingAndSortingRepository<Cemetery, Integer>{

    public List<Cemetery> findByDistrict(String district);
}
