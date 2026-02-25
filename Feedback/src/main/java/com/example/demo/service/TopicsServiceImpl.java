package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TopicsDto;
import com.example.demo.entity.Topics;
import com.example.demo.exception.TopicsServiceException;
import com.example.demo.repository.TopicsRepository;

@Service
public class TopicsServiceImpl implements TopicsServiceInt {

	@Autowired
	TopicsRepository topicsRepository;

	@Override
	public void saveTopics(TopicsDto topicsDto) {

		if (topicsDto.getName() == null || topicsDto.getName().trim().isEmpty()) {
			throw new TopicsServiceException("Empty Field", HttpStatus.BAD_REQUEST);
		}

		boolean topic = topicsRepository.existsTopicByName(topicsDto.getName());

		if (topic) {
			throw new TopicsServiceException("Name Already Exist", HttpStatus.BAD_REQUEST);
		}

		Topics topics = new Topics();
		topics.setName(topicsDto.getName());
		topics.setDate(LocalDate.now());

		topicsRepository.save(topics);
	}

	@Override
	public void updateTopic(int id, TopicsDto topicsDto) {
		
		Topics topic = topicsRepository.findById(id).get();
		
		if(topicsDto.getName() == null || topicsDto.getName().trim().isEmpty()) {
			throw new TopicsServiceException("Empty Field", HttpStatus.BAD_REQUEST);
		}
		
		if (topic != null) {
			topic.setName(topicsDto.getName());
		} else {
			throw new TopicsServiceException("Id Dosen't Exist", HttpStatus.BAD_REQUEST);
		}
		 topicsRepository.save(topic);
	}

	@Override
	public void deleteTopic(int id) {

		if (!topicsRepository.existsById(id)) {
			throw new TopicsServiceException("Id doesn't exist", HttpStatus.NOT_FOUND);
		}
		topicsRepository.deleteById(id);
	}

	@Override
	public void deleteAllTopics() {
		topicsRepository.deleteAll();
	}

	@Override
	public Topics getTopics(int id) {
		Optional<Topics> o = topicsRepository.findById(id);
		if (o.isEmpty()) {
			throw new TopicsServiceException("Empty field", HttpStatus.BAD_REQUEST);
		}
		return o.get();
	}

	@Override
	public List<Topics> getAllTopicsByPages(int pageNo, int size) {

		int pageNo2 = pageNo - 1;

		PageRequest pr = PageRequest.of(pageNo2, size);

		return topicsRepository.findAll(pr).getContent();
	}

}
