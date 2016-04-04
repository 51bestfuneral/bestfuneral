package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.TCostCalculationTraceDAO;
import com.funeral.kris.model.TCostCalculationTrace;

@Service
@Transactional
public class TCostCalculationTraceServiceImpl implements TCostCalculationTraceService {

	@Autowired
	private TCostCalculationTraceDAO tCostCalculationTraceDAO;
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addResource(TCostCalculationTrace entity) {
		tCostCalculationTraceDAO.save(entity);

	}

	@Override
	public void updateResource(TCostCalculationTrace entity) {
		tCostCalculationTraceDAO.save(entity);

	}

	@Override
	public TCostCalculationTrace getResource(int id) {

		return tCostCalculationTraceDAO.findOne(id);
	}

	@Override
	public void deleteResource(int id) {
		tCostCalculationTraceDAO.delete(id);
	}

	@Override
	public List<TCostCalculationTrace> getResources() {

		List<TCostCalculationTrace> costCalculationTraceList = new ArrayList<TCostCalculationTrace>();
		Iterable<TCostCalculationTrace> traceIter = tCostCalculationTraceDAO.findAll();
		Iterator<TCostCalculationTrace> iter = traceIter.iterator();
		while (iter.hasNext()) {
			TCostCalculationTrace trace = iter.next();
			costCalculationTraceList.add(trace);
		}
		return costCalculationTraceList;

	}

	@Override
	public List<TCostCalculationTrace> loadByUserId(int userId) {
		List<TCostCalculationTrace> costCalculationTraceList = this.getResources();

		Iterator iterator = costCalculationTraceList.iterator();

		while (iterator.hasNext()) {

			TCostCalculationTrace trace = (TCostCalculationTrace) iterator.next();

			if (trace.getUserId() != trace.getUserId()) {
				costCalculationTraceList.remove(trace);
			}

		}

		return costCalculationTraceList;

	}

	@Override
	public boolean selected(int cateId, int classId, List<TCostCalculationTrace> costCalculationTraceList) {
		Iterator iterator = costCalculationTraceList.iterator();

		while (iterator.hasNext()) {

			TCostCalculationTrace trace = (TCostCalculationTrace) iterator.next();

			if (cateId == trace.getCateId().intValue() && classId == trace.getClassId().intValue()) {
				return true;

			}

		}
		return false;
	}

	@Override
	public void deleteByCateIdClassIdUserId(int cateId, int classId, int userId) {

		List<TCostCalculationTrace> costCalculationTraceList = this.getResources();

		Iterator iterator = costCalculationTraceList.iterator();

		while (iterator.hasNext()) {

			TCostCalculationTrace trace = (TCostCalculationTrace) iterator.next();

			if (cateId == trace.getCateId().intValue() && classId == trace.getClassId().intValue()
					&& userId == trace.getUserId().intValue()) {
				this.deleteResource(trace.getId());
				return;
			}

		}

	}

	@Override
	public void deleteByCateIdUserId(int cateId, int userId) {
		List<TCostCalculationTrace> costCalculationTraceList = this.getResources();

		Iterator iterator = costCalculationTraceList.iterator();

		while (iterator.hasNext()) {

			TCostCalculationTrace trace = (TCostCalculationTrace) iterator.next();

			if (cateId == trace.getCateId().intValue() && userId == trace.getUserId().intValue()) {
				this.deleteResource(trace.getId());
				return;
			}

		}

	}

	@Override
	public TCostCalculationTrace loadByCateIdClassIdUserId(int cateId, int classId, int userId) {

		List<TCostCalculationTrace> costCalculationTraceList = this.getResources();

		Iterator iterator = costCalculationTraceList.iterator();

		while (iterator.hasNext()) {

			TCostCalculationTrace trace = (TCostCalculationTrace) iterator.next();

			if (trace.getUserId().intValue() == trace.getUserId() && trace.getCateId().intValue() == cateId
					&& classId == trace.getClassId().intValue()) {
				return trace;
			}

		}
		return null;

	}

}
