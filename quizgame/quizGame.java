package quizgame;

import java.util.*;

/**
 * Entry point for the Quiz Game.
 *
 * Responsibilities:
 *  - Main menu
 *  - Category selection
 *  - Game loop with lifelines (50-50, skip, hint)
 *  - Score calculation and display
 *  - Delegates persistence to ScoreManager and StreakManager
 */
public class quizGame {

    // ─── Constants ───────────────────────────────────────────────────────────

    private static final int POINTS_PER_CORRECT = 10;
    private static final int MAX_QUESTIONS       = 10;

    // ─── Dependencies ────────────────────────────────────────────────────────

    private final Scanner      scanner;
    private final scoreManager scoreManager;
    private final streakManager streakManager;

    // ─── Constructor ─────────────────────────────────────────────────────────

    public quizGame() {
        scanner      = new Scanner(System.in);
        scoreManager = new scoreManager();
        streakManager = new streakManager();
    }

    // ─── Main ────────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        new quizGame().run();
    }

    private void run() {
        printBanner();
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Choose an option: ", 1, 6);
            switch (choice) {
                case 1: startQuiz();                      break;
                case 2: showHighScore();                  break;
                case 3: streakManager.displayStreak();    break;
                case 4: scoreManager.displayLeaderboard(); break;
                case 5: showInstructions();               break;
                case 6: running = false;                  break;
            }
        }
        System.out.println("\nThanks for playing! See you tomorrow 👋\n");
        scanner.close();
    }

    // ─── Menu Displays ───────────────────────────────────────────────────────

    private void printBanner() {
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║         🎯  QUIZ GAME  🎯         ║");
        System.out.println("╚══════════════════════════════════╝");
    }

    private void printMainMenu() {
        System.out.println("\n════════════ MAIN MENU ════════════");
        System.out.println("  1. Start Quiz");
        System.out.println("  2. View High Score");
        System.out.println("  3. View Daily Streak");
        System.out.println("  4. View Leaderboard");
        System.out.println("  5. How to Play");
        System.out.println("  6. Exit");
        System.out.println("═══════════════════════════════════");
    }

    private void showHighScore() {
        System.out.println("\n🏅 All-time High Score: " + scoreManager.getHighScore() + " pts");
    }

    private void showInstructions() {
        System.out.println("\n── How to Play ──────────────────────────────────");
        System.out.println("  • Answer each multiple-choice question (1–4).");
        System.out.println("  • Correct answer  → +" + POINTS_PER_CORRECT + " points.");
        System.out.println("  • Wrong answer    → 0 points, correct answer shown.");
        System.out.println("\n  Lifelines (one of each per quiz):");
        System.out.println("  • Type 'h' – Show a hint for the current question.");
        System.out.println("  • Type 'f' – 50-50: removes two wrong options.");
        System.out.println("  • Type 's' – Skip this question (no points, no penalty).");
        System.out.println("─────────────────────────────────────────────────");
    }

    // ─── Quiz Flow ───────────────────────────────────────────────────────────

    private void startQuiz() {
        System.out.print("\nEnter your name: ");
        String playerName = scanner.nextLine().trim();
        if (playerName.isEmpty()) playerName = "Anonymous";

        String category = chooseCategory();
        List<question> questions = questionBank.getQuestions(category);

        // Limit to MAX_QUESTIONS
        if (questions.size() > MAX_QUESTIONS) {
            questions = questions.subList(0, MAX_QUESTIONS);
        }

        int totalQuestions = questions.size();
        int score          = 0;

        // Lifeline flags
        boolean hintAvailable  = true;
        boolean fiftyAvailable = true;
        boolean skipAvailable  = true;

        System.out.println("\n─────────────────────────────────────────────────");
        System.out.println("  Category : " + category);
        System.out.println("  Questions: " + totalQuestions);
        System.out.println("  Lifelines: [H]int  [F]ifty-Fifty  [S]kip");
        System.out.println("─────────────────────────────────────────────────\n");

        for (int i = 0; i < totalQuestions; i++) {
            question q = questions.get(i);

            System.out.println("Question " + (i + 1) + " of " + totalQuestions
                + "  |  Score: " + score + " pts");
            System.out.println();
            System.out.println(q);

            // Determine which options to display (for 50-50)
            boolean[] hidden = new boolean[4]; // true = this option is hidden by 50-50

            boolean answered = false;
            while (!answered) {
                System.out.print("Your answer (1-4");
                if (hintAvailable)  System.out.print(" | H=hint");
                if (fiftyAvailable) System.out.print(" | F=50-50");
                if (skipAvailable)  System.out.print(" | S=skip");
                System.out.print("): ");

                String input = scanner.nextLine().trim().toLowerCase();

                switch (input) {
                    // ── Lifelines ──────────────────────────────────────────
                    case "h":
                        if (!hintAvailable) {
                            System.out.println("  ⚠  Hint already used this quiz.");
                        } else {
                            System.out.println("  💡 Hint: " + q.getHint());
                            hintAvailable = false;
                        }
                        break;

                    case "f":
                        if (!fiftyAvailable) {
                            System.out.println("  ⚠  50-50 already used this quiz.");
                        } else {
                            hidden = applyFiftyFifty(q);
                            fiftyAvailable = false;
                            // Reprint question with 2 options hidden
                            System.out.println();
                            System.out.println(q.getQuestion());
                            String[] opts = q.getOptions();
                            for (int o = 0; o < opts.length; o++) {
                                if (!hidden[o]) {
                                    System.out.println("  " + (o + 1) + ". " + opts[o]);
                                } else {
                                    System.out.println("  " + (o + 1) + ". ──────");
                                }
                            }
                            System.out.println();
                        }
                        break;

                    case "s":
                        if (!skipAvailable) {
                            System.out.println("  ⚠  Skip already used this quiz.");
                        } else {
                            System.out.println("  ⏭  Question skipped.");
                            skipAvailable = false;
                            answered = true;
                        }
                        break;

                    // ── Numeric answer ─────────────────────────────────────
                    default:
                        try {
                            int userAnswer = Integer.parseInt(input);
                            if (userAnswer < 1 || userAnswer > 4) {
                                System.out.println("  ⚠  Please enter a number between 1 and 4.");
                            } else if (hidden[userAnswer - 1]) {
                                System.out.println("  ⚠  That option was removed by 50-50. Try another.");
                            } else {
                                if (q.isCorrect(userAnswer)) {
                                    score += POINTS_PER_CORRECT;
                                    System.out.println("  ✅ Correct! +" + POINTS_PER_CORRECT + " points\n");
                                } else {
                                    System.out.println("  ❌ Wrong! The correct answer was: "
                                        + q.getAnswer() + ". " + q.getOptions()[q.getAnswer() - 1] + "\n");
                                }
                                answered = true;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("  ⚠  Invalid input. Enter a number (1-4) or a lifeline key.");
                        }
                        break;
                }
            }
        }

        // ── End of quiz ───────────────────────────────────────────────────
        showFinalResult(playerName, score, totalQuestions, category);
    }

    /** Randomly hides two wrong options. Returns boolean[4] where true = hidden. */
    private boolean[] applyFiftyFifty(question q) {
        boolean[] hidden = new boolean[4];
        List<Integer> wrongIndices = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if ((i + 1) != q.getAnswer()) wrongIndices.add(i);
        }
        Collections.shuffle(wrongIndices);
        hidden[wrongIndices.get(0)] = true;
        hidden[wrongIndices.get(1)] = true;
        return hidden;
    }

    private void showFinalResult(String name, int score, int total, String category) {
        int maxScore = total * POINTS_PER_CORRECT;
        String grade = scoreManager.getGrade(score, maxScore);
        boolean newRecord = scoreManager.submitScore(name, score, category);
        streakManager.recordPlayedToday();

        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║           📋 QUIZ OVER!           ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.printf( "║  Player : %-23s║%n", name);
        System.out.printf( "║  Score  : %-3d / %-3d pts           ║%n", score, maxScore);
        System.out.printf( "║  Grade  : %-23s║%n", grade);
        System.out.printf( "║  Category: %-22s║%n", category);
        if (newRecord) {
            System.out.println("║  🏆 NEW HIGH SCORE ACHIEVED!     ║");
        }
        System.out.println("╚══════════════════════════════════╝");

        streakManager.displayStreak();
    }

    // ─── Category Selection ──────────────────────────────────────────────────

    private String chooseCategory() {
        System.out.println("\n── Select a Category ─────────────────────────────");
        String[] cats = questionBank.CATEGORIES;
        for (int i = 0; i < cats.length; i++) {
            System.out.println("  " + (i + 1) + ". " + cats[i]);
        }
        int choice = readInt("Enter category number: ", 1, cats.length);
        return cats[choice - 1];
    }

    // ─── Input Validation ────────────────────────────────────────────────────

    /**
     * Reads and returns an integer in [min, max].
     * Keeps prompting until the user enters valid input.
     */
    private int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= min && value <= max) return value;
                System.out.println("  ⚠  Please enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("  ⚠  Invalid input. Please enter a number.");
            }
        }
    }
}
