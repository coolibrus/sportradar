package com.sportradar.sportradar.repository;

import com.sportradar.sportradar.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("select CONCAT(homeTeam, ' ', homeScores, ' - ', awayTeam, ' ', awayScores) " +
            "from Game " +
            "where inProgress = true " +
            "order by homeScores + awayScores, startTimeStamp desc")
    List<String> getGamesByInProgress(boolean inProgress);
}
