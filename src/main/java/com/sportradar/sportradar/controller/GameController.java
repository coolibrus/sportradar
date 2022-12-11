package com.sportradar.sportradar.controller;

import com.sportradar.sportradar.model.Game;
import com.sportradar.sportradar.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping("/start")
    public Game startGame(@RequestParam String homeTeam, @RequestParam String awayTeam) {
        log.info("Game is started for teams: {} and {}.", homeTeam, awayTeam);
        return gameService.start(homeTeam, awayTeam);
    }
}
