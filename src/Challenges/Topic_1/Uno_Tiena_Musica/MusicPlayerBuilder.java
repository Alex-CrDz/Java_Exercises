package Challenges.Topic_1.Uno_Tiena_Musica;

import java.awt.Color;

public class MusicPlayerBuilder {

    private Color color;
    private int capacity;
    private int batteryHours;

    public MusicPlayerBuilder() {
        this.color = null;
        this.capacity = 0;
        this.batteryHours = 0;
    }

    public MusicPlayerBuilder setColor(Color color) {
        this.color = color;
        return this;
    }

    public MusicPlayerBuilder setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public MusicPlayerBuilder setBatteryHours(int batteryHours) {
        this.batteryHours = batteryHours;
        return this;
    }

    public MusicPlayer build() {
        return new MusicPlayer(color, capacity, batteryHours);
    }
}
