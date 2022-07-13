package am.alanmiste.ordersystemspring.shop.order;

import am.alanmiste.ordersystemspring.shop.product.Product;
import am.alanmiste.ordersystemspring.shop.product.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;



import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Test
    void getOrder() {
        //given
        ProductService productService = mock(ProductService.class);
        OrderRepo orderRepo = mock(OrderRepo.class);
        OrderService orderService = new OrderService(productService, orderRepo);
        when(orderRepo.getOrder(106)).thenReturn(new Order(
                106,
                List.of(
                        new Product(1, "Apfel"),
                        new Product(3, "Zitrone"),
                        new Product(4, "Mandarine")
                )
        ));
        //when
        Order actual = orderService.getOrder(106);

        //then
        assertThat(actual)
                .isEqualTo(new Order(
                        106,
                        List.of(
                                new Product(1, "Apfel"),
                                new Product(3, "Zitrone"),
                                new Product(4, "Mandarine")
                        )
                ));
    }

    @Test
    void addOrder() {
        //given
        ProductService productService = mock(ProductService.class);
        OrderRepo orderRepo = mock(OrderRepo.class);
        OrderService orderService = new OrderService(productService, orderRepo);
        when(productService.getProduct(1)).thenReturn(new Product(1, "Apfel"));
        when(productService.getProduct(3)).thenReturn(new Product(3, "Zitrone"));
        when(productService.getProduct(4)).thenReturn(new Product(4, "Mandarine"));

        //when
        orderService.addOrder(106, List.of(1, 3, 4));

        //then
        verify(orderRepo).addOrder(new Order(
                106,
                List.of(
                        new Product(1, "Apfel"),
                        new Product(3, "Zitrone"),
                        new Product(4, "Mandarine")
                )
        ));
    }

    @Test
    void listOrders() {
        //given
        ProductService productService = mock(ProductService.class);
        OrderRepo orderRepo = mock(OrderRepo.class);
        OrderService orderService = new OrderService(productService, orderRepo);
        when(orderRepo.listOrders()).thenReturn(List.of(
                new Order(
                        106,
                        List.of(
                                new Product(1, "Apfel"),
                                new Product(3, "Zitrone"),
                                new Product(4, "Mandarine")
                        )
                )
        ));
        //when
        List<Order> actual = orderService.listOrders();

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
        ProductService productService = mock(ProductService.class);
        OrderRepo orderRepo = mock(OrderRepo.class);
        OrderService orderService = new OrderService(productService, orderRepo);
        when(productService.getProduct(999)).thenThrow(new NoSuchElementException());
        //when
        try {
            orderService.addOrder(106, List.of(999));
            Assertions.fail("Expected exception was not thrown");
        } catch (NoSuchElementException e) {
            // perfect, exception was thrown
        }
    }
}


//
//class OrderServiceTest {
//
//    @Test
//    void getOrder(){
////        List<Product> product;
//        OrderRepo orderRepo = mock(OrderRepo.class);
//        ProductService productService = mock(ProductService.class);
//        when(orderRepo.getOrder(101)).thenReturn(
//                new Order(101,List.of(new Product(1,"Banane"))));
//        OrderService orderService = new OrderService(productService,orderRepo);
//
//        //When
//        Order actual = orderService.getOrder(101);
//
//        //Then
//        assertThat(actual).isEqualTo(new Order(101,List.of(new Product(1,"Banane"))));
//    }
//
//}