package Challenges.Topic_1.Dos_Tienda_en_Linea;

import java.util.ArrayList;
import java.util.List;

public class SubscribedUsersHandler implements Subject {

    private static SubscribedUsersHandler uniqueInstance;
    private List<User> subscribedUsers;

    private SubscribedUsersHandler() {
        this.subscribedUsers = new ArrayList<User>();
    }

    public static SubscribedUsersHandler getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new SubscribedUsersHandler();
        return uniqueInstance;
    }

    public void printSubscribedUsers() {
        if (subscribedUsers.isEmpty()) {
            System.out.println("-- there's any user subscribed");
        } else {
            System.out.println("");
            System.out.println("Subscribed users:");
            for (int i = 0; i < subscribedUsers.size(); i++) {
                System.out.println(i + 1 + "\tUser " + subscribedUsers.get(i).getNumUser());
            }
        }
    }

    @Override
    public void subscribe(Observer observer) {
        subscribedUsers.add((User) observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        subscribedUsers.remove(observer);
    }

    @Override
    public void notifyAll(String message) {
        System.out.println("- Notify subscribed users");
        for (Observer obs : subscribedUsers) {
            System.out.println("  |-- " + obs.update(message));
        }
    }

    public List<User> getSubscribedUsers() {
        return subscribedUsers;
    }
}
