package am.alanmiste.ordersystemspring.shop.order;

import am.alanmiste.ordersystemspring.shop.product.Product;
import am.alanmiste.ordersystemspring.shop.product.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final ProductService productService;
    private final OrderRepo orderRepo;

    public OrderService(
            ProductService productService,
            OrderRepo orderRepo) {
        this.productService = productService;
        this.orderRepo = orderRepo;
    }

    public void addOrder(int orderId, List<Integer> productIds) {
        List<Product> products = new ArrayList<>();
        for (int productId : productIds) {
            Product product = productService.getProduct(productId);
            products.add(product);
        }

        Order order = new Order(orderId, products);
        orderRepo.addOrder(order);
    }

    public Order getOrder(int orderId) {
        return orderRepo.getOrder(orderId);
    }

    public List<Order> listOrders() {
        return orderRepo.listOrders();
    }
}