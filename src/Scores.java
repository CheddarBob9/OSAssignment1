public class Scores implements Runnable {

    private String section;

    public Scores (String section) {
        this.section = section;
    }

    @Override
    public void run() {
        System.out.printf("Scores - Section 2 is: %s%n", section);
    }

}
