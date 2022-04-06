package com.example.server;

import com.example.model.AlarmClock;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
    private int port = 3124;;
    private AlarmClock alarmClock;

    public Server(AlarmClock alarmClock) {
        this.alarmClock = alarmClock;
        alarmClock.start();
        try {
            InetAddress ip = InetAddress.getLocalHost();
            ServerSocket ss = new ServerSocket(port, 0, ip);
            System.out.println("Сервер запущен!");
            while (true){
                Socket cs = ss.accept();
                System.out.println("Клиент подключен!");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
