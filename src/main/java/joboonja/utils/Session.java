package joboonja.utils;

import java.util.HashMap;

public class Session {
    private static HashMap<String, String> items = new HashMap<>();

    public static void put(String key, String value) {
        items.put(key, value);
    }

    public static String get(String key) {
        return items.get(key);
    }
}
