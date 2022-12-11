package com.sportradar.sportradar.repository;

import com.sportradar.sportradar.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
}
