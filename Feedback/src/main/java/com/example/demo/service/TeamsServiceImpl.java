package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TeamsDto;
import com.example.demo.entity.Teams;
import com.example.demo.entity.Topics;
import com.example.demo.exception.TeamsServiceException;
import com.example.demo.exception.TopicsServiceException;
import com.example.demo.repository.TeamsRepository;
import com.example.demo.repository.TopicsRepository;

@Service
public class TeamsServiceImpl implements TeamsServiceInt {

	@Autowired
	TeamsRepository teamsRepository;

	@Autowired
	TopicsRepository topicsRepository;

	@Override
	public void saveTeam(TeamsDto teamsDto) {

		Optional<Topics> o = topicsRepository.findById(teamsDto.getTopicId());
		if (o.isEmpty()) {
			throw new TopicsServiceException("Id dosent exist", HttpStatus.NOT_FOUND);
		}
		Topics topics = o.get();

		if (teamsDto.getName() == null || teamsDto.getName().trim().isEmpty()) {
			throw new TeamsServiceException("Empty Fields", HttpStatus.BAD_REQUEST);
		}

		boolean team = teamsRepository.existsByNameAndTopicsId(teamsDto.getName(), teamsDto.getTopicId());
		if (team) {
			throw new TeamsServiceException("Team Name Already Exist In Topic", HttpStatus.BAD_REQUEST);
		}

		Teams teams = new Teams();
		teams.setName(teamsDto.getName());
		teams.setTopics(topics);

		teamsRepository.save(teams);
	}

	@Override
	public void updateTeam(int id, TeamsDto teamsDto) {

		Teams teams = teamsRepository.findById(id).get();
		
		if (teamsDto.getName() == null || teamsDto.getName().trim().isEmpty()) {
			throw new TopicsServiceException("Empty Field", HttpStatus.BAD_REQUEST);
		}

		if (teams != null) {
			teams.setName(teamsDto.getName());
		} else {
			throw new TopicsServiceException("Id Dosen't Exist", HttpStatus.BAD_REQUEST);
		}
		teamsRepository.save(teams);

	}

	@Override
	public void deleteTeam(int id) {

		if (!teamsRepository.existsById(id)) {
			throw new TeamsServiceException("Id Dosen't Exist", HttpStatus.NOT_FOUND);
		}
		teamsRepository.deleteById(id);
	}

	@Override
	public void deleteAllTeams() {
		teamsRepository.deleteAll();
	}

	@Override
	public Teams getTeam(int id) {
		Optional<Teams> o = teamsRepository.findById(id);
		if (o.isEmpty()) {
			throw new TeamsServiceException("Id Dosen't Exist", HttpStatus.NOT_FOUND);
		}
		return o.get();
	}

	@Override
	public List<Teams> getAllTeams() {
		return teamsRepository.findAll();
	}

	@Override
	public List<Teams> getAllTeamsBypage(int pageNo, int size) {
		if (pageNo < 0 || size <= 0) {
			throw new TeamsServiceException("Invalid page number or size.", HttpStatus.BAD_REQUEST);
		}

		int pageNo2 = pageNo - 1;

		PageRequest pageRequest = PageRequest.of(pageNo2, size);
		Page<Teams> list = teamsRepository.findAll(pageRequest);
		return list.getContent();
	}

	@Override
	public List<Teams> getTeamsByTopic(int topicid) {
		return teamsRepository.findByTopicsId(topicid);
	}

}
