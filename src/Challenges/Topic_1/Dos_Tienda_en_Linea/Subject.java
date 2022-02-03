package Challenges.Topic_1.Dos_Tienda_en_Linea;

public interface Subject {
    void subscribe(Observer observer);

    void unsubscribe(Observer observer);

    void notifyAll(String message);
}
