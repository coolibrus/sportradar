package com.sportradar.sportradar.facade;

import com.sportradar.sportradar.exception.ExceptionCodeService;
import com.sportradar.sportradar.exception.GameException;
import com.sportradar.sportradar.facade.impl.GameFacadeImpl;
import com.sportradar.sportradar.model.Game;
import com.sportradar.sportradar.service.impl.CheckConstraintServiceImpl;
import com.sportradar.sportradar.service.impl.GameServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GameFacadeTest {

    @Mock
    private GameServiceImpl gameService;
    @Spy
    private CheckConstraintServiceImpl checkConstraintService;
    @InjectMocks
    private GameFacadeImpl gameFacade;

    private final String homeTeam = "Real";
    private final String awayTeam = "Barcelona";

    @Test
    public void successfulCheckAndStart() {
        Game game = Game.builder()
                .id(1L)
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .homeScores(0)
                .awayScores(0)
                .inProgress(true)
                .build();
        when(gameService.start(homeTeam, awayTeam)).thenReturn(game);

        Game gameToTest = gameFacade.checkAndStart(homeTeam, awayTeam);
        assertEquals(game.getHomeTeam(), gameToTest.getHomeTeam());
        assertEquals(game.getAwayTeam(), gameToTest.getAwayTeam());
        assertEquals(0, gameToTest.getHomeScores());
        assertEquals(0, gameToTest.getAwayScores());
        assertEquals(true, gameToTest.getInProgress());
    }

    @Test
    public void CheckAndStart_invalidName() {
        Game game = Game.builder()
                .id(1L)
                .homeTeam("")
                .awayTeam(awayTeam)
                .homeScores(0)
                .awayScores(0)
                .inProgress(true)
                .build();
        when(gameService.start(homeTeam, awayTeam)).thenReturn(game);

        Exception exception = assertThrows(GameException.class, () -> gameFacade.checkAndStart("", awayTeam));
        assertEquals(ExceptionCodeService.EMPTY_NAME, exception.getMessage());
    }

    @Test
    public void successfulCheckAndUpdate() {
        Game game = Game.builder()
                .id(1L)
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .homeScores(3)
                .awayScores(2)
                .inProgress(true)
                .build();
        when(gameService.getGameById(1L)).thenReturn(Optional.of(game));
        when(gameService.updateScores(game,3, 2)).thenReturn(game);

        Game gameToTest = gameFacade.checkAndUpdate(1L, 3, 2);
        assertEquals(3, gameToTest.getHomeScores());
        assertEquals(2, gameToTest.getAwayScores());
        assertEquals(true, gameToTest.getInProgress());
    }

    @Test
    public void checkAndUpdate_invalidNegativeScores() {
        Game game = Game.builder()
                .id(1L)
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .homeScores(-3)
                .awayScores(2)
                .inProgress(true)
                .build();
        when(gameService.getGameById(1L)).thenReturn(Optional.of(game));

        Exception exception = assertThrows(GameException.class, () -> gameFacade.checkAndUpdate(1L,-3, 2));
        assertEquals(ExceptionCodeService.NEGATIVE_SCORES, exception.getMessage());
    }

    @Test
    public void checkAndUpdate_invalidSmallerScores() {
        Game game = Game.builder()
                .id(1L)
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .homeScores(3)
                .awayScores(2)
                .inProgress(true)
                .build();
        when(gameService.getGameById(1L)).thenReturn(Optional.of(game));

        Exception exception = assertThrows(GameException.class, () -> gameFacade.checkAndUpdate(1L,2, 2));
        assertEquals(ExceptionCodeService.NEGATIVE_SCORES, exception.getMessage());
    }

    @Test
    public void successfulCheckAndFinish() {
        Game game = Game.builder()
                .id(1L)
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .homeScores(3)
                .awayScores(2)
                .inProgress(true)
                .build();
        Game finishedGame = Game.builder() // this is created to separate the logic of getting the needed game and of setting 'inProgress' to false
                .id(1L)
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .homeScores(3)
                .awayScores(2)
                .inProgress(false)
                .build();
        when(gameService.getGameById(1L)).thenReturn(Optional.of(game));
        when(gameService.finish(game)).thenReturn(finishedGame);

        Game gameToTest = gameFacade.checkAndFinish(1L);
        assertEquals(false, gameToTest.getInProgress());
    }

    @Test
    public void checkAndFinish_invalidGameHasAlreadyFinished() {
        Game game = Game.builder()
                .id(1L)
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .homeScores(3)
                .awayScores(2)
                .inProgress(true)
                .build();
        when(gameService.getGameById(1L)).thenReturn(Optional.of(game));
        game.setInProgress(false);
        when(gameService.finish(game)).thenReturn(game);

        Exception exception = assertThrows(GameException.class, () -> gameFacade.checkAndFinish(1L));
        assertEquals(ExceptionCodeService.FINISHED_GAME, exception.getMessage());
    }
}
