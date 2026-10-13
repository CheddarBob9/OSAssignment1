public class Discover implements Runnable {

    private String section;

    public Discover (String section) {
        this.section = section;
    }

    @Override
    public void run() {
        System.out.printf("Discover - Section 3 is: %s%n", section);
    }

}
