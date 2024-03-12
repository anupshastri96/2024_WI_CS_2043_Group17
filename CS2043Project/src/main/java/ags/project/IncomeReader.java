package ags.project;
import java.io.*;

public class IncomeReader{
   public IncomeReader(){
      try {
         FileReader file = new FileReader("C:\\Users\\afifs\\OneDrive\\Desktop\\Project\\CS2043Project\\src\\income.txt.txt");
      }
      catch(Exception e){
         System.err.println("Unable to find file");
      }
   }


}
