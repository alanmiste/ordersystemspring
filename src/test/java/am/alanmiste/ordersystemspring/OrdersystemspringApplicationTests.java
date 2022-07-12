package am.alanmiste.ordersystemspring;

import am.alanmiste.ordersystemspring.shop.ShopService;
import am.alanmiste.ordersystemspring.shop.order.Order;
import am.alanmiste.ordersystemspring.shop.order.OrderRepo;
import am.alanmiste.ordersystemspring.shop.product.Product;
import am.alanmiste.ordersystemspring.shop.product.ProductRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrdersystemspringApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void getProduct() {
        //given
        ProductRepo productRepo = new ProductRepo();
        OrderRepo orderRepo = new OrderRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);

        //when
        Product actual = shopService.getProduct(2);

        //then
        assertThat(actual).isEqualTo(new Product(2, "Banane"));
    }

    @Test
    void listProducts() {
        //given
        ProductRepo productRepo = new ProductRepo();
        OrderRepo orderRepo = new OrderRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);

        //when
        List<Product> actual = shopService.listProducts();

        //then
        List<Product> expected = List.of(
                new Product(1, "Apfel"),
                new Product(2, "Banane"),
                new Product(3, "Zitrone"),
                new Product(4, "Mandarine")
        );
        assertThat(actual)
                .hasSameElementsAs(expected)
                .hasSize(expected.size());
    }

    @Test
    void addAndGetOrder() {
        //given
        ProductRepo productRepo = new ProductRepo();
        OrderRepo orderRepo = new OrderRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);

        //when
        shopService.addOrder(106, List.of(1, 3, 4));
        Order actual = shopService.getOrder(106);

        //then
        assertThat(actual)
                .isEqualTo(
                        new Order(
                                106,
                                List.of(
                                        new Product(1, "Apfel"),
                                        new Product(3, "Zitrone"),
                                        new Product(4, "Mandarine")
                                )
                        )
                );
    }

    @Test
    void addAndListOrders() {
        //given
        ProductRepo productRepo = new ProductRepo();
        OrderRepo orderRepo = new OrderRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);

        //when
        shopService.addOrder(106, List.of(1, 3, 4));
        List<Order> actual = shopService.listOrders();

        //then
        List<Order> expected = List.of(
                new Order(
                        106,
                        List.of(
                                new Product(1, "Apfel"),
                                new Product(3, "Zitrone"),
                                new Product(4, "Mandarine")
                        )
                )
        );
        assertThat(actual)
                .hasSameElementsAs(expected)
                .hasSize(expected.size());
    }


    @Test
    void expectExceptionWhenReferencingNonexistingProduct() {
        //given
        ProductRepo productRepo = new ProductRepo();
        OrderRepo orderRepo = new OrderRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);

        //when
        try {
            shopService.addOrder(106, List.of(999));
            Assertions.fail("Expected exception was not thrown");
        } catch (NoSuchElementException e) {
            // perfect, exception was thrown
        }
    }

}
