package pow.blockchain;

public class Transaction {

    private final String data;

    public Transaction(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
