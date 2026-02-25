package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.TopicsDto;
import com.example.demo.entity.Topics;

public interface TopicsServiceInt {

	void saveTopics(TopicsDto topicsDto);

	void updateTopic(int id, TopicsDto topicsDto);

	void deleteTopic(int id);

	void deleteAllTopics();

	Topics getTopics(int id);

	List<Topics> getAllTopicsByPages(int pageNo, int size);
}
