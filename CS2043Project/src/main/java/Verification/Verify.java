package Verification;

import Database.DB_Category;
import Objects.Category;
import Objects.Transaction;

import java.util.LinkedList;
import java.util.regex.Pattern;

public class Verify {
    public static boolean doesTransactionExist(int transactionId, LinkedList<Transaction> transactionLinkedList){
        for (Transaction transaction : transactionLinkedList) {
            if (transaction.getTransactionId() == transactionId) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidDate(String date) {
        final String DATE_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";
        // Create a Pattern object
        Pattern pattern = Pattern.compile(DATE_PATTERN);

        // Now create matcher object.
        return pattern.matcher(date).matches();
    }

    public static boolean doesCategoryExist(int userId, String category){
        LinkedList<Category> categories = DB_Category.getCategoryList(userId);
        for (Category value : categories) {
            if (value.getName().equals(category)) {
                return true;
            }
        }
        return false;
    }
}
