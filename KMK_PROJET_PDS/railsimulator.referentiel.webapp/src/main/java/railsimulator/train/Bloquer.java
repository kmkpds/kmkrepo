package railsimulator.train;

import java.util.HashMap;
import java.util.Map;


public class Bloquer {
	//private static org.apache.log4j.Logger log = Logger.getLogger(Bloquer.class);

	public static Map<String, Boolean> m = new HashMap<String, Boolean>();

	public static int etat = 0;

	public static boolean isLocked(String key) {
		return m.get(key);
	}

	public static void lock(String key) {

		if (m.get(key) == null) {
			addKey(key);
		}

		if (isLocked(key))
			//log.info("locked");

		// on bloque le thread
		while (Bloquer.isLocked(key))
			;
		m.put(key, true);

	}

	public static void unlock(String key) {

		m.put(key, false);
		//log.info("unlocked");
	}

	public static void addKey(String key) {

		if (!m.containsKey(key))
			m.put(key, false);

	}

}
