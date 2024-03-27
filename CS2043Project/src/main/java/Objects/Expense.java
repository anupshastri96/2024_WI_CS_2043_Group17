package Objects;

import Enum.Term;

/**
 * The {@code Expense} class represents an expense transaction. It extends the {@code Transaction} class,
 * adding a specific term (short-term or long-term) to categorize the expense.
 */
public class Expense extends Transaction {
    private Term term;

    /**
     * Constructs a new Expense with a specified name, amount, description, and term.
     *
     * @param name        The name of the expense.
     * @param amount      The monetary amount of the expense.
     * @param description A detailed description of the expense.
     * @param term        The term (short-term or long-term) of the expense.
     */
    public Expense(int userId, int transactionId, java.util.Date date, String name, double amount, String description, Category category, Term term) {
        super(userId, transactionId, date, name, 'w', amount, description, category);
        this.term = term;
    }

    /**
     * Returns the term of the expense.
     *
     * @return The term (short-term or long-term) of this expense.
     */
    public Term getTerm() {
        return term;
    }

    /**
     * Sets the term of the expense.
     *
     * @param term The term (short-term or long-term) to set for this expense.
     */
    public void setTerm(Term term) {
        this.term = term;
    }
}
