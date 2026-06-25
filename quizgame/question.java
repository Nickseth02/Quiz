package quizgame;

public class question {
    private String question;
    private String[] options;
    private int answer; // 1-indexed correct answer
    private String hint;
    private String category;

    public question(String question, String[] options, int answer, String hint, String category) {
        this.question = question;
        this.options = options;
        this.answer = answer;
        this.hint = hint;
        this.category = category;
    }

    public question(String question, String[] options, int answer, String category) {
        this(question, options, answer, "No hint available.", category);
    }

    public String getQuestion()   { return question; }
    public String[] getOptions()  { return options; }
    public int getAnswer()        { return answer; }
    public String getHint()       { return hint; }
    public String getCategory()   { return category; }

    public boolean isCorrect(int userAnswer) {
        return userAnswer == answer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(question).append("\n");
        for (int i = 0; i < options.length; i++) {
            sb.append("  ").append(i + 1).append(". ").append(options[i]).append("\n");
        }
        return sb.toString();
    }
}