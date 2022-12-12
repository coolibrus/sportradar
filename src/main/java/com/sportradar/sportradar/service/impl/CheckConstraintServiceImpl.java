package com.sportradar.sportradar.service.impl;

import com.sportradar.sportradar.exception.ExceptionCodeService;
import com.sportradar.sportradar.exception.GameException;
import com.sportradar.sportradar.model.Game;
import com.sportradar.sportradar.service.CheckConstraintService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class CheckConstraintServiceImpl implements CheckConstraintService {

    @Override
    public void checkTeamName(String teamName) {
        if (isNull(teamName) || teamName.equals("")) {
            log.error("A team has empty name. " + ExceptionCodeService.EMPTY_NAME);
            throw new GameException(ExceptionCodeService.EMPTY_NAME);
        }
    }

    @Override
    public void checkScores(Integer updatedScores, Integer previousScores) {
        if (isNull(updatedScores) || updatedScores < 0 || updatedScores < previousScores) {
            log.error("A team has negative scores or the value is less than previous. " +
                    ExceptionCodeService.NEGATIVE_SCORES);
            throw new GameException(ExceptionCodeService.NEGATIVE_SCORES);
        }
    }

    @Override
    public void checkGameIsFinished(Game game) {
        if (isNull(game.getInProgress()) || game.getInProgress().equals(false)) {
            log.error("To update scores or to finish the game it should be in progress state. " +
                    ExceptionCodeService.FINISHED_GAME);
            throw new GameException(ExceptionCodeService.FINISHED_GAME);
        }
    }
}
