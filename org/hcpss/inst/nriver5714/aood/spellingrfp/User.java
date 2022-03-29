package org.hcpss.inst.nriver5714.aood.spellingrfp;

import java.util.BitSet;

public class User {
	private String name;
	private int levelIndex;
	private int streak;
	private BitSet recentResults;

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
}
