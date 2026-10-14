public class Scores implements Runnable {

    private String section;

    public Scores (String section) {
        this.section = section;
    } // End Scores constructor

    @Override
    public void run() {

        /* ===== PARSE THE SCORES ===== */
        // Split the section and initialize variables
        String[] parts = section.split(",");
        int[] scores = new int[parts.length];
        int n = 0; // Count valid scores found
        String line;

        // Search the parts array for numbers and store in scores array
        for (int i = 0; i < parts.length; i ++) {

            try {
                scores[n] = Integer.parseInt(parts[i].trim());
                n++;

            } catch (NumberFormatException ex) {
                // Skip non numbers
            }

        } // End for loop

        /* ===== PERFORM CALCULATIONS ===== */
        // No valid scores in the array
        if (n == 0) {
            line = "Scores - Section 2 is: " + section + " - No valid scores.";

        // With valid scores in the array
        } else {

            // Initialize variables for calculation
            double sum = 0;
            double sumOfSquares = 0;

            // Get running sum and sum of squares
            for (int i = 0; i < n; i++) {

                sum = sum + scores[i];
                sumOfSquares = sumOfSquares + scores[i] * scores[i];

            } // End for loop

            // Calculate average and std dev
            double average = sum / n;
            double sd = Math.sqrt(sumOfSquares / n - average * average);

            /* ===== FIND NUMBERS OUTSIDE STD DEV ===== */
            String outliers = "";

            for (int i = 0; i < n; i++) {

                if (Math.abs(scores[i] - average) > 1.9 * sd) {

                    if (!outliers.isEmpty()) {
                        outliers = outliers + ", ";
                    }

                    outliers = outliers + scores[i];

                } // End if (Math.abs(scores[i] - average)

            } // End for loop

            // If no outliers set to none
            if (outliers.isEmpty()) {
                outliers = "None";
            }

            // Build the required printout
            line = "Scores - Section 2 is: " + section
                    + " - average: " + String.format("%.0f", average)
                    + " - SD: " + String.format("%.2f", sd)
                    + " - Outliers: " + outliers;

        } //  End if (n == 0) else

        // Print 60 times
        for (int i = 0; i < 1; i++) {
            System.out.println(line);
        }

    } // End run method

} // End Scores class
