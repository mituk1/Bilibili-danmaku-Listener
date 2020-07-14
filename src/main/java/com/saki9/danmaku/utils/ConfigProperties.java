package com.saki9.danmaku.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author 本間Saki
 * @create 2020-07-14
 */
public class ConfigProperties {

    private static Properties properties;

    static {
        properties = new Properties();
        InputStream configInputStream = ConfigProperties.class
            .getClassLoader().getResourceAsStream("config.properties");

        try {
            properties.load(configInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}
