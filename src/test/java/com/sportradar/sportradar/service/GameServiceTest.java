package com.sportradar.sportradar.service;

import com.sportradar.sportradar.model.Game;
import com.sportradar.sportradar.repository.GameRepository;
import com.sportradar.sportradar.service.impl.GameServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;
    @InjectMocks
    private GameServiceImpl gameService;

    private final String homeTeam = "Real";
    private final String awayTeam = "Barcelona";

    @Test
    public void startGameTest() {
        Game game = Game.builder()
                .id(1L)
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .homeScores(0)
                .awayScores(0)
                .inProgress(true)
                .build();
        when(gameRepository.save(any())).thenReturn(game);

        Game gameToTest = gameService.start(homeTeam, awayTeam);
        assertEquals(game.getHomeTeam(), gameToTest.getHomeTeam());
        assertEquals(game.getAwayTeam(), gameToTest.getAwayTeam());
        assertEquals(0, gameToTest.getHomeScores());
        assertEquals(0, gameToTest.getAwayScores());
        assertEquals(true, gameToTest.getInProgress());
    }

    @Test
    public void updateScoresTest() {
        Game game = Game.builder()
                .id(1L)
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .homeScores(3)
                .awayScores(2)
                .inProgress(true)
                .build();
        when(gameRepository.save(any())).thenReturn(game);

        Game gameToTest = gameService.updateScores(game, 3, 2);
        assertEquals(3, gameToTest.getHomeScores());
        assertEquals(2, gameToTest.getAwayScores());
        assertEquals(true, gameToTest.getInProgress());
    }

    @Test
    public void finishGameTest() {
        Game game = Game.builder()
                .id(1L)
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .homeScores(3)
                .awayScores(2)
                .inProgress(true)
                .build();
        when(gameRepository.save(any())).thenReturn(game);

        Game gameToTest = gameService.finish(game);
        assertEquals(false, gameToTest.getInProgress());
    }

}
