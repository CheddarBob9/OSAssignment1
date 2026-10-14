public class Discover implements Runnable {
    private String section;

    public Discover(String section) {
        this.section = section;
    } // End Discover constructor

    @Override
    public void run() {

        // Array of written numbers
        String[] writtenNumber = {"zero", "one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine"};

        String[] sectionWords = section.split(" ");

        // Parse and go through section
        for (int i = 0; i < sectionWords.length; i++) {

            String parsedSection = sectionWords[i].replace(",", "");

            // Find and replace matching written numbers with digit numbers
            for (int j = 0; j < writtenNumber.length; j++) {

                if (parsedSection.equalsIgnoreCase(writtenNumber[j])) {
                    sectionWords[i] = "" + j;
                }

            } // End nested for loop

        } // End outer for loop

        /* ===== FIND THE FIRST CHARACTER FOR THE PASSWORD ===== */
        String password = "";

        // Go through sectionWords for the first letter of qualifying words
        for (int i = 0; i < sectionWords.length; i++) {

            // If the index is empty skip it /* MIGHT BE ABLE TO DELETE THIS SECTION */
            if (sectionWords[i].length() == 0) {
                continue;
            }

            // Look at the first letter of each word for a lowercase or digit and add to password
            char firstLetter = sectionWords[i].charAt(0);

            if ((firstLetter >= 'a' && firstLetter <= 'z') ||
                    (firstLetter >= '0' && firstLetter <= '9')) {
                password = password + firstLetter;
            }
        }

        /* ===== CHECK PASSWORD VALIDITY OF FIRST 6 THEN LAST 6 ===== */
        String sixCharPassword = password;
        String status = "invalid";

        // Make 6 character substrings of the password if needed
        if (password.length() >= 6) {
            String firstSix = password.substring(0, 6);
            String lastSix = password.substring(password.length() - 6);

            // Check if the first 6 characters are valid
            if (isValid(firstSix)) {
                sixCharPassword = firstSix;
                status = "valid";

            // Check if the second 6 characters are valid
            } else if (isValid(lastSix)) {
                sixCharPassword = lastSix;
                status = "valid";
            }

        } // End if (password.lenth() >= 6)

        // Build the required printout
        String line = "Discover - Section 3 is: " + section
                + " - passwd is: " + sixCharPassword + " - " + status;

        // Print 50 times
        for (int i = 0; i < 1; i++) {
            System.out.println(line);
        }

    } // End run method

    // Password validity method
    private static boolean isValid(String p) {

        // If password is too short return false
        if (p.length() != 6) {
            return false;
        }

        // If the password does not start with a lowercase letter return false
        char first = p.charAt(0);
        if (first < 'a' || first > 'z') {
            return false;
        }

        // Search the password for a digit number and return true
        for (int i = 0; i < p.length(); i++) {

            char ch = p.charAt(i);
            if (ch >= '0' && ch <= '9') {
                return true;
            }

        } // End for loop

        // Any other password disqualifications return false
        return false;

    } // End isValid method

} // End Discover class