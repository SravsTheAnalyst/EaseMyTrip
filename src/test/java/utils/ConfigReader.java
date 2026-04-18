package utils;

import java.io.FileInputStream;
import java.util.Properties;

public final class ConfigReader {
    private static Properties prop = new Properties();

    static {
        try {
        	FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
        	prop.load(file);
         } catch (Exception e) {
            System.out.println("Could not load config.properties: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }

    public static String get(String key, String def) {
        return prop.getProperty(key, def);
    }
    }
