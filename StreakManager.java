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
    }

        lastPlayedDate = today;
        saveStreak();
    }

    public void displayStreak() {
        System.out.println("\n🔥 Current Streak: " + currentStreak + " day"
            + (currentStreak == 1 ? "" : "s") + " 🔥");

        if (lastPlayedDate != null) {
            System.out.println("   Last played: " + lastPlayedDate);
        }

        if (currentStreak >= 7) {
            System.out.println("   🌟 Week-long warrior! Keep it up!");
        } else if (currentStreak >= 3) {
            System.out.println("   ⚡ On a roll! Don't break the chain!");
        } else if (currentStreak == 1) {
            System.out.println("   👋 Welcome! Play tomorrow to build a streak.");
        }
    }

    private void loadStreak() {
        File f = new File(STREAK_FILE);
        if (!f.exists()) {
            lastPlayedDate = null;
            currentStreak  = 0;
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String dateLine   = br.readLine();
            String streakLine = br.readLine();
            lastPlayedDate = (dateLine   != null) ? LocalDate.parse(dateLine.trim()) : null;
            currentStreak  = (streakLine != null) ? Integer.parseInt(streakLine.trim()) : 0;
        } catch (Exception e) {
            lastPlayedDate = null;
            currentStreak  = 0;
        }
    }
