package com.sportradar.sportradar.controller;

import com.sportradar.sportradar.facade.GameFacade;
import com.sportradar.sportradar.model.Game;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    private final GameFacade gameFacade;

    @PostMapping("/start")
    public Game startGame(@RequestParam String homeTeam, @RequestParam String awayTeam) {
        return gameFacade.checkAndStart(homeTeam, awayTeam);
    }

    @PatchMapping("/update/{id}")
    public Game updateScores(@PathVariable Long id, @RequestParam Integer homeTeamScores, @RequestParam Integer awayTeamScores) {
        return gameFacade.checkAndUpdate(id, homeTeamScores, awayTeamScores);
    }

    @PatchMapping("/finish/{id}")
    public Game finish(@PathVariable Long id) {
        return gameFacade.checkAndFinish(id);
    }

    @GetMapping("/active")
    public List<String> getActiveGames() {
        return gameFacade.getActiveGames();
    }
}
