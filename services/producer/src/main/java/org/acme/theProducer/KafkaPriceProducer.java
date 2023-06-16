package org.acme.theProducer;

import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

@ApplicationScoped
public class KafkaPriceProducer {

    @Inject
    PriceEmitter priceEmitter;

    Logger LOGGER = Main.getLogger();
    
    KafkaPriceProducer(PriceEmitter priceEmitter) {
        LOGGER.info("KafkaPriceProducer constructor");
        this.priceEmitter = priceEmitter;
    }

   
    public void start() {
/*         priceEmitter.generate().subscribe().with(price -> {
            LOGGER.info(KafkaPriceProducer.class.getName() + " - Generated price: " + price);
        }); */
        LOGGER.info("Starting KafkaPriceProducer");
    }

    @PreDestroy
    public void cleanup() {
        LOGGER.info("Closing KafkaPriceProducer");
    }

}
