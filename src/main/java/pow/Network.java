package pow;

import pow.blockchain.Block;
import pow.blockchain.Transaction;
import pow.nodes.Node;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;
import static java.util.Arrays.asList;

public class Network {
    public static double MAX_DIFFICULTY = pow(2, 32);
    public static Integer CURRENT_DIFFICULTY = 23;

    private static final List<Node> nodes = new ArrayList<>();

    public static void addNodes(Node... newNodes) {
        nodes.addAll(asList(newNodes));
    }

    public static void transmitTransaction(Transaction transaction) {
        nodes.parallelStream().forEach(node -> node.newTransactionReceived(transaction));
    }

    public static void transmitMinedBlock(Node from, Block block) {
        nodes.parallelStream().forEach(node -> {
            if (!node.equals(from)) {
                node.newMinedBlockReceived(block);
            }
        });
    }

}
