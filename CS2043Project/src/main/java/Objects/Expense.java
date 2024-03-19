package Objects;
import Enum.Term;
public class Expense extends Transaction{
    private Term term;

    public Expense(String name, double amount, String description, Term term) {
        super(name, 'w', amount, description);
        this.term = term;
    }

    public Expense(String name, double amount, Term term) {
        super(name, 'w', amount);
        this.term = term;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }
}
