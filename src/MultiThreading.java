import java.io.*;

public class MultiThreading {

    static void main(String[] args) throws IOException, InterruptedException {

        // Read file "xyz" in the project folder
        FileReader reader = new FileReader("xyz");

        /* ===== INITIALIZE VARIABLES ===== */
        String section1 = null, section2 = null, section3 = null;
        StringBuilder buildingString = new StringBuilder(); /* CHANGE NAME OF CURRENT */
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

                // If there is whitespace, skip it and restart while loop
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
                String text = buildingString.toString().trim();

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
                buildingString.setLength(0); starCount = 0; inSection = false;
                sectionsFound++;

                /* ===== IF ALL 3 SECTIONS OF A RECORD ARE FOUND MAKE AND SEND THEM TO THREADS ===== */
                if (sectionsFound == 3) {
                    Thread personThread = new Thread(new Person(section1));
                    Thread scoresThread = new Thread(new Scores(section2));
                    Thread discoverThread = new Thread(new Discover(section3));

                    // Start thread
                    personThread.start();
                    scoresThread.start();
                    discoverThread.start();

                    // Join threads to print properly
                    personThread.join();
                    scoresThread.join();
                    discoverThread.join();

                    // Spacing between records
                    System.out.printf("\n ===== RECORD SPACING ===== \n\n");

                    // Reset section Strings and sectionsFound counter
                    section1 = null; section2 = null; section3 = null;
                    sectionsFound = 0;
                } // End if (sectionsFound == 3)

            // Inside section, is whitespace
            } else if (Character.isWhitespace(ch)) {

                // Deal with random whitespace
                if (buildingString.length() > 0 &&
                        buildingString.charAt(buildingString.length() - 1) != ' ') {
                    buildingString.append(' ');
                }

            // Inside section, not end, not whitespace, write the char
            } else {
                buildingString.append(ch);
            } // End if (ch == '$')

        } // End while loop

    } // End main method

} // End class