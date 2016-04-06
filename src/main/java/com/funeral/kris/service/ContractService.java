package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.Contract;

public interface ContractService {
	
	public void addResource(Contract contract);
	public void updateResource(Contract contract);
	public Contract getResource(int id);
	public void deleteResource(int id);
	public List<Contract> getResources();

}
