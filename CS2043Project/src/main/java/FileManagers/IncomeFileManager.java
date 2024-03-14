package FileManagers;

import java.io.*;

/**
 * Manages income by reading from and writing to a CSV file.
 */
public class IncomeFileManager {
   private static final String filePath = "C:\\Users\\afifs\\OneDrive\\Desktop\\Project\\CS2043Project\\data\\income.csv";

   /**
    * Reads the income value from the CSV file.
    * @return The income value read from the file.
    */
   public static double readIncome() {
      double income = 0.0;
      try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
         String line = reader.readLine();
         if (line != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
               income = Double.parseDouble(parts[0]);
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
      return income;
   }

   /**
    * Writes the income value to the CSV file.
    * @param income The income value to write to the file.
    */
   public static void writeIncome(double income) {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
         writer.write(Double.toString(income));
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
