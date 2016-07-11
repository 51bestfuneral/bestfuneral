package com.funeral.kris.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.model.Filter;
import com.funeral.kris.service.FilterService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/filter")
public class FilterController {

    @Autowired
    private FilterService filterService;

    @RequestMapping(value="/add", method=RequestMethod.GET)
    public ModelAndView addFilterPage() {
        ModelAndView modelAndView = new ModelAndView("add-filter-form");
        modelAndView.addObject("filter", new Filter());
        return modelAndView;
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public ModelAndView addingFilter(@ModelAttribute Filter filter) {

        ModelAndView modelAndView = new ModelAndView("home");
        filterService.addResource(filter);

        String message = "Filter was successfully added.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value="/list",method=RequestMethod.GET, produces = "application/json")
    public List<Filter> listOfFilters(HttpServletRequest request) {
        List<Filter> filters = filterService.getResources(request);

        return filters;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editFilterPage(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("edit-filter-form");
        Filter filter = filterService.getResource(id);
        modelAndView.addObject("filter",filter);
        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView edditingFilter(@ModelAttribute Filter filter, @PathVariable Integer id) {

        ModelAndView modelAndView = new ModelAndView("home");

        filterService.updateResource(filter);

        String message = "Filter was successfully edited.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deleteFilter(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("home");
        filterService.deleteResource(id);
        String message = "Filter was successfully deleted.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

}
