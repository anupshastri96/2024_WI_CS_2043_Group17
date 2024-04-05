package Tests;

import Database.DB_Access;
import Database.DB_Transaction;

import java.sql.Connection;

public class DBTransactionTest {
    public static void main(String[] args){
        Connection dbConnect = DB_Access.Connect();
        boolean found1 = DB_Transaction.checkIfTrancactionExists(dbConnect, 2, 73);//Valid transaction in table
        boolean found2 = DB_Transaction.checkIfTrancactionExists(dbConnect, 2, 100000);//Not a valid transaction in table

        System.out.println("Does transaction 73 exist? - " + found1 + " (Should return 'true')");
        System.out.println("Does transaction 100000 exist? - " + found2 + " (Should return 'false')");
    }
}
