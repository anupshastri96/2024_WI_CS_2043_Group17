package Verification;

import Objects.Transaction;

import java.util.LinkedList;

public class Verify {
    public static boolean doesTransactionExist(int transactionId, LinkedList<Transaction> transactionLinkedList){
        for (Transaction transaction : transactionLinkedList) {
            if (transaction.getTransactionId() == transactionId) {
                return true;
            }
        }
        return false;
    }
}
