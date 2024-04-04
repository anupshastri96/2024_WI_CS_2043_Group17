package Objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConversion {
    public static java.sql.Date dateConversion(String date) {
        // Define the date format that matches your input string
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Create a Date object by parsing the string input
        java.sql.Date  dateObject = null;
        try {
            dateObject = (java.sql.Date) dateFormat.parse(date);
            System.out.println("Date object: " + dateObject);
        } catch (ParseException e) {
            System.out.println("Invalid date input");
        }
        return dateObject;
    }
}
