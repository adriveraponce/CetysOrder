package mx.cetys.aaronrivera.service.orders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Set;

public class Orders {
}

@RestController
@ResponseBody
@RequestMapping("/orders")
class OrdersController{
    private final Logger logger = LoggerFactory.getLogger(OrdersController.class);
    private final OrderRepository repository;

    OrdersController(OrderRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    Order create(@RequestBody Order order){
        var saved = this.repository.save(order);
        logger.info("Saved Order: {}",saved);
        return saved;
    }
}

interface OrderRepository extends
        ListCrudRepository<Order, Integer>{}

@Table("orders")
record Order (@Id Integer id, Set<LineItem> lineItems) {}

@Table("order_line_items")
record LineItem (@Id Integer id, int product, int quantity){}


