package com.exam.repository;

import com.exam.entity.Scores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoresRepo extends JpaRepository<Scores, Long> {
}
