package com.example;

import com.example.model.AlarmClock;
import com.example.model.Time;
import com.example.repository.EventRepository;
import com.example.server.Server;

public class AlarmClockApplication {
    public static void main(String[] args) {
        EventRepository repository = new EventRepository();
        AlarmClock alarmClock = new AlarmClock(new Time(0,0,0),repository);
        Server server = new Server(alarmClock);
    }
}

