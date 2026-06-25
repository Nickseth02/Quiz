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
// ─── High Score ──────────────────────────────────────────────────────────

    public int getHighScore() { return highScore; }

    /**
     * Submits a new score.  Returns true if a new high score was set.
     */
    public boolean submitScore(String playerName, int score, String category) {
        boolean newRecord = false;
        if (score > highScore) {
            highScore = score;
            newRecord = true;
            saveHighScore();
        }
        addToLeaderboard(new PlayerRecord(
            playerName, score, LocalDate.now().toString(), category));
        return newRecord;
    }

    private void loadHighScore() {
        File f = new File(HIGH_SCORE_FILE);
        if (!f.exists()) { highScore = 0; return; }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line = br.readLine();
            highScore = (line != null) ? Integer.parseInt(line.trim()) : 0;
           } catch (IOException | NumberFormatException e) {
            highScore = 0;
        }
    }

    private void saveHighScore() {
        ensureDataDir();
        try (PrintWriter pw = new PrintWriter(new FileWriter(HIGH_SCORE_FILE))) {
            pw.println(highScore);
        } catch (IOException e) {
            System.err.println("Warning: could not save high score – " + e.getMessage());
        }
    }
