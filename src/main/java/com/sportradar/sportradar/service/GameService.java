package com.sportradar.sportradar.service;

import com.sportradar.sportradar.model.Game;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface GameService {

    /**
     * Starts the game, call the repository to save the game in the DB.
     * @param homeTeam
     * @param awayTeam
     * @return Game entity
     */
    Game start(String homeTeam, String awayTeam);

    /**
     * Updates scores of both teams in the game. Returns the game itself
     * @param game
     * @param homeTeamScores
     * @param awayTeamScores
     * @return Game entity
     */
    Game updateScores(Game game, Integer homeTeamScores, Integer awayTeamScores);

    /**
     * Finishes the game. Returns the game itself
     * @param game
     * @return Game entity
     */
    Game finish(Game game);

    /**
     * @param gameId - Id of the needed game
     * @return Optional<Game>
     */
    Optional<Game> getGameById(Long gameId);

    /**
     * @return The list of all active games with inProgress = true
     */
    List<String> getActiveGames();
}
