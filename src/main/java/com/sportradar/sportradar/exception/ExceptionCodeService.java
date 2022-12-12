package com.sportradar.sportradar.exception;

public interface ExceptionCodeService {
    String EMPTY_NAME = "Team name shouldn't be empty.";
    String NEGATIVE_SCORES = "Team scores shouldn't be negative or less than previous value.";
    String INVALID_GAME = "The game doesn't exist.";
    String FINISHED_GAME = "The game has already finished.";
}
