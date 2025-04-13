package utils;

public class PropertyUtil {

    public static void saveProperty(String key, String value) {
        System.setProperty(key, value);
    }

    public static String getProperty(String key) {
        return System.getProperty(key);
    }
}
