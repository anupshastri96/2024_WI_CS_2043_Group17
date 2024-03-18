package Lists;

import ags.project.Transaction;

import java.util.LinkedList;

public class TransactionList{
    private final LinkedList<Transaction> transactionList;

    public TransactionList(){
        transactionList = new LinkedList<>();
    }
    public boolean addTransaction(Transaction transaction){
        return transactionList.add(transaction);
    }
    public void addTransaction(String name, char type, double amount){
        Transaction transaction = new Transaction(name, type, amount);
        transactionList.add(transaction);
    }
    public boolean addTransaction(String name, char type, double amount, String description){
        Transaction transaction = new Transaction(name, type, amount, description);
        return transactionList.add(transaction);
    }

    public void removeTransaction(Transaction transaction){
        for(int i = 0; i < transactionList.size(); i++){
            if(transactionList.get(i).equals(transaction)){
                transactionList.remove(i);
            }
        }
    }

    public void removeTransaction(int transactionId){
        for(int i = 0; i < transactionList.size(); i++){
            if(transactionList.get(i).getTransactionId() == transactionId){
                transactionList.remove(i);
            }
        }
    }

    public void printList() {

        System.out.printf("------------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-10s | %-15s | %-8s | %-4s | %-8s | %-50s |%n", "Date", "Transaction ID", "Name", "Type", "Amount", "Description");
        System.out.printf("------------------------------------------------------------------------------------------------------------------%n");
        for (Transaction transaction : transactionList) {
            System.out.printf("| %-10s | %-15d | %-8s | %-4c | %-8.2f | %-50s |%n",
                    transaction.getDate(),
                    transaction.getTransactionId(),
                    transaction.getName(),
                    transaction.getType(),
                    transaction.getAmount(),
                    transaction.getDescription()
            );
        }
        System.out.printf("------------------------------------------------------------------------------------------------------------------%n");
    }

}
