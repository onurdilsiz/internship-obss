package com.example.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class Client {

    public static void main(String [] args) throws  InterruptedException, IOException {
        try (SocketChannel clientChannel = SocketChannel.open(new InetSocketAddress("localhost", 1111))) {
            clientChannel.configureBlocking(false);

            System.out.println("Connected to server");
            for (int i = 0; i < 3; i++) {
                sendNumber(clientChannel, i);
                Thread.sleep(100);
                String response = receiveResponse(clientChannel);
                System.out.println("Response: " + response);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void sendNumber(SocketChannel clientChannel, int number) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt(number);
        buffer.flip();
        clientChannel.write(buffer);
        System.out.println("Sent: " + number);
    }

    private static String receiveResponse(SocketChannel clientChannel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(256);
        int bytesRead = clientChannel.read(buffer);
        System.out.println("Received " + bytesRead + " bytes");

        if (bytesRead == -1) {
            throw new IOException("Connection closed by server");
        }

        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        return new String(bytes);
    }

}
