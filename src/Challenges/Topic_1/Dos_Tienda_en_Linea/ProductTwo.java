package Challenges.Topic_1.Dos_Tienda_en_Linea;

public class ProductTwo implements Product{

    private static ProductTwo uniqueInstance;
    private final String name = "product 2";
    private double price = 5.3;

    private ProductTwo() {
    }

    public static Product getInstance() {
        if (uniqueInstance == null){
            uniqueInstance = new ProductTwo();
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
