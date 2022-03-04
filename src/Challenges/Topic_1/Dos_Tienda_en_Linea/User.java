package Challenges.Topic_1.Dos_Tienda_en_Linea;

public class User implements Observer {

    private int numUser;

    public User(int numUser) {
        this.numUser = numUser;
    }

    public int getNumUser() {
        return numUser;
    }

    @Override
    public String update(String message) {
        return "Message for user " + this.numUser + ": " + message;
    }
}
