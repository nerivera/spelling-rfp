package org.hcpss.inst.nriver5714.aood.spellingrfp;

import java.util.BitSet;

public class User {
	private String name;
	private int levelIndex;
	private int streak;
	private BitSet recentResults;

	public User(String name, int levelIndex) {
		this.name = name;
		this.levelIndex = levelIndex;
		this.streak = 0;
		this.recentResults = new BitSet();
	}

	public User(String name, int levelIndex, int streak, boolean[] recentResults) {
		this.name = name;
		this.levelIndex = levelIndex;
		this.streak = streak;
		this.recentResults = new BitSet(recentResults.length);
		for (int i = 0; i < recentResults.length; i++) {
			if (recentResults[i] == true) {
				this.recentResults.set(i);
			}
		}
	}
	
	@Override
	public String toString() {
		return String.format("%s (Level: %d, Streak: %d, Recent Results: %s)", this.name, this.levelIndex + 1, this.streak, this.recentResults);
	}
}
