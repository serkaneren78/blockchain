package com.seren.blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {

	private int difficulty;
	private List<Block> blocks = new ArrayList<>();

	public Blockchain(int difficulty) {
		this.difficulty = difficulty;
		addBlock(createFirstBlock());
	}

	private Block createFirstBlock() {
		return new Block(0, System.currentTimeMillis(), null, "");
	}

	public Block lastBlock() {
		return blocks.get(blocks.size() - 1);
	}

	public Block newBlock(String data) {
		return new Block(lastBlock().getIndex() + 1, System.currentTimeMillis(), lastBlock().getHash(), data);
	}

	public void addBlock(Block b) {
		if (b != null) {
			b.mineBlock(difficulty);
			blocks.add(b);
		}
	}

	public boolean validateFirstBlock() {
		Block firstBlock = blocks.get(0);

		if (firstBlock.getIndex() != 0 
				|| firstBlock.getPreviousHash() != null 
				|| firstBlock.getHash() == null
				|| !Utils.generateHash(firstBlock.concatFields()).equals(firstBlock.getHash())) {
			return false;
		}

		return true;
	}

	public boolean validateNewBlock(Block newBlock, Block previousBlock) {
		if (newBlock != null && previousBlock != null) {

			if (newBlock.getPreviousHash() == null 
					|| !newBlock.getPreviousHash().equals(previousBlock.getHash())) {
				return false;
			}

			if (newBlock.getHash() == null 
					|| !Utils.generateHash(newBlock.concatFields()).equals(newBlock.getHash())) {
				return false;
			}

			return true;
		}

		return false;
	}

	public boolean validateBlockchain() {
		if (!validateFirstBlock()) {
			return false;
		}

		for (int i = 1; i < blocks.size(); i++) {
			Block currentBlock = blocks.get(i);
			Block previousBlock = blocks.get(i - 1);

			if (!validateNewBlock(currentBlock, previousBlock)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Blockchain [difficulty=");
		builder.append(difficulty);
		builder.append(", blocks=");
		builder.append("\n");
		for (Block block : blocks) {
			builder.append(block);	
			builder.append("\n");
		}
		builder.append("]");
		return builder.toString();
	}
	
	

}