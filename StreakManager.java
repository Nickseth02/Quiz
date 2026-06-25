package quizgame;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Tracks the player's daily login streak.
 *
 * File format (streak.txt):
 *   Line 1: last played date  (yyyy-MM-dd)
 *   Line 2: current streak    (integer)
 */
public class StreakManager {

    private static final String STREAK_FILE = "data/streak.txt";

    private LocalDate lastPlayedDate;
    private int currentStreak;

    public StreakManager() {
        loadStreak();
    }
