public class Discover implements Runnable {

    private String section;

    public Discover (String section) {
        this.section = section;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.printf("Discover - Section 3 is: %s%n", section);
        }
    }

}
