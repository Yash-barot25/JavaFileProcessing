package com.company.yash;

import java.io.*;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("locationObject.dat")))) {

            for (Location location : locations.values()) {
                objectOutputStream.writeObject(location);
            }

        }

    }


//        try (DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))) {
//            for (Location location : locations.values()) {
//
//                dataOutputStream.writeInt(location.getDirection());
//                dataOutputStream.writeUTF(location.getDescription());
//
//                int totalExits = location.getExits().size() - 1;
//                System.out.println("writing location" + location.getDirection() + ":" + location.getDescription());
//                System.out.println("Total " + totalExits + " exits");
//                dataOutputStream.writeInt(totalExits);
//                for (String exit : location.getExits().keySet()) {
//                    if (!exit.equalsIgnoreCase("Q")) {
//                        dataOutputStream.writeUTF(exit);
//                        dataOutputStream.writeInt(location.getExits().get(exit));
//                        System.out.println("\t\t" + exit + ":" + location.getExits().get(exit));
//                    }
//
//                }
//            }
//        }
//    }

//        try (FileWriter locFile = new FileWriter("locations.txt");
//             FileWriter dirFile = new FileWriter("directions.txt")) {
//            for (Location location : locations.values()) {
//                locFile.write(location.getDirection() + "," + location.getDescription() + "\n");
//                for (String dir : location.getExits().keySet()) {
//                    if (!dir.equalsIgnoreCase("Q")){
//                        dirFile.write(location.getDirection() + "," + dir + "," + location.getExits().get(dir) + "\n");
//                    }
//
//                }
//            }
//        }
//    }


    static {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream("locationObject.dat")))) {
            boolean isEOF = false;

            while (!isEOF) {
                try {
                    Location location = (Location) objectInputStream.readObject();
                    int dir = location.getDirection();
                    String desc = location.getDescription();
                    locations.put(dir, new Location(dir, desc, location.getExits()));
                } catch (EOFException eof) {
                    isEOF = true;
                }

            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
//        try (DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))) {
//            boolean isEOF = false;
//
//            while (!isEOF) {
//                try {
//                    Map<String, Integer> exits = new HashMap<>();
//                    int dir = dataInputStream.readInt();
//                    String desc = dataInputStream.readUTF();
//                    int totalExits = dataInputStream.readInt();
//                    System.out.println("importing : " + dir + "," + desc);
//                    for (int i = 0; i < totalExits; i++) {
//                        String x = dataInputStream.readUTF();
//                        int y = dataInputStream.readInt();
//                        System.out.println("\t\t" + x + ":" + y);
//                        exits.put(x, y);
//                    }
//                    locations.put(dir, new Location(dir, desc, exits));
//                } catch (EOFException eof) {
//                    isEOF = true;
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("File completed");
//
//        }
//
//
//    }

//        Scanner scanner = null;
//        try {
//            scanner = new Scanner(new FileReader("locations_big.txt"));
//            scanner.useDelimiter(",");
//            while (scanner.hasNextLine()) {
//                int dir = scanner.nextInt();
//                scanner.skip(scanner.delimiter());
//                String description = scanner.nextLine();
//                Map<String, Integer> exits = new HashMap<>();
//                System.out.println("Importing " + dir + "," + description);
//                locations.put(dir, new Location(dir, description, exits));
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (scanner != null) {
//                scanner.close();
//            }
//        }
//
//        try (BufferedReader reader = new BufferedReader(new FileReader("directions_big.txt"))) {
//            String input;
//            while ((input = reader.readLine()) != null) {
//                String[] pieces = input.split(",");
//                int doc = Integer.parseInt(pieces[0]);
//                String dir = pieces[1];
//                int value = Integer.parseInt(pieces[2]);
//                System.out.println("available exits " + dir + " : " + value);
//                Location location = locations.get(doc);
//                location.addExit(dir, value);
//            }
////            scanner = new Scanner(new BufferedReader(new  FileReader("directions.txt")));
////            scanner.useDelimiter(",");
////            while(scanner.hasNextLine()){
////                int loc = scanner.nextInt();
////                scanner.skip(scanner.delimiter());
////                String dir = scanner.next();
////                scanner.skip(scanner.delimiter());
////                String exit = scanner.nextLine();
////                int val = Integer.parseInt(exit);
////              Location location =   locations.get(loc);
////              location.addExit(dir,val);
//            //}
//
//
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//
//        }
//    }

//        Map<String, Integer> tempExits = new HashMap<>();
//        tempExits.put("N", 2);
//        tempExits.put("S", 1);
//        tempExits.put("E", 4);
//        tempExits.put("W", 3);
//        locations.put(0, new Location(0, "You'r are in-front of your computer learning java.", tempExits));
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
//}


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
