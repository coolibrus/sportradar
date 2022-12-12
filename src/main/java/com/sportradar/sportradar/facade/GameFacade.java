package com.sportradar.sportradar.facade;

import com.sportradar.sportradar.model.Game;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GameFacade {
    Game checkAndStart(String homeTeam, String awayTeam);
    Game checkAndUpdate(Long gameId, Integer homeTeamScores, Integer awayTeamScores);
    Game checkAndFinish(Long gameId);
    List<String> getActiveGames();
}
