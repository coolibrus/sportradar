package com.sportradar.sportradar.service.impl;

import com.sportradar.sportradar.model.Game;
import com.sportradar.sportradar.repository.GameRepository;
import com.sportradar.sportradar.service.GameService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Override
    public Game start(String homeTeam, String awayTeam) {
        Game game = Game.builder()
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .awayScores(0)
                .homeScores(0)
                .inProgress(true)
                .startTimeStamp(new Date().getTime())
                .build();
        log.info("Game is started for teams: {} and {}.", homeTeam, awayTeam);
        return gameRepository.save(game);
    }

    @Override
    @Transactional
    public Game updateScores(Game game, Integer homeTeamScores, Integer awayTeamScores) {
        log.info("Scores updated for game {} between teams: {} and {}.", game.getId(), homeTeamScores, awayTeamScores);
        game.setHomeScores(homeTeamScores);
        game.setAwayScores(awayTeamScores);
        return gameRepository.save(game);
    }

    @Override
    @Transactional
    public Game finish(Game game) {
        game.setInProgress(false);
        log.info("The game between {} and {} is finished.", game.getHomeTeam(), game.getAwayTeam());
        return gameRepository.save(game);
    }

    @Override
    public Optional<Game> getGameById(Long gameId) {
        return gameRepository.findById(gameId);
    }

    @Override
    public List<String> getActiveGames() {
        return gameRepository.getGamesByInProgress(true);
    }

}
