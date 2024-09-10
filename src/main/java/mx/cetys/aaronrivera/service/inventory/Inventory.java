package mx.cetys.aaronrivera.service.inventory;

import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class Inventory {
    private final Logger logger = LoggerFactory.getLogger(Inventory.class);
     @EventListener
    void on (InventoryUpdatedEvent inventoryUpdatedEvent){

     }
}
