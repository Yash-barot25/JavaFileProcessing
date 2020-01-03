package com.company.yash;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Location implements Serializable {

    private final String description;
    private final int direction;
    private final Map<String, Integer> exits;

    private long serialVersionUID = 1L;

    public Location(int direction, String description, Map<String, Integer> exits) {
        this.description = description;
        this.direction = direction;
        if (exits != null){
            this.exits = new LinkedHashMap<>(exits);
        }else{
            this.exits = new LinkedHashMap<>();
        }

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

    @Override
    public String toString() {
        return  direction + "," + description + "\n";
    }

    public String getDirectionString(){
        StringBuilder builder = new StringBuilder();
        for (String exit : this.exits.keySet()){
            builder.append(this.direction).append(",").append(exit).append(",").append(this.exits.get(exit)).append("\n");
        }
        return builder.toString();
    }
}
