package am.alanmiste.ordersystemspring.shop.order;

import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping(path = "/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "{id}")
    public void addOrder(
            @PathVariable int id,
            @RequestBody List<Integer> productIds) {
        orderService.addOrder(id, productIds);
    }

    @GetMapping(path = "{id}")
    public Order getOrder(
            @PathVariable int id) {
        return orderService.getOrder(id);
    }

    @GetMapping
    public List<Order> listOrders() {
        return orderService.listOrders();
    }

}