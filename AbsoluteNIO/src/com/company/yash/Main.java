package com.company.yash;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try{
            Path path = FileSystems.getDefault().getPath("data.txt");
          //  Files.writeString(path, "\nwe will rock....hurray" + "\nya man we will rock" + "\ni don't suspect about it", StandardOpenOption.APPEND);
            List<String> names = Files.readAllLines(path,StandardCharsets.US_ASCII);
            for (String name : names){
                System.out.println(name);
            }


        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
