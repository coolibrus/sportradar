package com.sportradar.sportradar.service;

import com.sportradar.sportradar.model.Game;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface GameService {
    Game start(String homeTeam, String awayTeam);
    Game updateScores(Game game, Integer homeTeamScores, Integer awayTeamScores);
    Game finish(Game game);
    Optional<Game> getGameById(Long gameId);
    List<String> getActiveGames();
}
