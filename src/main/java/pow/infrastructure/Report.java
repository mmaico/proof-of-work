package pow.infrastructure;

import pow.blockchain.Block;

import java.math.BigInteger;

import static pow.Network.CURRENT_DIFFICULTY;

public class Report {

    public static void showMiningReport(long start, String minerName, Block block) {
        BigInteger difficulty = new BigInteger("256").subtract(new BigInteger(CURRENT_DIFFICULTY + ""));
        long end = System.currentTimeMillis();
        long elapsedTimeInSeconds = (end - start) / 1000;

        System.out.println("#################### " + minerName + " ########################");
        System.out.println("time: " + elapsedTimeInSeconds + " seconds");
        System.out.println("Success with nonce: " + block.getNonce());
        System.out.println("Difficulty: " + difficulty);
        System.out.println("Hash is: " + block.getHash());
        System.out.println("###############################################################");
    }
}
