package com.exam.repository;

import com.exam.entity.Exams;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamsRepo extends JpaRepository<Exams, Long> {
}
