package am.alanmiste.ordersystemspring.shop.order;

//import model.ordersystem.shop.product.Product;
import am.alanmiste.ordersystemspring.shop.product.Product;

import java.util.List;

public record Order(
        int id,
        List<Product> products
) {
}