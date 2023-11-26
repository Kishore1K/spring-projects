package com.exam.repository;

import com.exam.entity.Exams;
import com.exam.entity.Scores;
import com.exam.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScoresRepo extends JpaRepository<Scores, Long> {
    @Query(" from  Scores  s where s.exams =:exams and s.students=:students")
    Optional<Scores> findByExamsAndStudents(@Param("exams") Exams exams, @Param("students") Students students);

    @Query(value = "select  distinct  *  from Scores where s.exams_id = ?1 and s.students_id = ?2", nativeQuery = true)
    Scores getScores(Long id, Long id1);

//    @Query("update  Scores s set  s.score =:marks where s.exams=:eId and s.students=:sId")
//    Scores updateScore(@Param("score") Integer marks, @Param("eId") Long eId , @Param("sId") Long sId);
}
