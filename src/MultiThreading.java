import java.io.FileReader;
import java.io.IOException;

public class MultiThreading {

    static void main(String[] args) {

        try (FileReader reader = new FileReader("xyz")) {
            int c;
            while ((c = reader.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            System.out.printf("Error reading file: %s%n", e.getMessage());
        }

        // Initialize vars to parse sections of the string
        String trim1 = "1", trim2 = "2", trim3 = "3";

        System.out.printf("Trim1: %s%n", trim1);
        System.out.printf("Trim2: %s%n", trim2);
        System.out.printf("Trim3: %s%n", trim3);

    }

}
