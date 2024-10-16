package com.example.server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Server  {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) throws IOException {
        try(Selector selector = Selector.open();
            ServerSocketChannel socket = ServerSocketChannel.open();
        ) {
            InetSocketAddress serverSocketAddr = new InetSocketAddress("localhost", 1111);

            socket.bind(serverSocketAddr);
            socket.configureBlocking(false);
            socket.register(selector, SelectionKey.OP_ACCEPT); // Interested only in Accept connection

            while (true) {
                System.out.println("I'm a server and I'm waiting for new connection and buffer select...");
                selector.select();

                //define a set of selectable keys
                Set<SelectionKey> selKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selKeys.iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey myKey = keyIterator.next();
                    keyIterator.remove();

                    if (!myKey.isValid()) {
                        continue;
                    }
                    if (myKey.isAcceptable()) {
                        accept(selector, socket);
                    } else if (myKey.isReadable()) {
                        handleClientRequest(myKey);
                    }

                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void accept(Selector selector, ServerSocketChannel socket) throws IOException {
        SocketChannel clientChannel = socket.accept();
        clientChannel.configureBlocking(false);
        clientChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("Connection Accepted: " + clientChannel.getRemoteAddress());
    }

    private static void handleClientRequest(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(256);
        int bytesRead = clientChannel.read(buffer);

        if (bytesRead == -1) {
            clientChannel.close();
            return;
        }

        buffer.flip();
        int clientNumber = buffer.getInt();
        int generatedNumber = RANDOM.nextInt(10);
        String response;
        System.out.println("Received: " + clientNumber);
        System.out.println("Generated: " + generatedNumber);
        if (clientNumber == generatedNumber) {
            response = "Congratulations!";
        } else if (clientNumber < generatedNumber) {
            response = "Auto generated value was " + generatedNumber + ". Try a bigger number.";
        } else {
            response = "Auto generated value was " + generatedNumber + ". Try a smaller number.";
        }
        buffer.clear();
        buffer.put(response.getBytes());
        buffer.flip();
        clientChannel.write(buffer);

        System.out.println("Sent: " + response);

        if (buffer.hasRemaining()) {
            buffer.compact();
        } else {
            buffer.clear();
        }
    }



}
