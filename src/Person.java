import java.time.LocalDate;
import java.time.Period;

public class Person implements Runnable {
    private String section;

    public Person(String section) {
        this.section = section;
    } // End Person constructor

    @Override
    public void run() {

        // Initialize variables
        String firstName = "Unknown";
        String lastName = "Unknown";
        String middleInitial = "Unknown";
        String date = null;
        String age = "Unknown";

        // Split the section into words
        String[] words = section.replace(", ", ",").split(" ");

        // Find each word by its prefix letter
        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            // If there's just prefix with nothing else restart for loop
            if (word.length() < 2) {
                continue;
            }

            // Initialize variables for the prefix
            char prefix = word.charAt(0);
            String value = word.substring(1);

            // Determine the value of the prefix
            if (prefix == 'F') {
                firstName = value;

            } else if (prefix == 'L') {
                lastName = value;

            } else if (prefix == 'M') {
                middleInitial = value;

            } else if (prefix == 'D') {
                date = value;
            }

        } // End for loop

        /* =====  CALCULATIONS FOR THE DATE ===== */
        if (date != null) {

            // Split the date into parts
            String[] dateParts = date.split(",");

            // If we have a complete date
            if (dateParts.length == 3) {

                // Assign the array elements to their variables using helper methods for conversion
                int day = toNumber(dateParts[0].trim());
                int month = toMonth(dateParts[1].trim());
                int year = toNumber(dateParts[2].trim());

                // Actual age calculation
                try {
                    LocalDate birthDate = LocalDate.of(year, month, day);
                    Period p = Period.between(birthDate, LocalDate.now());
                    age = p.getYears() + "y, " + p.getMonths() + "m and " + p.getDays() + "d";

                // Incomplete date data
                } catch (Exception e) {
                    age = "Unknown";
                }
            } // End if (dateParts.length == 3)

        } // End if (date != null)

        // Build the required printout
        String line = "Person - Section 1 is: " + section
                + " - Last Name: " + lastName
                + " - First Name: " + firstName
                + " - MI: " + middleInitial
                + " - Age: " + age;

        // Print 40 times
        for (int i = 0; i < 1; i++) {
            System.out.println(line);
        }
    }

    // Written number to digit number conversion method
    private static int toNumber(String number) {

        // Check for a digit number first
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            // Written numbers not digit numbers so continue with program
        }

        // Array of written numbers for each possible day of a month
        String[] writtenNumbers = {
                "zero", "one", "two", "three", "four", "five", "six", "seven",
                "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen",
                "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty",
                "twenty-one", "twenty-two", "twenty-three", "twenty-four",
                "twenty-five", "twenty-six", "twenty-seven", "twenty-eight",
                "twenty-nine", "thirty", "thirty-one"
        };

        // Search the array for the matching written number
        for (int i = 0; i < writtenNumbers.length; i++) {

            // If match return the index
            if (number.equalsIgnoreCase(writtenNumbers[i])) {
                return i;
            }

        } // End for loop

        // No match returns -1
        return -1;

    } // End toNumber method

    // Written month to digit month conversion method
    private static int toMonth(String month) {

        // Check if the month is already in digit format
        int number = toNumber(month);
        if (number != -1) {
            return number;
        }

        // Array with month abbreviations
        String[] monthAbbreviations = {"jan", "feb", "mar", "apr", "may", "jun",
                "jul", "aug", "sep", "oct", "nov", "dec"};

        // Search monthAbbreviations with the beginning of the target month
        for (int i = 0; i < monthAbbreviations.length; i++) {

            // Check the beginning of the passed month with the array
            if (month.toLowerCase().startsWith(monthAbbreviations[i])) {
                return i + 1;
            }

        } // End for loop

        return -1;

    } // End toMonth method

} // End Person class