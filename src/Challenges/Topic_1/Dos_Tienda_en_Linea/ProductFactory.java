package Challenges.Topic_1.Dos_Tienda_en_Linea;

public class ProductFactory {

    public Product getProduct(ProductType productType){
        switch (productType){
            case ONE -> {
                return ProductOne.getInstance();
            }
            case TWO -> {
                return ProductTwo.getInstance();
            }
            case THREE -> {
                return ProductThree.getInstance();
            }
            default -> {
                return null;
            }
        }

    }
}
