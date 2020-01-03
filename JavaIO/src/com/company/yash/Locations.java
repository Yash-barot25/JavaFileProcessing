package com.company.yash;

import java.io.*;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Locations implements Map<Integer, Location> {

    private static Map<Integer, Location> locations = new LinkedHashMap<>();

    static {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream("locationsObject.dat")))) {
            boolean isEOF = false;
            while (!isEOF) {
                try {
                    Location location = (Location) objectInputStream.readObject();
                    System.out.println("importing " + location.getDirection() + " : " + location.getDescription());
                    for (String direction : location.getExits().keySet()) {
                        System.out.println("\t\t" + direction + "," + location.getExits().get(direction));
                    }
                    locations.put(location.getDirection(), location);
                } catch (EOFException eof) {
                    System.out.println("Reached at end");
                    isEOF = true;

                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
//        try (DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))) {
//            boolean isEOF = false;
//            while (!isEOF) {
//                try {
//                    Map<String, Integer> exits = new LinkedHashMap<>();
//                    int dir = dataInputStream.readInt();
//                    String description = dataInputStream.readUTF();
//                    System.out.println("Reading location " + dir + " : " + description);
//                    int availableExits = dataInputStream.readInt();
//                    for (int i = 0; i < availableExits; i++) {
//                        int exit = dataInputStream.readInt();
//                        String direction = dataInputStream.readUTF();
//                        System.out.println("\t\t" + exit + " : " + direction);
//                        exits.put(direction, exit);
//
//                    }
//                    locations.put(dir, new Location(dir, description, exits));
//                } catch (EOFException eof) {
//                    isEOF = true;
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("IO-EXCEPTION thrown");
//        }


        //try with resources version
//        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("locations.txt")));) {
//            scanner.useDelimiter(",");
//            while (scanner.hasNextLine()) {
//                int dir = scanner.nextInt();
//                scanner.skip(scanner.delimiter());
//                String description = scanner.nextLine();
//                Map<String, Integer> tempexits = new LinkedHashMap<>();
//                System.out.println("imported : " + dir + " : " + description);
//                locations.put(dir, new Location(dir, description, tempexits));
//            }
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        // //try , catch and finally version
//        Scanner scanner = null;
//        try {
//            scanner = new Scanner(new BufferedReader(new FileReader("locations.txt")));
//            scanner.useDelimiter(",");
//            while (scanner.hasNextLine()) {
//                int dir = scanner.nextInt();
//                scanner.skip(scanner.delimiter());
//                String description = scanner.nextLine();
//                Map<String, Integer> tempexits = new LinkedHashMap<>();
//                System.out.println("imported : " + dir + " : " + description);
//                locations.put(dir, new Location(dir, description, tempexits));
//            }
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (scanner != null) {
//                scanner.close();
//            }
//        }

//        try {
//            scanner = new Scanner(new BufferedReader(new FileReader("directions_big.txt")));
//            scanner.useDelimiter(",");
//            while (scanner.hasNextLine()) {
//                int dir = scanner.nextInt();
//                scanner.skip(scanner.delimiter());
//                String location = scanner.next();
//                scanner.skip(scanner.delimiter());
//                String value = scanner.nextLine();
//                int val = Integer.parseInt(value);
//                Location loc = locations.get(dir);
//                System.out.println(dir + ":" + location + ":" + value);
//                loc.addExit(location, val);
//            }
//        } catch (IOException e) {
//
//        } finally {
//            if (scanner != null) {
//                scanner.close();
//            }
//        }
//        try (BufferedReader reader = new BufferedReader(new FileReader("directions.txt"))) {
//            String input;
//            while ((input = reader.readLine()) != null) {
//                String[] pieces = input.split(",");
//                int direction = Integer.parseInt(pieces[0]);
//                String sign = pieces[1];
//                int exit = Integer.parseInt(pieces[2]);
//                Location location = locations.get(direction);
//                System.out.println(direction + ":" + sign + ":" + exit);
//                location.addExit(sign, exit);
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }

    }

//        Map<String, Integer> tempExits = new HashMap<>();
//        tempExits.put("N", 2);
//        tempExits.put("S", 1);
//        tempExits.put("E", 4);
//        tempExits.put("W", 3);
//        locations.put(0, new Location(0, "You'r are in-front of your computer learning java", tempExits));
//
//        tempExits = new HashMap<>();
//        tempExits.put("W", 3);
//        tempExits.put("E", 4);
//        locations.put(1, new Location(1, "Gas-station", tempExits));
//
//        tempExits = new HashMap<>();
//        tempExits.put("E", 4);
//        tempExits.put("W", 3);
//        locations.put(2, new Location(2, "Down town", tempExits));
//
//        tempExits = new HashMap<>();
//        tempExits.put("N", 2);
//        locations.put(3, new Location(3, "Go station", tempExits));
//
//        tempExits = new HashMap<>();
//        tempExits.put("S", 1);
//        tempExits.put("N", 2);
//        locations.put(4, new Location(4, "Ford car center", tempExits));
    //   }

    public static void main(String[] args) throws IOException {
        try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("locationsObject.dat")))) {
            for (Location location : locations.values()) {
                locFile.writeObject(location);
            }
        }


//writing data to disk using byte  streams.

//        try (DataOutputStream locFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))) {
//            for (Location location : locations.values()) {
//                locFile.writeInt(location.getDirection());
//                locFile.writeUTF(location.getDescription());
//                System.out.println("Writing location " + location.getDirection() + " : " + location.getDescription());
//                System.out.println("Writing " + (location.getExits().size() - 1) + " Exits");
//                locFile.writeInt(location.getExits().size() - 1);
//                for (String key : location.getExits().keySet()) {
//                    if (!key.equalsIgnoreCase("Q")) {
//                        System.out.println("\t\t" + key + "," + location.getExits().get(key));
//                        locFile.writeInt(location.getExits().get(key));
//                        locFile.writeUTF(key);
//                    }
//
//                }
//            }
    }


//buffer-writer and file-writer approach of writing content to a disk
//        try (BufferedWriter locFile = new BufferedWriter(new FileWriter("locations.txt"));
//             BufferedWriter dir = new BufferedWriter(new FileWriter("directions.txt"))) {
//            for (Location location : locations.values()) {
//                locFile.write(location.getDirection() + "," + location.getDescription() + "\n");
//                for (String direction : location.getExits().keySet()) {
//                    if (!direction.equalsIgnoreCase("Q")) {
//                        dir.write(location.getDirection() + "," + direction + "," + location.getExits().get(direction) + "\n");
//                    }
//                }
//            }
//        }

//    }
//Try , catch and finally syntax an old way of writing data
//        FileWriter locFile = null;
//        try {
//            locFile = new FileWriter("locations.txt");
//            for (Location location: locations.values()){
//                locFile.write( location.getDirection() + "," + location.getDescription() + "\n");
//                System.out.println("Imported "+ location.getDirection() + " : " + location.getDescription());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (locFile != null) {
//                    locFile.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    //   }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
