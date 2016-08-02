package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.FilterDao;
import com.funeral.kris.model.Filter;
import com.funeral.kris.util.SqlHelper;

@Service
@Transactional
public class FilterServiceImpl implements FilterService {

    @Autowired
    private FilterDao filterDAO;
    @Autowired
    private EntityManager em;

    public void addResource(Filter filter) {
        filterDAO.save(filter);
    }

    public void updateResource(Filter filter) {
        filterDAO.save(filter);
    }

    public Filter getResource(int id) {
        return filterDAO.findOne(id);
    }

    public void deleteResource(int id) {
        filterDAO.delete(id);
    }



    public List<Filter> getResources(HttpServletRequest request) {
        String a = null;
        try {
            a = SqlHelper.getSqlFromRequest("Filter", request);
        }
        catch (Exception e) {

        }
        Query query = em.createQuery(a);
        List<Filter> filters = query.getResultList();
        return filters;
    }
}
