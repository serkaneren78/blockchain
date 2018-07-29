package com.seren.blockchain;

public class Main {
	public static void main(String[] args) {
		Blockchain blockchain = new Blockchain(4);
		blockchain.addBlock(blockchain.newBlock("[{\"from\":\"AAA\",\"to\":\"BBB\", \"amount\":100},"
				+ "{\"from\":\"BBB\",\"to\":\"CCC\", \"amount\":50},"
				+ "{\"from\":\"CCC\",\"to\":\"DDD\", \"amount\":20}]"));
		blockchain.addBlock(blockchain.newBlock("[{\"from\":\"CCC\",\"to\":\"BBB\", \"amount\":100},"
				+ "{\"from\":\"EEE\",\"to\":\"AAA\", \"amount\":150},"
				+ "{\"from\":\"DDD\",\"to\":\"BBB\", \"amount\":120}]"));
		blockchain.addBlock(blockchain.newBlock("[{\"from\":\"AAA\",\"to\":\"BBB\", \"amount\":150},"
				+ "{\"from\":\"VVV\",\"to\":\"AAA\", \"amount\":500},"
				+ "{\"from\":\"SSS\",\"to\":\"RRR\", \"amount\":200}]"));

		System.out.println("Blockchain valid: " + blockchain.validateBlockchain());
		System.out.println(blockchain);

	}
}
