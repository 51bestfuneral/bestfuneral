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

import com.funeral.kris.model.Contract;
import com.funeral.kris.service.ContractService;

@Controller
@RequestMapping(value="/contract")
public class ContractController {
	
	@Autowired
	private ContractService contractService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addContractPage() {
		ModelAndView modelAndView = new ModelAndView("add-contract-form");
		modelAndView.addObject("contract", new Contract());
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingContract(@ModelAttribute Contract contract) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		contractService.addResource(contract);
		
		String message = "Contract was successfully added.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.GET, produces = "application/json")
	public List<Contract> listOfContracts() {
		ModelAndView modelAndView = new ModelAndView("list-of-contracts");

		List<Contract> contracts = contractService.getResources();
		modelAndView.addObject("contracts", contracts);

		return contracts;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editContractPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-contract-form");
		Contract contract = contractService.getResource(id);
		modelAndView.addObject("contract",contract);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingContract(@ModelAttribute Contract contract, @PathVariable Integer id) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		contractService.updateResource(contract);
		
		String message = "Contract was successfully edited.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteContract(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		contractService.deleteResource(id);
		String message = "Contract was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

}
