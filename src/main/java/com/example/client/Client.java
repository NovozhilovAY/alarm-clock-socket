package com.example.client;

import com.example.model.Event;
import com.example.server.InitMessage;
import com.example.server.Message;
import com.google.gson.Gson;

import javax.naming.InitialContext;
import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private int port = 3124;
    private Thread msgListenThread;
    private ClientForm form;
    private List<Event> events = new ArrayList<>();
    private Socket socket;
    public Client() {
        form = new ClientForm(this);
        try {
            InetAddress ip = InetAddress.getLocalHost();
            socket = new Socket(ip, port);
            msgListenThread = new Thread(()->{
                init();
                DataInputStream inputStream = null;
                try {
                    inputStream = new DataInputStream(socket.getInputStream());
                    while (true){
                        String json = inputStream.readUTF();
                        Message message = new Gson().fromJson(json, Message.class);
                        System.out.println(message);
                        handleEvent(message);
                        handleNewEvent(message);
                        handleNewTime(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            msgListenThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void handleEvent(Message message){
        if(message.getCurrentEvent() != null){
            form.showEventDialog(message.getCurrentEvent());
        }
    }
    private void handleNewEvent(Message message){
        if(message.getNewEvent()!=null){
            this.events.add(message.getNewEvent());
            form.addEvent(message.getNewEvent());
        }
    }
    private void handleNewTime(Message message){
        if(message.getTime()!=null){
            form.setTime(message.getTime());
        }
    }

    public void addNewEvent(Event event){
        try {
            this.events.add(event);
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            String eventMsg = new Gson().toJson(event);
            outputStream.writeUTF(eventMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init(){
        try {
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            String json = inputStream.readUTF();
            InitMessage message = new Gson().fromJson(json, InitMessage.class);
            this.events = message.getEvents();
            this.form.setTime(message.getTime());
            for (Event event: events){
                form.addEvent(event);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Client();
    }
}
