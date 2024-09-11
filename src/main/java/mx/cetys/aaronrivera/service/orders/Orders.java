package mx.cetys.aaronrivera.service.orders;

import mx.cetys.aaronrivera.service.inventory.InventoryUpdatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.transaction.annotation.Transactional;
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
//@Transactional
@RequestMapping("/orders")
class OrdersController{
    private final Logger logger = LoggerFactory.getLogger(OrdersController.class);
    private final OrderRepository repository;

    private final ApplicationEventPublisher publisher;


    OrdersController(OrderRepository repository, ApplicationEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    @GetMapping
    Collection<Order> getOrders(){
        return  this.repository.findAll();
    }

    @PostMapping
    Order create(@RequestBody Order order){
        var saved = this.repository.save(order);
        logger.info("Saved Order: {}",saved);
        saved
                .lineItems()
                        .forEach(li -> {
                            publisher.publishEvent(new InventoryUpdatedEvent(li.product(),
                                    li.quantity()));
                        });
        return saved;
    }
}

interface OrderRepository extends
        ListCrudRepository<Order, Integer>{}

@Table("orders")
record Order (@Id Integer id, Set<LineItem> lineItems) {}

@Table("order_line_items")
record LineItem (@Id Integer id, int product, int quantity){}


