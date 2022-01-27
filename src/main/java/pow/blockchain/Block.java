package pow.blockchain;

import pow.Network;
import pow.infrastructure.Constants;

import java.math.BigInteger;
import java.util.Date;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;
import static pow.Network.CURRENT_DIFFICULTY;

public class Block {
    private String hash;
    private final String previousHash;
    private final Transaction transaction;
    private final Long timeStamp;
    private final int difficultyBits;
    private int nonce;

    public Block(Transaction transaction, String previousHash) {
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.transaction = transaction;
        this.hash = calculatedHash();
        this.difficultyBits = Network.CURRENT_DIFFICULTY;
    }

    public String calculatedHash() {
        return sha256Hex(this.previousHash + this.timeStamp + this.nonce + this.difficultyBits + this.transaction.getData());
    }

    public String getHash() {
        return hash;
    }

    public void calculateHashWithNewNonce() {
        this.nonce ++;
        this.hash = calculatedHash();
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public int getNonce() {
        return nonce;
    }

    public Boolean isMined() {
        BigInteger difficulty = new BigInteger("256").subtract(new BigInteger(CURRENT_DIFFICULTY.toString()));
        BigInteger target = new BigInteger("2").pow(difficulty.intValue());

        BigInteger hashResult = new BigInteger(this.hash, Constants.HEX);

        if (hashResult.compareTo(target) < 0) return Boolean.TRUE;

        return Boolean.FALSE;
    }

}
