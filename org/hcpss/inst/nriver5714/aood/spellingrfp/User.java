package org.hcpss.inst.nriver5714.aood.spellingrfp;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

public class User {
	private final String name;
	private int levelIndex;
	private int streak;
	private final BitSet recentResults;
	private int recentIncorrectAnswerCount;

	public User(String name, int levelIndex) {
		this.name = name;
		this.levelIndex = levelIndex;
		this.streak = 0;
		this.recentResults = new BitSet(Constants.NUM_RECENT_WORDS);
		this.recentIncorrectAnswerCount = 0;
	}

	public User(String name, int levelIndex, int streak, boolean[] recentResults) {
		this.name = name;
		this.levelIndex = levelIndex;
		this.streak = streak;
		this.recentIncorrectAnswerCount = 0;
		this.recentResults = new BitSet(Constants.NUM_RECENT_WORDS);
		for (int i = 0; i < Math.min(recentResults.length, Constants.NUM_RECENT_WORDS); i++) {
			if (recentResults[i] == false)
				this.recentIncorrectAnswerCount++;
			this.recentResults.set(i, recentResults[i]);
		}
	}

	public String getName() {
		return name;
	}

	public int getLevelIndex() {
		return levelIndex;
	}

	public Level getLevel() {
		return Configuration.getLevel(getLevelIndex());
	}

	public void recordResult(boolean correct) {
		if (this.recentResults.get(Constants.NUM_RECENT_WORDS - 1) == false)
			this.recentIncorrectAnswerCount--;

		BitSet recentResultsClone = (BitSet) recentResults.clone();
		recentResults.clear();
		recentResultsClone.stream().forEachOrdered(i -> {
			if (i < Constants.NUM_RECENT_WORDS - 1)
				recentResults.set(i + 1);
		});

		if (correct) {
			recentResults.set(0);
			this.streak++;
		} else {
			this.recentIncorrectAnswerCount++;
			this.streak = 0;
		}

		if (this.streak >= Math.min(this.getLevel().size(), Constants.STREAK_TO_LEVEL_UP) && this.levelIndex < Configuration.getLevels().length - 1) {
			this.levelIndex++;
		} else if (((double) this.recentIncorrectAnswerCount) / Constants.NUM_RECENT_WORDS >= Constants.RECENT_PROPORTION_WRONG_TO_DROP && this.levelIndex > 0)
			this.levelIndex--;
	}
	
	@Override
	public String toString() {
		return String.format("%s (Level: %d, Streak: %d, Recent Results: %s)", this.name, this.levelIndex + 1,
				this.streak, this.recentResults);
	}
}
