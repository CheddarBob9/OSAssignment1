public class Person implements Runnable {

    private String section;

    public Person (String section) {
        this.section = section;
    } // End Person constructor

    @Override
    public void run() {

        // Print 40 times
        for (int i = 0; i < 40; i++) {
            System.out.printf("Person - Section 1 is: %s%n", section);
        }

    } // End run method

} // End Person class
