package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Feedbacks;

public interface FeedbackRepository extends JpaRepository<Feedbacks, Integer> {

	List<Feedbacks> findByTeamsId(int teamId);

}
