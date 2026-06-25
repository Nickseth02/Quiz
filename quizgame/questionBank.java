package quizgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Central repository of all quiz questions, grouped by category.
 * Add or remove questions here without touching any other class.
 */
public class questionBank {

    public static final String CAT_JAVA    = "Java";
    public static final String CAT_GK      = "General Knowledge";
    public static final String CAT_MOVIES  = "Movies";
    public static final String CAT_SPORTS  = "Sports";

    public static final String[] CATEGORIES = {CAT_JAVA, CAT_GK, CAT_MOVIES, CAT_SPORTS};

    // ─── Java ────────────────────────────────────────────────────────────────

    private static List<question> javaQuestions() {
        List<question> q = new ArrayList<>();
        q.add(new question(
            "Which keyword is used to inherit a class in Java?",
            new String[]{"implements", "extends", "inherits", "super"},
            2, "It also means 'reaches out to'.", CAT_JAVA));
        q.add(new question(
            "What is the default value of an int variable in Java?",
            new String[]{"null", "1", "0", "undefined"},
            3, "Think of an empty number line.", CAT_JAVA));
        q.add(new question(
            "Which collection allows duplicate elements and maintains insertion order?",
            new String[]{"HashSet", "TreeSet", "ArrayList", "HashMap"},
            3, "It's the most commonly used List implementation.", CAT_JAVA));
        q.add(new question(
            "What does JVM stand for?",
            new String[]{"Java Virtual Machine", "Java Variable Method", "Java Verified Module", "Joint Virtual Manager"},
            1, "It's what makes Java platform-independent.", CAT_JAVA));
        q.add(new question(
            "Which of these is NOT a primitive type in Java?",
            new String[]{"int", "boolean", "String", "char"},
            3, "Primitives start with a lowercase letter.", CAT_JAVA));
        q.add(new question(
            "What is the size of a Java int in bytes?",
            new String[]{"2", "4", "8", "depends on platform"},
            2, "Count: short=2, int=?, long=8.", CAT_JAVA));
        q.add(new question(
            "Which access modifier makes a member accessible only within its own class?",
            new String[]{"public", "protected", "default", "private"},
            4, "The most restrictive modifier.", CAT_JAVA));
        q.add(new question(
            "Which Java keyword prevents a variable from being reassigned?",
            new String[]{"static", "final", "const", "immutable"},
            2, "It also prevents method overriding when applied to methods.", CAT_JAVA));
        q.add(new question(
            "What interface must a class implement to be sortable by Collections.sort()?",
            new String[]{"Sortable", "Orderable", "Comparable", "Comparator"},
            3, "Think: can it compare itself?", CAT_JAVA));
        q.add(new question(
            "What is the output of: System.out.println(10 / 3)?",
            new String[]{"3.33", "3", "3.0", "Compile error"},
            2, "Integer division truncates the decimal part.", CAT_JAVA));
        return q;
    }

    // ─── General Knowledge ────────────────────────────────────────────────────

    private static List<question> gkQuestions() {
        List<question> q = new ArrayList<>();
        q.add(new question(
            "What is the capital of India?",
            new String[]{"Mumbai", "Delhi", "Chennai", "Kolkata"},
            2, "It houses the Red Fort.", CAT_GK));
        q.add(new question(
            "How many planets are in our solar system?",
            new String[]{"7", "8", "9", "10"},
            2, "Pluto was reclassified in 2006.", CAT_GK));
        q.add(new question(
            "Which is the largest ocean on Earth?",
            new String[]{"Atlantic", "Indian", "Arctic", "Pacific"},
            4, "It covers more than 30% of Earth's surface.", CAT_GK));
        q.add(new question(
            "Who wrote the play 'Romeo and Juliet'?",
            new String[]{"Charles Dickens", "Leo Tolstoy", "William Shakespeare", "Mark Twain"},
            3, "He's often called the Bard of Avon.", CAT_GK));
        q.add(new question(
            "What is the chemical symbol for Gold?",
            new String[]{"Go", "Gd", "Au", "Ag"},
            3, "From the Latin word 'Aurum'.", CAT_GK));
        q.add(new question(
            "Which country has the largest population in the world?",
            new String[]{"USA", "India", "China", "Russia"},
            2, "It overtook its neighbour in 2023.", CAT_GK));
        q.add(new question(
            "How many sides does a hexagon have?",
            new String[]{"5", "6", "7", "8"},
            2, "'Hexa' is Greek for this number.", CAT_GK));
        q.add(new question(
            "Which gas do plants absorb during photosynthesis?",
            new String[]{"Oxygen", "Nitrogen", "Carbon Dioxide", "Hydrogen"},
            3, "Humans exhale it; plants love it.", CAT_GK));
        q.add(new question(
            "What is the longest river in the world?",
            new String[]{"Amazon", "Yangtze", "Mississippi", "Nile"},
            4, "It flows through Africa into the Mediterranean.", CAT_GK));
        q.add(new question(
            "In which year did the first moon landing occur?",
            new String[]{"1965", "1967", "1969", "1971"},
            3, "Neil Armstrong said 'one small step...'", CAT_GK));
        return q;
    }

    // ─── Movies ──────────────────────────────────────────────────────────────

    private static List<question> movieQuestions() {
        List<question> q = new ArrayList<>();
        q.add(new question(
            "Which film won the first Academy Award for Best Picture?",
            new String[]{"Citizen Kane", "Wings", "Gone with the Wind", "Casablanca"},
            2, "It was a silent war film.", CAT_MOVIES));
        q.add(new question(
            "Who directed the movie 'Inception' (2010)?",
            new String[]{"Steven Spielberg", "James Cameron", "Christopher Nolan", "Ridley Scott"},
            3, "He also directed The Dark Knight trilogy.", CAT_MOVIES));
        q.add(new question(
            "Which movie features the quote 'To infinity and beyond!'?",
            new String[]{"WALL-E", "Toy Story", "Up", "Finding Nemo"},
            2, "Buzz Lightyear says it.", CAT_MOVIES));
        q.add(new question(
            "What is the highest-grossing movie of all time (unadjusted)?",
            new String[]{"Titanic", "Avengers: Endgame", "Avatar", "The Lion King"},
            3, "It was released in 2009 and re-released in 2022.", CAT_MOVIES));
        q.add(new question(
            "In 'The Godfather', what animal's head is found in the bed?",
            new String[]{"Dog", "Cat", "Horse", "Pig"},
            3, "It's an offer you can't refuse.", CAT_MOVIES));
        q.add(new question(
            "Which actor played Iron Man in the Marvel Cinematic Universe?",
            new String[]{"Chris Evans", "Chris Hemsworth", "Robert Downey Jr.", "Mark Ruffalo"},
            3, "He said 'I am Iron Man' twice — once to end it all.", CAT_MOVIES));
        q.add(new question(
            "Which country produces the most films annually?",
            new String[]{"USA", "China", "India", "Japan"},
            3, "Bollywood is just one of its film industries.", CAT_MOVIES));
        q.add(new question(
            "What year was the first Harry Potter film released?",
            new String[]{"1999", "2000", "2001", "2002"},
            3, "Harry started his journey at Hogwarts that year.", CAT_MOVIES));
        q.add(new question(
            "Which film features the song 'Let It Go'?",
            new String[]{"Tangled", "Moana", "Brave", "Frozen"},
            4, "Set in the kingdom of Arendelle.", CAT_MOVIES));
        q.add(new question(
            "'You talking to me?' is a famous line from which movie?",
            new String[]{"Scarface", "Goodfellas", "Taxi Driver", "The Godfather"},
            3, "Robert De Niro delivers it in front of a mirror.", CAT_MOVIES));
        return q;
    }

    // ─── Sports ──────────────────────────────────────────────────────────────

    private static List<question> sportsQuestions() {
        List<question> q = new ArrayList<>();
        q.add(new question(
            "How many players are there in a cricket team?",
            new String[]{"9", "10", "11", "12"},
            3, "Same as in a football team.", CAT_SPORTS));
        q.add(new question(
            "Which country has won the most FIFA World Cup titles?",
            new String[]{"Germany", "Argentina", "Brazil", "Italy"},
            3, "They have a record five titles.", CAT_SPORTS));
        q.add(new question(
            "In which sport would you perform a 'slam dunk'?",
            new String[]{"Volleyball", "Basketball", "Handball", "Water Polo"},
            2, "Michael Jordan was famous for it.", CAT_SPORTS));
        q.add(new question(
            "How many Grand Slam tournaments are played in tennis each year?",
            new String[]{"2", "3", "4", "5"},
            3, "Australian Open, French Open, Wimbledon, US Open.", CAT_SPORTS));
        q.add(new question(
            "What is the national sport of Canada?",
            new String[]{"Baseball", "Ice Hockey", "Lacrosse (summer)", "Both Ice Hockey and Lacrosse"},
            4, "Officially it has two national sports.", CAT_SPORTS));
        q.add(new question(
            "Which athlete has won the most Olympic gold medals?",
            new String[]{"Usain Bolt", "Carl Lewis", "Michael Phelps", "Larisa Latynina"},
            3, "A swimmer who dominated multiple Olympics.", CAT_SPORTS));
        q.add(new question(
            "In football (soccer), how many minutes is a standard game?",
            new String[]{"80", "90", "100", "120"},
            2, "Plus stoppage time added by the referee.", CAT_SPORTS));
        q.add(new question(
            "What color jersey does the leader wear in the Tour de France?",
            new String[]{"Red", "Green", "White", "Yellow"},
            4, "Named after the newspaper that originally sponsored the race.", CAT_SPORTS));
        q.add(new question(
            "How many rings are on the Olympic flag?",
            new String[]{"4", "5", "6", "7"},
            2, "Each ring represents a continent.", CAT_SPORTS));
        q.add(new question(
            "Which Indian cricketer is known as the 'God of Cricket'?",
            new String[]{"MS Dhoni", "Virat Kohli", "Kapil Dev", "Sachin Tendulkar"},
            4, "He scored 100 international centuries.", CAT_SPORTS));
        return q;
    }

    // ─── Public API ──────────────────────────────────────────────────────────

    /** Returns a shuffled copy of questions for the given category. */
    public static List<question> getQuestions(String category) {
        List<question> list;
        switch (category) {
            case CAT_JAVA:   list = javaQuestions();   break;
            case CAT_GK:     list = gkQuestions();     break;
            case CAT_MOVIES: list = movieQuestions();  break;
            case CAT_SPORTS: list = sportsQuestions(); break;
            default:         list = new ArrayList<>(); break;
        }
        Collections.shuffle(list);
        return list;
    }
}
