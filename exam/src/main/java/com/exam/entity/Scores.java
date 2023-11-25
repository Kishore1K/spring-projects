package com.exam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Scores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer score;

    @ManyToOne(cascade = CascadeType.ALL)
    private Students students;

    @ManyToOne(cascade = CascadeType.ALL)
    private Exams exams;

    @Override
    public String toString() {
        return "Scores{" +
                "id=" + id +
                ", score=" + score +
                ", student=" + students +
                ", exams=" + exams +
                '}';
    }
}
