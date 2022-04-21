package org.hcpss.inst.nriver5714.aood.spellingrfp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Map;
import java.util.Objects;
import java.util.HashMap;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

// TODO: add saving functionality
public class Configuration {

	// Suppresses default constructor, ensuring non-instantiability.
	private Configuration() {
	}

	public static enum LoadState {
		NOT_LOADED, LOADING, LOADED
	}

	private static LoadState loadState = LoadState.NOT_LOADED;
	private static String configFilePath;
	private static Map<String, User> users;
	private static List<Level> levels;

	@SuppressWarnings("serial")
	public static class NotYetLoadedException extends IllegalStateException {

		private NotYetLoadedException() {
		}

	}

	@SuppressWarnings("serial")
	public static class LoadPendingException extends IllegalStateException {

		private LoadPendingException() {
		}

	}

	@SuppressWarnings("serial")
	public static class AlreadyLoadedException extends IllegalStateException {

		private AlreadyLoadedException() {
		}

	}

	@SuppressWarnings("serial")
	public static class UserAlreadyExistsException extends RuntimeException {

		private UserAlreadyExistsException(String name) {
			super("a user with the name \"" + name + "\" already exists");
		}

	}

	public static void load(Runnable callback) throws FileNotFoundException {
		load(Constants.DEFAULT_CONFIG_FILE_PATH, callback);
	}

	public static void load(String configFilePath, Runnable callback) throws FileNotFoundException {
		if (loadState == LoadState.LOADING)
			throw new LoadPendingException();
		if (loadState == LoadState.LOADED)
			throw new AlreadyLoadedException();
		Objects.requireNonNull(configFilePath);

		loadState = LoadState.LOADING;

		Configuration.configFilePath = configFilePath;
		var jsonObject = new JSONObject(new JSONTokener(new FileReader(configFilePath)));

		var forEachCompleted = new AtomicBoolean();

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
			if (users.size() == usersArray.length()) {
				if (forEachCompleted.getAndSet(true)) {
					loadState = LoadState.LOADED;
					callback.run();
				}
			}
		});

		levels = new LinkedList<>();
		JSONArray levelsArray = jsonObject.getJSONArray("levels");
		levelsArray.forEach(levelObject -> {
			var levelJSONArray = (JSONArray) levelObject;

			var words = new Word[levelJSONArray.length()];
			for (var i = 0; i < words.length; i++) {
				var wordJSONObject = levelJSONArray.getJSONObject(i);

				String wordString = wordJSONObject.getString("word");
				String sentence = wordJSONObject.getString("sentence");

				var word = new Word(wordString, sentence);
				words[i] = word;
			}

			var level = new Level(words);
			levels.add(level);
			if (levels.size() == levelsArray.length()) {
				if (forEachCompleted.getAndSet(true)) {
					loadState = LoadState.LOADED;
					callback.run();
				}		
			}
		});
	}

	public static LoadState getLoadState() {
		return loadState;
	}

	public static boolean userExists(String name) {
		if (loadState == LoadState.NOT_LOADED)
			throw new NotYetLoadedException();
		if (loadState == LoadState.LOADING)
			throw new LoadPendingException();

		return users.containsKey(name);
	}

	public static Optional<User> getUser(String name) {
		if (loadState == LoadState.NOT_LOADED)
			throw new NotYetLoadedException();
		if (loadState == LoadState.LOADING)
			throw new LoadPendingException();

		return Optional.ofNullable(users.get(name));
	}

	public static void createUser(String name, int age) {
		if (loadState == LoadState.NOT_LOADED)
			throw new NotYetLoadedException();
		if (loadState == LoadState.LOADING)
			throw new LoadPendingException();
		if (users.containsKey(name))
			throw new UserAlreadyExistsException(name);

		users.put(name, new User(name, Constants.AGE_TO_LEVEL_INDEX.applyAsInt(age)));
	}

	public static Level getLevel(int levelIndex) {
		if (loadState == LoadState.NOT_LOADED)
			throw new NotYetLoadedException();
		if (loadState == LoadState.LOADING)
			throw new LoadPendingException();

		return levels.get(levelIndex);
	}

	public static void addLevelBefore(int levelIndex) {
		if (loadState == LoadState.NOT_LOADED)
			throw new NotYetLoadedException();
		if (loadState == LoadState.LOADING)
			throw new LoadPendingException();

		levels.add(levelIndex, new Level());
	}

	public static void addLevelAfter(int levelIndex) {
		if (loadState == LoadState.NOT_LOADED)
			throw new NotYetLoadedException();
		if (loadState == LoadState.LOADING)
			throw new LoadPendingException();

		levels.add(levelIndex + 1, new Level());
	}

	public static void removeLevel(int levelIndex) {
		if (loadState == LoadState.NOT_LOADED)
			throw new NotYetLoadedException();
		if (loadState == LoadState.LOADING)
			throw new LoadPendingException();

		levels.remove(levelIndex);
	}

}
