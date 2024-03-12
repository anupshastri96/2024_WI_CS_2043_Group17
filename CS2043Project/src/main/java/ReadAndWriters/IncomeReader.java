package ReadAndWriters;
import java.io.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IncomeReader {

   private static final String filePath = "C:\\Users\\afifs\\OneDrive\\Desktop\\Project\\CS2043Project\\data\\income.txt";

   // Method to read the income from the file
   public static int getIncome(){
      int income = -1;
      try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
         String line = reader.readLine(); // Read the first line
         if (line != null) {
            income = Integer.parseInt(line.trim()); // Parse and return the income as an integer
         } else {
            System.err.println("The file is empty.");
         }
      } catch (Exception e) {
         // This block catches parsing errors if the first line doesn't contain a valid integer
         System.err.println("The file does not contain a valid integer on the first line.");
      }
      return income;
   }

   public static void writeIncome(int income) throws IOException {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
         writer.write(Integer.toString(income)); // Convert the income to string and write/overwrite it in the file
      } // The try-with-resources statement ensures the writer is closed after use
   }
}
