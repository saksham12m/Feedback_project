package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Teams;

public interface TeamsRepository extends JpaRepository<Teams, Integer> {

	List<Teams> findByTopicsId(int topicId);

	boolean existsByNameAndTopicsId(String name, int TopicId);
}
