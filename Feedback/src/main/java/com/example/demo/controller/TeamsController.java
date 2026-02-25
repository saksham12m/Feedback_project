package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TeamsDto;
import com.example.demo.dto.TopicsDto;
import com.example.demo.entity.Teams;
import com.example.demo.service.TeamsServiceInt;

@RestController
public class TeamsController {

	@Autowired
	TeamsServiceInt teamsServiceInt;

	@PostMapping("team")
	ResponseEntity team(@RequestBody TeamsDto teamsDto) {
		teamsServiceInt.saveTeam(teamsDto);
		return new ResponseEntity(HttpStatus.CREATED);
	}

	@PutMapping("team/{id}")
	ResponseEntity updateTeam(@PathVariable int id, @RequestBody TeamsDto teamsDto) {
		teamsServiceInt.updateTeam(id, teamsDto);
		return new ResponseEntity(HttpStatus.OK);
	}

	@DeleteMapping("team/{id}")
	ResponseEntity deleteTeam(@PathVariable int id) {
		teamsServiceInt.deleteTeam(id);
		return new ResponseEntity(HttpStatus.OK);
	}

	@DeleteMapping("teams")
	ResponseEntity deleteAllTeams() {
		teamsServiceInt.deleteAllTeams();
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("team/{id}")
	ResponseEntity<Teams> getTeam(@PathVariable int id) {
		return new ResponseEntity<Teams>(teamsServiceInt.getTeam(id), HttpStatus.OK);
	}

	@GetMapping("teamss")
	ResponseEntity<List<Teams>> getAllTeams() {
		return new ResponseEntity<List<Teams>>(teamsServiceInt.getAllTeams(), HttpStatus.OK);
	}

	@GetMapping("teams")
	ResponseEntity<List<Teams>> getAllTeams(@RequestParam(defaultValue = "1") int pageNo,
			@RequestParam(defaultValue = "30") int size) {
		return new ResponseEntity<List<Teams>>(teamsServiceInt.getAllTeamsBypage(pageNo, size), HttpStatus.OK);
	}

	@GetMapping("teams/{topicId}")
	ResponseEntity<List<Teams>> getAllTopic(@PathVariable int topicId) {
		return new ResponseEntity<List<Teams>>(teamsServiceInt.getTeamsByTopic(topicId), HttpStatus.OK);
	}
}
