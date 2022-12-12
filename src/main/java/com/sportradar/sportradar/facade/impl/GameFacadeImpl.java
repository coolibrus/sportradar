package com.sportradar.sportradar.facade.impl;

import com.sportradar.sportradar.exception.ExceptionCodeService;
import com.sportradar.sportradar.exception.GameException;
import com.sportradar.sportradar.facade.GameFacade;
import com.sportradar.sportradar.model.Game;
import com.sportradar.sportradar.service.CheckConstraintService;
import com.sportradar.sportradar.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameFacadeImpl implements GameFacade {

    private final GameService gameService;
    private final CheckConstraintService checkConstraintService;

    @Override
    public Game checkAndStart(String homeTeam, String awayTeam) {
        checkConstraintService.checkTeamName(homeTeam);
        checkConstraintService.checkTeamName(awayTeam);
        return gameService.start(homeTeam, awayTeam);
    }

    @Override
    public Game checkAndUpdate(Long gameId, Integer homeTeamScores, Integer awayTeamScores) {
        Game game = gameService.getGameById(gameId).orElseThrow(() -> new GameException(ExceptionCodeService.INVALID_GAME));
        checkConstraintService.checkScores(homeTeamScores, game.getHomeScores());
        checkConstraintService.checkScores(awayTeamScores, game.getAwayScores());
        checkConstraintService.checkGameIsFinished(game);
        return gameService.updateScores(game, homeTeamScores, awayTeamScores);
    }

    @Override
    public Game checkAndFinish(Long gameId) {
        Game game = gameService.getGameById(gameId).orElseThrow(() -> new GameException(ExceptionCodeService.INVALID_GAME));
        checkConstraintService.checkGameIsFinished(game);
        return gameService.finish(game);
    }

    @Override
    public List<String> getActiveGames() {
        return gameService.getActiveGames();
    }
}
