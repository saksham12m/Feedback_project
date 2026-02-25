package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.TeamsDto;
import com.example.demo.entity.Teams;

public interface TeamsServiceInt {

	void saveTeam(TeamsDto teamsDto);

	void updateTeam(int id, TeamsDto teamsDto);

	void deleteTeam(int id);

	void deleteAllTeams();

	Teams getTeam(int id);

	List<Teams> getAllTeams();

	List<Teams> getAllTeamsBypage(int pageNo, int size);

	List<Teams> getTeamsByTopic(int topicid);

}
