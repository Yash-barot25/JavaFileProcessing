package com.company.yash;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;

public class Main2 {

    public static void main(String[] args) {
        try(FileOutputStream stream = new FileOutputStream("data.dat");
            FileChannel channel =  stream.getChannel()){

            byte[] message = "Hello universe!".getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(message);
           int writtenBytes =  channel.write(buffer);
            System.out.println("Bytes written " + writtenBytes);

            ByteBuffer myBuffer = ByteBuffer.allocate(Integer.BYTES);
            myBuffer.putInt(888);
            myBuffer.flip();
            writtenBytes = channel.write(myBuffer);
            System.out.println("Bytes written " + writtenBytes);

            myBuffer.flip();
            myBuffer.putInt(555);
            myBuffer.flip();
            writtenBytes = channel.write(myBuffer);
            System.out.println("Bytes written " + writtenBytes);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
