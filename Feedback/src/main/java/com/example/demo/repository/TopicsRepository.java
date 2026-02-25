package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Topics;

public interface TopicsRepository extends JpaRepository<Topics, Integer> {

	boolean existsTopicByName(String name);

}
