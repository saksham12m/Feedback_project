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
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.dto.TopicsDto;
import com.example.demo.entity.Topics;
import com.example.demo.service.TeamsServiceImpl;
import com.example.demo.service.TopicsServiceInt;

//Team 3

@CrossOrigin(origins = "*")
@RestController
public class TopicsController {

	private final TeamsServiceImpl teamsServiceImpl;

	@Autowired
	TopicsServiceInt topicsServiceInt;

	TopicsController(TeamsServiceImpl teamsServiceImpl) {
		this.teamsServiceImpl = teamsServiceImpl;
	}

	@PostMapping("topic")
	ResponseEntity Topic(@RequestBody TopicsDto topicsDto) {
		topicsServiceInt.saveTopics(topicsDto);
		return new ResponseEntity(HttpStatus.CREATED);
	}

	@PutMapping("topic/{id}")
	ResponseEntity updateTopic(@PathVariable int id, @RequestBody TopicsDto topicsDto) {
		topicsServiceInt.updateTopic(id, topicsDto);
		return new ResponseEntity(HttpStatus.OK);
	}

	@DeleteMapping("topic/{id}")
	ResponseEntity deleteTopics(@PathVariable int id) {
		topicsServiceInt.deleteTopic(id);
		return new ResponseEntity(HttpStatus.OK);
	}

	@DeleteMapping("topics")
	ResponseEntity deleteAllTopics() {
		topicsServiceInt.deleteAllTopics();
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("topic/{id}")
	ResponseEntity<Topics> getTopics(@PathVariable int id) {
		return new ResponseEntity<Topics>(topicsServiceInt.getTopics(id), HttpStatus.OK);
	}

	@GetMapping("topics")
	ResponseEntity<List<Topics>> getAllTopics(@RequestParam(defaultValue = "1") int pageNo,
			@RequestParam(defaultValue = "30") int size) {
		return new ResponseEntity<List<Topics>>(topicsServiceInt.getAllTopicsByPages(pageNo, size), HttpStatus.OK);
	}
}