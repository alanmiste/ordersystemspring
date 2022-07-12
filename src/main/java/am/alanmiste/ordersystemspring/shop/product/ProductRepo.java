package am.alanmiste.ordersystemspring.shop.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductRepo {

    private final Map<Integer, Product> products = Map.of(
            1, new Product(1, "Apfel"),
            2, new Product(2, "Banane"),
            3, new Product(3, "Zitrone"),
            4, new Product(4, "Mandarine")
    );

    public Product getProduct(int id) {
        return products.get(id);
    }

    public List<Product> listProducts() {
        return new ArrayList<>(products.values());
    }
}