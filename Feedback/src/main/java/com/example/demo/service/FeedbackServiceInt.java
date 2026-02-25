package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.FeedbacksDto;
import com.example.demo.entity.Feedbacks;

public interface FeedbackServiceInt {

	void savefeedback(FeedbacksDto feedbackDto);

	void deletefeedback(int id);

	void deleteAllFeedbacks();

	List<Feedbacks> getAllFeedbacksByPage(int pageNo, int size);

	List<Feedbacks> getFeedbacksByTeam(int teamId);
}
