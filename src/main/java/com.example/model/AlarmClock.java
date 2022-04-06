package com.example.model;

import com.example.repository.EventRepository;

import java.util.List;

public class AlarmClock {
    private final Time time;
    private final EventRepository repository;
    private Thread thread;
    private Event activeEvent;

    public AlarmClock(Time time, EventRepository repository) {
        this.time = time;
        this.repository = repository;
    }

    public void start(){
        if(thread==null){
            thread = new Thread(
                    ()-> {
                        while (true){
                            try {
                                activeEvent = setActiveEvent();
                                if(activeEvent!=null){
                                    this.doEventLogic();
                                }
                                System.out.println(time);
                                Thread.sleep(1000);
                                time.addSeconds(1);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    });
        }
        thread.start();
    }

    private void doEventLogic() {
        System.out.println(activeEvent.getEventText());
    }

    private Event setActiveEvent(){
        List<Event> events = repository.findAll();
        for (Event event: events) {
            if(event.getTime().equals(this.time)){
                return event;
            }
        }
        return null;
    }
}
