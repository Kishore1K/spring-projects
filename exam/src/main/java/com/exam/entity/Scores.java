package com.exam.entity;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
public class Scores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer score;

    @ManyToOne(cascade = CascadeType.ALL)
    private Users users;

    @ManyToOne(cascade = CascadeType.ALL)
    private Exams exams;

    @Override
    public String toString() {
        return "Scores{" +
                "id=" + id +
                ", score=" + score +
                ", users=" + users +
                ", exams=" + exams +
                '}';
    }
}
