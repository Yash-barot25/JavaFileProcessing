package com.company.yash;

import jdk.jshell.execution.LoaderDelegate;

import java.io.*;
import java.nio.BufferOverflowException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException{

        Path locPath = Paths.get("locationsObject.dat");


       try(ObjectOutputStream stream  = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(locPath)))){
           for (Location location: locations.values()){
               stream.writeObject(location);
           }
       }


    }


    static {
        Path locPath = Paths.get("locationsObject.dat");
      //  Path dirPath = Paths.get("directions.txt");

        try(ObjectInputStream stream = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(locPath)))){
            boolean isEOF= false;
            while(!isEOF){
                try{
                    Location location = (Location)stream.readObject();
                    locations.put(location.getDirection(), new Location(location.getDirection(), location.getDescription(), location.getExits()));
                }catch (EOFException e){
                    isEOF = true;
                }
            }


        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e1){
            System.out.println(e1.getMessage());
            e1.printStackTrace();
        }

//
//        try (Scanner scanner = anew Scanner(Files.newBufferedReader(locPath))) {
//            scanner.useDelimiter(",");
//            while (scanner.hasNextLine()) {
//                int dir = scanner.nextInt();
//                scanner.skip(scanner.delimiter());
//                String desc = scanner.nextLine();
//                locations.put(dir, new Location(dir, desc, null));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try (BufferedReader reader = Files.newBufferedReader(dirPath)) {
//            String input;
//            while ((input = reader.readLine()) != null) {
//
//                String[] pieces = input.split(",");
//                int dir = Integer.parseInt(pieces[0]);
//                locations.get(dir).addExit(pieces[1], Integer.parseInt(pieces[2]));
//
//            }
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }


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
