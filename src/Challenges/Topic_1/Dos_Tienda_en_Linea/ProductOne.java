package Challenges.Topic_1.Dos_Tienda_en_Linea;

public class ProductOne implements Product {

    private static ProductOne uniqueInstance;
    private final String name = "product 1";
    private double price = 2.5;

    private ProductOne() {
    }

    public static Product getInstance() {
        if (uniqueInstance == null){
            uniqueInstance = new ProductOne();
        }
        return uniqueInstance;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(double newPrice) {
        this.price = newPrice;
    }
}
