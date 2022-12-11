package com.sportradar.sportradar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="game_seq_gen")
    private Long id;
    private String homeTeam;
    private String awayTeam;
    private Integer homeScores;
    private Integer awayScores;
    private Boolean inProgress;
    private Long startTimeStamp;
}
