public class Discover implements Runnable {

    private String section;

    public Discover (String section) {
        this.section = section;
    } // End Discover constructor

    @Override
    public void run() {

        // Print 50 times
        for (int i = 0; i < 50; i++) {
            System.out.printf("Discover - Section 3 is: %s%n", section);
        }

    } // End run method

} // End Discover class
