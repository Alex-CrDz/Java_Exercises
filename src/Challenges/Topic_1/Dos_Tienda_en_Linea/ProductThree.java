package Challenges.Topic_1.Dos_Tienda_en_Linea;

public class ProductThree implements Product {

    private static ProductThree uniqueInstance;
    private final String name = "product 3";
    private double price = 8.2;

    private ProductThree() {
    }

    public static Product getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ProductThree();
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
