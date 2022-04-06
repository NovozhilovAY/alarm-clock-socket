package com.example;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private int port = 3124;
    public Client() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            Socket socket = new Socket(ip, port);
            System.out.println("Подключение");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Client();
    }
}
