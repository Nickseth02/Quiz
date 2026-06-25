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
    public int getCurrentStreak() { return currentStreak; }

    /**
     * Call once per game session (after a quiz is completed).
     * Updates the streak and saves to disk.
     */
    public void recordPlayedToday() {
        LocalDate today = LocalDate.now();

        if (lastPlayedDate == null) {
            // First time ever
            currentStreak = 1;
        } else {
            long daysBetween = ChronoUnit.DAYS.between(lastPlayedDate, today);
            if (daysBetween == 0) {
                // Already played today — no change
                return;
            } else if (daysBetween == 1) {
                // Consecutive day
                currentStreak++;
            } else {
                // Streak broken
                currentStreak = 1;
            }
