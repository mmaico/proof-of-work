package pow.nodes;

import pow.Network;
import pow.blockchain.Block;
import pow.blockchain.Blockchain;
import pow.blockchain.Transaction;
import pow.infrastructure.Constants;
import pow.pool.MinedBlocksPool;
import pow.pool.TransactionsPool;

import java.math.BigInteger;
import java.util.Objects;

import static pow.Network.CURRENT_DIFFICULTY;
import static pow.infrastructure.Report.showMiningReport;

public class Miner implements Node {
    private final Blockchain blockchain = new Blockchain();
    private final MinedBlocksPool newMinedBlocks = new MinedBlocksPool();
    private final TransactionsPool newTransactions = new TransactionsPool();

    private final String name;

    public Miner(String name) {
        this.name = name;
    }

    public void mineTransaction() {
        if (!this.newTransactions.hasTransactions()) return;
        Transaction transaction = newTransactions.next();
        Block block = new Block(transaction, blockchain.getHashOfLastBlock());
        mine(block);
        if (newMinedBlocks.hasNew()) {
            blockchain.add(newMinedBlocks.getFirst());
        } else {
            if (block.isMined()) {
                Network.transmitMinedBlock(this, block);
                blockchain.add(block);
            }
        }

        if (this.newTransactions.hasTransactions()) mineTransaction();
    }


    public void mine(Block block) {
        System.out.println(this.name + ": Starting mining");
        long start = System.currentTimeMillis();
        BigInteger difficulty = new BigInteger("256").subtract(new BigInteger(CURRENT_DIFFICULTY + ""));
        BigInteger target = new BigInteger("2").pow(difficulty.intValue());

        for (int i = block.getNonce(); i < Network.MAX_DIFFICULTY; block.calculateHashWithNewNonce()) {
            BigInteger hashResult = new BigInteger(block.getHash(), Constants.HEX);
            if (newMinedBlocks.hasNew()) {
                System.out.println(this.name + ": mining stopped because they already solved the puzzle");
                break;
            }
            if (hashResult.compareTo(target) < 0) {
                showMiningReport(start, this.name, block);
                return;
            }
        }
    }

    public Blockchain getBlockchain() {
        return blockchain;
    }

    @Override
    public void newTransactionReceived(Transaction transaction) {
        this.newTransactions.add(transaction);
        this.mineTransaction();
    }

    @Override
    public void newMinedBlockReceived(Block block) {
        this.newMinedBlocks.add(block);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Miner miner = (Miner) o;
        return Objects.equals(name, miner.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
