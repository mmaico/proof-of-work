package pow.pool;

import pow.blockchain.Block;

import java.util.LinkedList;

public class MinedBlocksPool {

    private LinkedList<Block> blocks;

    public MinedBlocksPool() {
        this.blocks = new LinkedList<>();
    }

    public void add(Block block) {
        blocks.add(block);
    }

    public Boolean hasNew() {
        return blocks.size() > 0;
    }

    public Block getFirst() {
        return this.blocks.removeFirst();
    }
}
