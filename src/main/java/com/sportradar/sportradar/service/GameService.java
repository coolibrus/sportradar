package com.sportradar.sportradar.service;

import com.sportradar.sportradar.model.Game;
import com.sportradar.sportradar.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public Game start(String homeTeam, String awayTeam) {
        Game game = Game.builder()
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .awayScores(0)
                .homeScores(0)
                .inProgress(true)
                .build();
        return gameRepository.save(game);
    }
}
