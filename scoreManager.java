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
// ─── Leaderboard ─────────────────────────────────────────────────────────

    private void addToLeaderboard(PlayerRecord record) {
        leaderboard.offer(record);
        // Keep only the top MAX_LEADERBOARD entries
        if (leaderboard.size() > MAX_LEADERBOARD) {
            // Rebuild keeping the best N (PQ is a max-heap already — just trim)
            List<PlayerRecord> all = new ArrayList<>(leaderboard);
            Collections.sort(all);
            leaderboard.clear();
            for (int i = 0; i < Math.min(MAX_LEADERBOARD, all.size()); i++) {
                leaderboard.offer(all.get(i));
            }
        }
        saveLeaderboard();
    }

    /** Returns the leaderboard sorted best-first (copy, safe to mutate). */
    public List<PlayerRecord> getTopScores() {
        List<PlayerRecord> sorted = new ArrayList<>(leaderboard);
        Collections.sort(sorted);
        return sorted;
    }
 public void displayLeaderboard() {
        List<PlayerRecord> top = getTopScores();
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║               🏆  TOP LEADERBOARD  🏆                ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        if (top.isEmpty()) {
            System.out.println("║              No records yet. Play to appear here!    ║");
        } else {
            String[] medals = {"🥇", "🥈", "🥉"};
            for (int i = 0; i < top.size(); i++) {
                String rank = (i < 3) ? medals[i] : " " + (i + 1) + ".";
                System.out.printf("║ %-3s  %s%n", rank, top.get(i));
            }
        }
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }

    private void loadLeaderboard() {
        File f = new File(LEADERBOARD_FILE);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                PlayerRecord r = PlayerRecord.fromCsvLine(line);
                if (r != null) leaderboard.offer(r);
            }
        } catch (IOException e) {
            System.err.println("Warning: could not load leaderboard – " + e.getMessage());
        }
    }

    private void saveLeaderboard() {
        ensureDataDir();
        List<PlayerRecord> sorted = getTopScores();
        try (PrintWriter pw = new PrintWriter(new FileWriter(LEADERBOARD_FILE))) {
            for (PlayerRecord r : sorted) pw.println(r.toCsvLine());
        } catch (IOException e) {
            System.err.println("Warning: could not save leaderboard – " + e.getMessage());
        }
    }	 
 // ─── Grading ─────────────────────────────────────────────────────────────

    /** Returns letter grade based on percentage score. */
    public static String getGrade(int score, int total) {
        if (total == 0) return "N/ public void displayLeaderboard() {
        List<PlayerRecord> top = getTopScores();
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║               🏆  TOP LEADERBOARD  🏆                ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        if (top.isEmpty()) {
            System.out.println("║              No records yet. Play to appear here!    ║");
        } else {
            String[] medals = {"🥇", "🥈", "🥉"};
            for (int i = 0; i < top.size(); i++) {
                String rank = (i < 3) ? medals[i] : " " + (i + 1) + ".";
                System.out.printf("║ %-3s  %s%n", rank, top.get(i));
            }
        }
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }

    private void loadLeaderboard() {
        File f = new File(LEADERBOARD_FILE);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                PlayerRecord r = PlayerRecord.fromCsvLine(line);A";
        double pct = (score * 100.0) / total;
        if (pct >= 90) return "A+";
        if (pct >= 80) return "A";
        if (pct >= 70) return "B";
        if (pct >= 60) return "C";
        if (pct >= 50) return "D";
        return "F";
    }

    private void ensureDataDir() {
        File dir = new File("data");
        if (!dir.exists()) dir.mkdirs();
    }
}
