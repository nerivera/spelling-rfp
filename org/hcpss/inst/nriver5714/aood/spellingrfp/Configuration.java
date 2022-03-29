package org.hcpss.inst.nriver5714.aood.spellingrfp;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Configuration {

	// Suppresses default constructor, ensuring non-instantiability.
	private Configuration() {
	}

	private static Set<User> users;
	private static List<Level> levels;

	public static void load() {
		load(Constants.DEFAULT_CONFIG_FILE_PATH);
	}

	public static void load(String configFilePath) {
		// TODO write load method
	}

	public static Optional<User> getUser(String name) {
	}

	public static User createUser(String name, int age) {
	}

	public static Level getLevel(int levelIndex) {
	}

	public static void insertLevelBefore(int levelIndex) {
	}

	public static void addLevelAfter(int levelIndex) {
	}

	public static void removeLevel(int levelIndex) {
	}

}
