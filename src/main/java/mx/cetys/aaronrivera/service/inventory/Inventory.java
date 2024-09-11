package mx.cetys.aaronrivera.service.inventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Inventory {
    private final Logger logger = LoggerFactory.getLogger(Inventory.class);

    //@ApplicationModuleListener
    @EventListener
    //@Async
    void on(InventoryUpdatedEvent inventoryUpdatedEvent) throws InterruptedException {
        logger.info("catching the event...");
        Thread.sleep(10_000);
        logger.info("The inventory hast been updated");
    }
}
