package quizgame;

public class playerRecord implements Comparable<playerRecord> {
    private String name;
    private int score;
    private String date;
    private String category;

    public playerRecord(String name, int score, String date, String category) {
        this.name    = name;
        this.score   = score;
        this.date    = date;
        this.category = category;
    }

    public String getName()     { return name; }
    public int getScore()       { return score; }
    public String getDate()     { return date; }
    public String getCategory() { return category; }

