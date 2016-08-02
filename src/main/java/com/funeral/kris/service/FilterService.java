package com.funeral.kris.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.funeral.kris.model.Filter;

public interface FilterService {

    public void addResource(Filter cart);
    public void updateResource(Filter cart);
    public Filter getResource(int id);
    public void deleteResource(int id);
    public List<Filter> getResources(HttpServletRequest request);
}
