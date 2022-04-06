package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "h")
    private int hours;

    @Column(name = "m")
    private int minutes;

    @Column(name = "s")
    private int seconds;

    @Column(name = "text")
    private String eventText;


    public Event() {
    }

    public Event(Time time, String eventText) {
        this.hours = time.getHours();
        this.minutes = time.getMinutes();
        this.seconds = time.getSeconds();
        this.eventText = eventText;
    }

    public Time getTime() {
        return new Time(hours,minutes,seconds);
    }


    public String getEventText() {
        return eventText;
    }

    public void setEventText(String eventText) {
        this.eventText = eventText;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Event{" +
                "time=" + this.getTime() +
                ", eventText='" + eventText + '\'' +
                '}';
    }

    public void setTime(Time time) {
        this.hours = time.getHours();
        this.minutes = time.getMinutes();
        this.seconds = time.getSeconds();
    }
}