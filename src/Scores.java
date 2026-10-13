public class Scores implements Runnable {

    private String section;

    public Scores (String section) {
        this.section = section;
    } // End Scores constructor

    @Override
    public void run() {

        // Print 60 times
        for (int i = 0; i <60; i++) {
            System.out.printf("Scores - Section 2 is: %s%n", section);
        }

    } // End run method

} // End Scores class
