package quizgame;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
public class scoreManager {

    private static final String HIGH_SCORE_FILE  = "data/highscore.txt";
    private static final String LEADERBOARD_FILE = "data/leaderboard.txt";
    private static final int    MAX_LEADERBOARD  = 10;

    private int highScore;
    private final PriorityQueue<PlayerRecord> leaderboard;

    public scoreManager() {
        leaderboard = new PriorityQueue<>();
        loadHighScore();
        loadLeaderboard();
    }
