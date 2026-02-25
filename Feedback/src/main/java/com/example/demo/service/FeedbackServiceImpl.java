package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dto.FeedbacksDto;
import com.example.demo.entity.Feedbacks;
import com.example.demo.entity.Teams;
import com.example.demo.exception.FeedbackServiceException;
import com.example.demo.repository.FeedbackRepository;
import com.example.demo.repository.TeamsRepository;

@Service
public class FeedbackServiceImpl implements FeedbackServiceInt {

	@Autowired
	FeedbackRepository feedbackRepository;

	@Autowired
	TeamsRepository teamsRepository;

	@Override
	public void savefeedback(FeedbacksDto feedbackDto) {

		Optional<Teams> o = teamsRepository.findById(feedbackDto.getTeamId());
		if (o.isEmpty()) {
			throw new FeedbackServiceException("Empty Team Id", HttpStatus.BAD_REQUEST);
		}
		Teams teams = o.get();

		if (feedbackDto.getFeedback() == null || feedbackDto.getFeedback().trim().isEmpty()) {
			throw new FeedbackServiceException("Empty fields", HttpStatus.BAD_REQUEST);
		}

		Feedbacks feedback = new Feedbacks();
		feedback.setFeedback(feedbackDto.getFeedback());
		feedback.setDate(LocalDate.now());
		feedback.setTeams(teams);

		feedbackRepository.save(feedback);

	}

	@Override
	public void deletefeedback(int id) {
		if (!feedbackRepository.existsById(id)) {
			throw new FeedbackServiceException("Id Dosen't Exist", HttpStatus.NOT_FOUND);
		}
		feedbackRepository.deleteById(id);
	}

	@Override
	public void deleteAllFeedbacks() {
		feedbackRepository.deleteAll();
	}

	@Override
	public List<Feedbacks> getAllFeedbacksByPage(int pageNo, int size) {
		if (pageNo < 1 || size <= 0) {
			throw new FeedbackServiceException("Invalid page number or size.", HttpStatus.BAD_REQUEST);
		}

		int pageNo2 = pageNo - 1;

		PageRequest pageRequest = PageRequest.of(pageNo2, size);
		Page<Feedbacks> list = feedbackRepository.findAll(pageRequest);
		return list.getContent();
	}

	@Override
	public List<Feedbacks> getFeedbacksByTeam(int teamId) {
		if (teamId <= 0) {
			throw new FeedbackServiceException("Invalid Team Id", HttpStatus.BAD_REQUEST);
		}

		Optional<Teams> team = teamsRepository.findById(teamId);
		if (team.isEmpty()) {
			throw new FeedbackServiceException("Team doesn't exist", HttpStatus.NOT_FOUND);
		}
		return feedbackRepository.findByTeamsId(teamId);
	}
}
