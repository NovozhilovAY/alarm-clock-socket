package com.example.model;

public class Time {
    private int hours;
    private int minutes;
    private int seconds;

    public Time() {
    }

    public Time(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
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

    public void addSeconds(int numSeconds){
        int currentNumOfSeconds = this.getTimeInSeconds();
        currentNumOfSeconds += numSeconds;
        this.setTimeFromSeconds(currentNumOfSeconds);
    }

    private int getTimeInSeconds(){
        return seconds + minutes * 60 + hours * 60 * 60;
    }

    private void setTimeFromSeconds(int seconds){
        this.seconds = seconds % 60;
        this.minutes = seconds / 60 % 60;
        this.hours = seconds / 60 / 60 % 24;
    }

    @Override
    public String toString() {
        return "Time{" +
                "h=" + hours +
                ", m=" + minutes +
                ", s=" + seconds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Time)) return false;
        Time time = (Time) o;
        if (getHours() != time.getHours()) return false;
        if (getMinutes() != time.getMinutes()) return false;
        return getSeconds() == time.getSeconds();
    }
}
