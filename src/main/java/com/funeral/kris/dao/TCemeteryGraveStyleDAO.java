package com.funeral.kris.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funeral.kris.model.TCemeteryGraveStyle;

import java.util.List;


@Repository
public interface TCemeteryGraveStyleDAO  extends PagingAndSortingRepository<TCemeteryGraveStyle, Integer>{

    List<TCemeteryGraveStyle> findByCemeteryId(int id);
}	

