package com.sportradar.sportradar.service;

import com.sportradar.sportradar.model.Game;
import org.springframework.stereotype.Service;

@Service
public interface CheckConstraintService {
    void checkTeamName(String teamName);
    void checkScores(Integer updatedScore, Integer previousScores);
    void checkGameIsFinished(Game game);
}
