package Challenges.Topic_1.Uno_Tiena_Musica;

import java.awt.*;

public class MusicPlayer {

    public static final Color BlUE = new Color(Color.blue.getRGB());
    public static final Color GREEN = new Color(Color.green.getRGB());
    public static final int GB8 = 8;
    public static final int GB16 = 16;
    public static final int BATTERY_5_HOURS = 5;
    public static final int BATTERY_8_HOURS = 8;

    private Color color;
    private int capacity;
    private int batteryHours;

    public MusicPlayer(Color color, int capacity, int batteryHours) {
        this.color = color;
        this.capacity = capacity;
        this.batteryHours = batteryHours;
    }

    @Override
    public String toString() {
        String details = "MusicPlayer specs:\n" +
                "\tColor:\t\t\t";
        if (color.equals(Color.blue))
            details += "Blue\n";
        else
            details += "Green\n";
        details += "\tCapacity:\t\t" + capacity + " GB\n" +
                "\tBattery Life:\t" + batteryHours + " hours\n" +
                '\n';
        return details;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getBatteryHours() {
        return batteryHours;
    }

    public void setBatteryHours(int batteryHours) {
        this.batteryHours = batteryHours;
    }
}
