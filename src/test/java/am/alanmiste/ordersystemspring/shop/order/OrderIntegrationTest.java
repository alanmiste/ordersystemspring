package am.alanmiste.ordersystemspring.shop.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getOneProduct() throws Exception {
        mockMvc
                .perform(
                        post("/api/orders/987")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        [1,2]
                                        """)
                )

                .andExpect(status().isOk())
                .andExpect(content().string(""));
        mockMvc
                .perform(
                        get("/api/orders/987")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        [1,2]
                                        """)
                )

                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {"id":987,"products":[{"id":1,"name":"Apfel"},{"id":2,"name":"Banane"}]}
                        """));

    }
}
