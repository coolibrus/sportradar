package com.sportradar.sportradar.service;

import com.sportradar.sportradar.model.Game;
import org.springframework.stereotype.Service;

@Service
public interface CheckConstraintService {
    /**
     * Checks the name on NULL and on empty value. In case of finding anything throws the exception
     * @param teamName
     */
    void checkTeamName(String teamName);

    /**
     * Checks the updatedScore field on NULL, on negative value or on the value less than in previousScores field.
     * In case of finding anything throws the exception
     * @param updatedScores
     * @param previousScores
     */
    void checkScores(Integer updatedScores, Integer previousScores);

    /**
     * Checks if the game is still in progress and if not then throws the exception
     * @param game
     */
    void checkGameIsFinished(Game game);
}
