public class Person implements Runnable {

    private String section;

    public Person (String section) {
        this.section = section;
    }

    @Override
    public void run() {
        for (int i = 0; i < 40; i++) {
            System.out.printf("Person - Section 1 is: %s%n", section);
        }
    }

}
