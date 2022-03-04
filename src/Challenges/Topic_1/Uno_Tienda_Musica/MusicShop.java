package Challenges.Topic_1.Uno_Tienda_Musica;

import java.util.*;

public class MusicShop {

    private static HashMap<String, MusicPlayer> availableMusicPlayers;

    public static void restockMusicPlayers() {  //  this method use a builder to populate the collection of avaliableMusiclPlayers to sell
        System.out.println("-- restock in process");
        MusicPlayerBuilder musicPlayerBuilder = new MusicPlayerBuilder();
        availableMusicPlayers.put("green 8gb 5hr", musicPlayerBuilder
                .setColor(MusicPlayer.GREEN)
                .setCapacity(MusicPlayer.GB8)
                .setBatteryHours(MusicPlayer.BATTERY_5_HOURS)
                .build());
        availableMusicPlayers.put("green 8gb 8hr", musicPlayerBuilder
                .setColor(MusicPlayer.GREEN)
                .setCapacity(MusicPlayer.GB8)
                .setBatteryHours(MusicPlayer.BATTERY_8_HOURS)
                .build());
        availableMusicPlayers.put("green 16gb 5hr", musicPlayerBuilder
                .setColor(MusicPlayer.GREEN)
                .setCapacity(MusicPlayer.GB16)
                .setBatteryHours(MusicPlayer.BATTERY_5_HOURS)
                .build());
        availableMusicPlayers.put("green 16gb 8hr", musicPlayerBuilder
                .setColor(MusicPlayer.GREEN)
                .setCapacity(MusicPlayer.GB16)
                .setBatteryHours(MusicPlayer.BATTERY_8_HOURS)
                .build());
        availableMusicPlayers.put("blue 8gb 5hr", musicPlayerBuilder
                .setColor(MusicPlayer.BlUE)
                .setCapacity(MusicPlayer.GB8)
                .setBatteryHours(MusicPlayer.BATTERY_5_HOURS)
                .build());
        availableMusicPlayers.put("blue 8gb 8hr", musicPlayerBuilder
                .setColor(MusicPlayer.BlUE)
                .setCapacity(MusicPlayer.GB8)
                .setBatteryHours(MusicPlayer.BATTERY_8_HOURS)
                .build());
        availableMusicPlayers.put("blue 16gb 5hr", musicPlayerBuilder
                .setColor(MusicPlayer.BlUE)
                .setCapacity(MusicPlayer.GB16)
                .setBatteryHours(MusicPlayer.BATTERY_5_HOURS)
                .build());
        availableMusicPlayers.put("blue 16gb 8hr", musicPlayerBuilder
                .setColor(MusicPlayer.BlUE)
                .setCapacity(MusicPlayer.GB16)
                .setBatteryHours(MusicPlayer.BATTERY_8_HOURS)
                .build());
    }

    public static List<String> printAvailableStock() {
        List<String> availableList = new ArrayList<String>();
        if (availableMusicPlayers == null)
            availableMusicPlayers = new HashMap<String, MusicPlayer>();
        if (!availableMusicPlayers.isEmpty()) {
            availableMusicPlayers.forEach((s, musicPlayer) -> {
                availableList.add(s);
            });
            Collections.sort(availableList, Collections.reverseOrder());
            System.out.println("Available for reserve (color, capacity, battery life)");
            for (int i = 0; i < availableList.size(); i++) {
                System.out.println(i + 1 + ".\tMusicPlayer\t" + availableList.get(i));
            }
            return availableList;
        } else {
            restockMusicPlayers();
            return printAvailableStock();
        }
    }

    public static MusicPlayer reserve(String key) {
        if (availableMusicPlayers.containsKey(key)) {
            return availableMusicPlayers.remove(key);
        }
        System.out.println("-- not available --");
        return null;
    }

    public static void run() {
        Scanner read = new Scanner(System.in);
        int inputToReserve;
        String keyReserve;
        MusicPlayer reserved;
        while (true) {
            System.out.println("\nINFO:\tFor the reserve of your music player, please select one of the next list.\n" +
                    "\t\tStock will be automatically restock when all reserves are exhausted.\n" +
                    "\t\t(type 0 to exit)");
            List<String> availableList = printAvailableStock();
            System.out.print("\nReserve item number: ");
            try {
                inputToReserve = read.nextInt();
                if (inputToReserve < 0 || inputToReserve > availableList.size())
                    throw new InputMismatchException();
            } catch (InputMismatchException e) {
                read.nextLine(); //  flush input buffer from scanner object
                continue;
            }
            if (inputToReserve == 0)
                break;
            keyReserve = availableList.get(inputToReserve - 1);
            reserved = reserve(keyReserve);
            System.out.println("\nReserved item:");
            System.out.println(reserved.toString());
        }
    }

    public static void main(String[] args) {
        run();
    }
}
