package org.acme.theProducer;

import java.time.Duration;
import java.util.Random;

import org.jboss.logging.Logger;

import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.mutiny.Multi;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PriceEmitter {
    
    Logger LOGGER = Main.getLogger();

    private final Random random = new Random();

    @Outgoing("generated-price")
    public Multi<Double> generate() {
        // Build an infinite stream of random prices
        // It emits a price every 5 seconds
        return Multi.createFrom().ticks().every(Duration.ofSeconds(5))
            .map(x -> {
                double price = random.nextDouble();
                LOGGER.info("Generated price: " + price);
                return price;
            });
    }
}
