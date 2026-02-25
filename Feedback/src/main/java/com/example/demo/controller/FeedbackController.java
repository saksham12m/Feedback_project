package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.dto.FeedbacksDto;
import com.example.demo.entity.Feedbacks;
import com.example.demo.service.FeedbackServiceInt;

@CrossOrigin(origins = "*")
@RestController
public class FeedbackController {

	@Autowired
	FeedbackServiceInt feedbackServiceInt;

	@PostMapping("feedback")
	ResponseEntity feedback(@RequestBody FeedbacksDto feedbackDto) {
		feedbackServiceInt.savefeedback(feedbackDto);
		return new ResponseEntity(HttpStatus.CREATED);
	}

	@DeleteMapping("feedback/{id}")
	ResponseEntity deleteFeedback(@PathVariable int id) {
		feedbackServiceInt.deletefeedback(id);
		return new ResponseEntity(HttpStatus.OK);
	}

	@DeleteMapping("feedbacks")
	ResponseEntity deleteAllFeedBack() {
		feedbackServiceInt.deleteAllFeedbacks();
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("feedbacks")
	ResponseEntity<List<Feedbacks>> getAllFeedbacks(@RequestParam(defaultValue = "1") int pageNo,
			@RequestParam(defaultValue = "30") int size) {
		return new ResponseEntity<List<Feedbacks>>(feedbackServiceInt.getAllFeedbacksByPage(pageNo, size),
				HttpStatus.OK);
	}

	@GetMapping("feedbacks/{teamId}")
	ResponseEntity<List<Feedbacks>> getFeedbackByTeam(@PathVariable int teamId) {
		return new ResponseEntity<>(feedbackServiceInt.getFeedbacksByTeam(teamId), HttpStatus.OK);
	}
}
