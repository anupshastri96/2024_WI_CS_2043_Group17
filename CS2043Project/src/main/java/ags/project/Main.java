package ags.project;


import java.io.IOException;
import static ReadAndWriters.IncomeReader.*;

public class Main{
    public static void main(String[] args) throws IOException {
        System.out.println(getIncome());
        writeIncome(10);
        System.out.println(getIncome());
    }
}