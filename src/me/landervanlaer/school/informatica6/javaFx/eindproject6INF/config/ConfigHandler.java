package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.config;

import java.util.HashMap;

public class ConfigHandler {
    private static final ConfigHandler configHandler = new ConfigHandler();
    private final HashMap<String, String> hashMap;

    private ConfigHandler() {
        this.hashMap = new HashMap<>();
    }

    public static ConfigHandler getInstance() {
        return configHandler;
    }

    public <T> T get(String key, Class<T> type) {
//        switch(type.getSimpleName()) {}
        return null;
    }
}
