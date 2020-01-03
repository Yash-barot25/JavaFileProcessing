package com.company.yash;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Locations locations = new Locations();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        Map<String, String> helper = new HashMap<>();
        helper.put("NORTH", "N");
        helper.put("SOUTH", "S");
        helper.put("WEST", "W");
        helper.put("EAST", "E");
        helper.put("QUIT", "Q");

        int loc = 1;
        String location;
        String temp = null;
        while (true) {
            Map<String, Integer> exits = locations.get(loc).getExits();
            System.out.println(locations.get(loc).getDescription());

            if (loc == 0) {
                break;
            }

            System.out.print("Available exits are ");
            for (String keys : exits.keySet()) {
                System.out.print(keys + " ");
            }
            System.out.println();

            location = scanner.nextLine().toUpperCase();
            if (location.length() > 1) {
                String[] words = location.split(" ");
                for (String word : words) {
                    if (helper.containsKey(word)) {
                        temp = helper.get(word);
                        if (exits.containsKey(temp)) {
                            loc = exits.get(temp);
                            break;
                        }
                    }
                }
                if (temp == null) {
                    System.out.println("Invalid direction!!!");
                }

            } else {
                if (exits.containsKey(location)) {
                    loc = exits.get(location);
                } else {
                    System.out.println("Invalid direction!!!");
                }
            }

        }
    }
}

//It can be done with few restrictions.
//            if (!exits.containsKey(location) && !helper.containsKey(location)) {
//                System.out.println("you can't go in that direction.");
//            } else {
//                if (location.length() > 1){
//                    location = helper.get(location);
//                    loc = exits.get(location);
//                }else{
//                    loc = exits.get(location);
//                }
//            }