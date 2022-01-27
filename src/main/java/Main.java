import pow.Network;
import pow.blockchain.Transaction;
import pow.nodes.Miner;

public class Main {

    public static void main(String[] args) {
        Transaction genesysTransaction = new Transaction("My genesys block");
        Transaction secondTransaction = new Transaction("My Second transaction");
        Transaction thirdTransaction = new Transaction("My third transaction");

        Miner spider = new Miner("Spiderman");
        Miner batman = new Miner("Batman");
        Miner ironman = new Miner("Ironman");
        Miner freakman = new Miner("Freakman");

        Network.addNodes(spider, batman, ironman, freakman);
        Network.transmitTransaction(genesysTransaction);
        Network.transmitTransaction(secondTransaction);
        Network.transmitTransaction(thirdTransaction);

        System.out.println("############################ final report #####################################");
        System.out.println("Spider Blockchain is valid: " + spider.getBlockchain().isValid() + " Blockchain: " + spider.getBlockchain());
        System.out.println("Batman Blockchain is valid: " + batman.getBlockchain().isValid() + " Blockchain: " + batman.getBlockchain());
        System.out.println("Ironman Blockchain is valid: " + ironman.getBlockchain().isValid() + " Blockchain: " + ironman.getBlockchain());
        System.out.println("Freakman Blockchain is valid: " + freakman.getBlockchain().isValid() + " Blockchain: " + freakman.getBlockchain());
        System.out.println("###############################################################################");
    }
}
