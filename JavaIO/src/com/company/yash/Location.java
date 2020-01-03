package com.company.yash;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;


public class Location implements Serializable {

    private long serialVersionUID = 1L;

    private final String description;
    private final int direction;
    private final Map<String, Integer> exits;

    public Location(int direction, String description, Map<String, Integer> exits) {
        this.description = description;
        this.direction = direction;
        this.exits = new LinkedHashMap<>(exits);
        this.exits.put("Q",0);
    }

    public void addExit(String desc, Integer dir) {
        exits.put(desc, dir);
    }

    public String getDescription() {
        return description;
    }

    public int getDirection() {
        return direction;
    }

    public Map<String, Integer> getExits() {
        return new LinkedHashMap<>(exits);
    }
}
