package org.hcpss.inst.nriver5714.aood.spellingrfp;

import java.util.function.IntUnaryOperator;

public class Constants {
	public static final String DEFAULT_CONFIG_FILE_PATH = "config.json";
	public static final IntUnaryOperator AGE_TO_LEVEL_INDEX = age -> Math.min(age / 2, 9);
	public static final int STREAK_TO_LEVEL_UP = 7;
	public static final int NUM_RECENT_WORDS = 6;
	public static final double RECENT_PROPORTION_WRONG_TO_DROP = 0.7;

}
