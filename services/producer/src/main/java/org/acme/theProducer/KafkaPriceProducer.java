package org.acme.theProducer;

import jakarta.annotation.PostConstruct;
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


    @PostConstruct
    public void start() {
        priceEmitter.generate().subscribe().with(price -> {
            LOGGER.info("Generated price: " + price);
        });
    }

    @PreDestroy
    public void cleanup() {
        LOGGER.info("Closing KafkaPriceProducer");
    }

}
