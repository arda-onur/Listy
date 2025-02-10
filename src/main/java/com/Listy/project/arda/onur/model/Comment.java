package com.Listy.project.arda.onur.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String comment;

    @Column(name = "author", nullable = false)
    private String author;

    private LocalDate date;

}
