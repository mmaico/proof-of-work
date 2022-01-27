package pow.nodes;

import pow.blockchain.Block;
import pow.blockchain.Transaction;

public interface Node {

    void newTransactionReceived(Transaction transaction);
    void newMinedBlockReceived(Block block);
}
