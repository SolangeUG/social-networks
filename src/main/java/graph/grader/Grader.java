package graph.grader;

/**
 * Abstract grader class that includes methods common to concrete graders.
 * @author UCSD MOOC development team
 *
 */
public abstract class Grader implements Runnable {

    public String feedback = "";
    public int correct = 0;
    protected static final int TESTS = 10;

    /**
     * Format output to look nice
     * @param score the grader output score
     * @param feedback the grader output feedback
     * @return a nicely formatted grader output
     */
    static String makeOutput(double score, String feedback) {
        return "Score: " + score + "\nFeedback: " + feedback;
    }


    /**
     * Format test descriptions neatly
     * @param num the test id number
     * @param test the test description
     * @return a neatly formatted description of the test
     */
    static String appendFeedback(int num, String test) {
        return "\n**Test #" + num + ": " + test + "...";
    }

}
