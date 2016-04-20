package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.TeamDAO;
import com.funeral.kris.model.Team;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamDAO TeamDAO;

	public void addTeam(Team team) {
		TeamDAO.save(team);		
	}

	public void updateTeam(Team team) {
		TeamDAO.save(team);
	}

	public Team getTeam(int id) {
		return TeamDAO.findOne(id);
	}

	public void deleteTeam(int id) {
		TeamDAO.delete(id);
	}

	public List<Team> getTeams() {
		List<Team> teamList = new ArrayList<Team>();
		Iterable<Team> teamIter = TeamDAO.findAll();
		Iterator<Team> iter = teamIter.iterator();
		while(iter.hasNext()) {
			Team team = iter.next();
			teamList.add(team);
		}
		return teamList;
	}
}
