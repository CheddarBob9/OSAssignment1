import java.io.*;

public class MultiThreading {

    static void main(String[] args) {

        // Read file "xyz" in the project folder
        try (FileReader reader = new FileReader("xyz")) {

            /* ===== INITIALIZE VARIABLES ===== */
            String section1 = null, section2 = null, section3 = null;
            StringBuilder current = new StringBuilder(); /* CHANGE NAME OF CURRENT */
            int starCount = 0;
            int sectionsFound = 0;
            boolean inSection = false; // Section or whitespace in between
            int c; // Valid character during read or -1 for end of file

            /* ===== WHILE LOOP TO READ THE FILE ===== */
            while ((c = reader.read()) != -1) {
                char ch = (char) c; // Typecast to the read character

                /* ===== DETERMINE BEGINNING OF SECTION AND COUNT ASTERISKS ===== */
                if (!inSection) {
                    // If asterisk, count it and restart while loop to check for another
                    if (ch == '*') {
                        starCount++;
                        continue;

                    /* I THINK THIS CAN BE DELETED. CHECKS WHITESPACE BETWEEN ASTERISKS AND WE SHOULDN'T HAVE THAT */
                    // If there is whitespace, skip it and check for asterisk or whitespace
                    } else if (Character.isWhitespace(ch)) {
                        continue;

                    // If not asterisk or whitespace, it's in the section
                    } else {
                        inSection = true;
                    }
                } // End if (!inSection)

                /* ===== ACTIONS TO TAKE ONCE READING A SECTION ===== */
                // End of the section, make it a string and trim extra whitespace
                if (ch == '$') {
                    String text = current.toString().trim();

                    // Assign StringBuilder to respective section
                    if (starCount == 1) {
                        section1 = text;

                    } else if (starCount == 2) {
                        section2 = text;

                    } else if (starCount == 3) {
                        section3 = text;

                    // Too many asterisks
                    } else {
                        System.out.println("Error, too many asterisks: " + starCount);
                    }

                    // Reset section counters and increment how many sections have been found
                    current.setLength(0); starCount = 0; inSection = false;
                    sectionsFound++;

                    // If all 3 sections of a record are found, print them
                    if (sectionsFound == 3) {
                        Thread personThread = new Thread(new Person(section1));
                        Thread scoresThread = new Thread(new Scores(section2));
                        Thread discoverThread = new Thread(new Discover(section3));

                        personThread.start();
                        scoresThread.start();
                        discoverThread.start();

                        try {
                            personThread.join();
                            scoresThread.join();
                            discoverThread.join();
                        } catch (InterruptedException e) {
                            System.out.println("Interrupted: " + e.getMessage());
                        }

                        /* Spacing */
                        System.out.println();

                        // Reset section Strings and sectionsFound
                        section1 = null; section2 = null; section3 = null;
                        sectionsFound = 0;
                    } // End if (sectionsFound == 3)

                // Inside section, is whitespace
                } else if (Character.isWhitespace(ch)) {

                    // Deal with random whitespace
                    if (current.length() > 0 && current.charAt(current.length() - 1) != ' ') {
                        current.append(' ');
                    }

                // Inside section, not end, not whitespace, write the char
                } else {
                    current.append(ch);
                } // End if (ch == '$')

            } // End while loop

        // Bad file error message
        } catch (IOException e) {

            System.out.println("Bad file: " + e.getMessage());

        } // End try catch

    } // End main method

} // End class