package org.hcpss.inst.nriver5714.aood.spellingrfp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Configuration {

	// Suppresses default constructor, ensuring non-instantiability.
	private Configuration() {
	}

	private static Map<String, User> users;
	private static List<Level> levels;

	public static void load() throws FileNotFoundException {
		load(Constants.DEFAULT_CONFIG_FILE_PATH);
	}

	public static void load(String configFilePath) throws FileNotFoundException {
		var jsonObject = new JSONObject(new JSONTokener(new FileReader(configFilePath)));
		users = new HashMap<>();
		JSONArray usersArray = jsonObject.getJSONArray("users");
		usersArray.forEach(userObject -> {
			var userJSONObject = (JSONObject) userObject;
			String name = userJSONObject.getString("name");
			int levelIndex = userJSONObject.getInt("levelIndex");
			int streak = userJSONObject.getInt("streak");
			JSONArray recentResultsJSONArray = userJSONObject.getJSONArray("recentResults");
			boolean[] recentResults = new boolean[recentResultsJSONArray.length()];
			for (var i = 0; i < recentResults.length; i++) {
				recentResults[i] = recentResultsJSONArray.getBoolean(i);
			}
			var user = new User(name, levelIndex, streak, recentResults);
			users.put(name, user);
		});
	}

	public static Optional<User> getUser(String name) {
		return Optional.ofNullable(users.get(name));
	}

	public static void createUser(String name, int age) {
		if (users.containsKey(name))
			throw new IllegalStateException("a user with the name \"" + name + "\" already exists");

		users.put(name, new User(name, Constants.AGE_TO_LEVEL_INDEX.applyAsInt(age)));
	}

	public static Level getLevel(int levelIndex) {
		return levels.get(levelIndex);
	}

	public static void addLevelBefore(int levelIndex) {
		levels.add(levelIndex, new Level());
	}

	public static void addLevelAfter(int levelIndex) {
		levels.add(levelIndex + 1, new Level());
	}

	public static void removeLevel(int levelIndex) {
		levels.remove(levelIndex);
	}

}
