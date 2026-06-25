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
// Max-heap: higher score = higher priority
    @Override
    public int compareTo(playerRecord other) {
        return Integer.compare(other.score, this.score);
    }

    /** Serialise to one CSV line for file storage */
    public String toCsvLine() {
        return name + "," + score + "," + date + "," + category;
    }

    /** Parse from a CSV line produced by toCsvLine() */
    public static playerRecord fromCsvLine(String line) {
        String[] parts = line.split(",", 4);
        if (parts.length < 4) return null;
        try {
            return new playerRecord(parts[0], Integer.parseInt(parts[1].trim()), parts[2].trim(), parts[3].trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
   @Override
    public String toString() {
        return String.format("%-15s %4d pts   %-12s  [%s]", name, score, date, category);
    }
}
