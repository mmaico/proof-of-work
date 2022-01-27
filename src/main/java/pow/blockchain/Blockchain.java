package pow.blockchain;

import java.util.LinkedList;

import static java.lang.String.format;

public class Blockchain {
    private static final String HASH_NOT_MATCH_WITH_CALCULATED_HASH = "The current hash: %s doesnt match the calculated: %s";
    private static final String PREVIOUS_HASH_NOT_EQUALS_CURRENT_HASH = "The previous hash: %s doesnt match with previous block: %s";
    private static final String GENESYS_PREVIOUS_HASH = "0";
    private final LinkedList<Block> chain = new LinkedList<>();

    public Boolean isValid() {

        for (int i = 1; i < chain.size() ; i++) {
            Block current = chain.get(i);
            Block previous = chain.get(i - 1);

            if (!current.getHash().equals(current.calculatedHash())) {
                String message = format(HASH_NOT_MATCH_WITH_CALCULATED_HASH, current.getHash(), current.calculatedHash());
                System.out.println(message);
                return Boolean.FALSE;
            }

            if (!previous.getHash().equals(current.getPreviousHash())) {
                String message = format(PREVIOUS_HASH_NOT_EQUALS_CURRENT_HASH, current.getPreviousHash(), previous.getHash());
                System.out.println(message);
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }

    public String getHashOfLastBlock() {
        if (this.chain.size() == 0) return GENESYS_PREVIOUS_HASH;
        return this.chain.getLast().getHash();
    }

    public void add(Block block) {
        if (this.chain.isEmpty()) {
            this.chain.add(block);
        } else {
            if (!this.chain.getLast().getHash().equals(block.getPreviousHash())) {
                throw new RuntimeException("The previous hash is different from current block");
            } else {
                this.chain.add(block);
            }
        }
    }

    @Override
    public String toString() {
        return this.chain.stream().map(item -> item.getHash()).reduce((one, two) -> one + "," + two).get();
    }
}
