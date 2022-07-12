package am.alanmiste.ordersystemspring.shop.product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Test
    void getProduct() {
        //given
        ProductRepo productRepo = mock(ProductRepo.class);
        when(productRepo.getProduct(2)).thenReturn(new Product(2, "Banane"));
        ProductService productService = new ProductService(productRepo);

        //when
        Product actual = productService.getProduct(2);

        //then
        assertThat(actual).isEqualTo(new Product(2, "Banane"));
    }

    @Test
    void listProducts() {
        //given
        ProductRepo productRepo = new ProductRepo();
        ProductService productService = new ProductService(productRepo);

        //when
        List<Product> actual = productService.listProducts();

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

}