package pow.pool;

import pow.blockchain.Transaction;

import java.util.LinkedList;

public class TransactionsPool {

    public final LinkedList<Transaction> transactions;

    public TransactionsPool() {
        this.transactions = new LinkedList<>();
    }

    public void add(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public Boolean hasTransactions() {
        return this.transactions.size() > 0;
    }

    public Transaction next() {
        return transactions.removeFirst();
    }

}
