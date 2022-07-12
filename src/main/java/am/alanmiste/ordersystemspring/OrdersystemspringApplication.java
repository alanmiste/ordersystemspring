package am.alanmiste.ordersystemspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import am.alanmiste.ordersystemspring.shop.ShopService;
import am.alanmiste.ordersystemspring.shop.order.Order;
import am.alanmiste.ordersystemspring.shop.order.OrderRepo;
import am.alanmiste.ordersystemspring.shop.product.Product;
import am.alanmiste.ordersystemspring.shop.product.ProductRepo;

import java.util.List;

@SpringBootApplication
public class OrdersystemspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdersystemspringApplication.class, args);

        ProductRepo productRepo = new ProductRepo();
        OrderRepo orderRepo = new OrderRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);

        Product product = shopService.getProduct(3);
        System.out.println(product);

        List<Product> products = shopService.listProducts();
        System.out.println(products);

        shopService.addOrder(101, List.of(2,3));
        shopService.addOrder(102, List.of(1));

        Order order = shopService.getOrder(101);
        System.out.println(order);

        List<Order> orders = shopService.listOrders();
        System.out.println(orders);


        shopService.addOrder(123, List.of(999));

    }

}
