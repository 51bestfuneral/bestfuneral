package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.ContractDAO;
import com.funeral.kris.model.Contract;

@Service
@Transactional
public class ContractServiceImpl implements ContractService {

	@Autowired
	private ContractDAO ContractDAO;

	public void addResource(Contract contract) {
		ContractDAO.save(contract);		
	}

	public void updateResource(Contract contract) {
		ContractDAO.save(contract);
	}

	public Contract getResource(int id) {
		return ContractDAO.findOne(id);
	}

	public void deleteResource(int id) {
		ContractDAO.delete(id);
	}

	public List<Contract> getResources() {
		List<Contract> contractList = new ArrayList<Contract>();
		Iterable<Contract> contractIter = ContractDAO.findAll();
		Iterator<Contract> iter = contractIter.iterator();
		while(iter.hasNext()) {
			Contract contract = iter.next();
			contractList.add(contract);
		}
		return contractList;
	}
}
