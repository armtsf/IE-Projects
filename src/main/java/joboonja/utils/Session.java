package joboonja.utils;

import java.util.HashMap;

public class Session {
    private static HashMap<String, Integer> items = new HashMap<>();

    public static void put(String key, int value) {
        items.put(key, value);
    }

    public static int get(String key) {
        return items.get(key);
    }
}
