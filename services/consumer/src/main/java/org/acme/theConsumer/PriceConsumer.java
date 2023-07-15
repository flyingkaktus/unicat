package org.acme.theConsumer;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Queue;

import org.apache.kafka.clients.consumer.ConsumerRecord;

@Path("/api")
@ApplicationScoped
public class PriceConsumer {

    @Inject
    EntityManager entityManager;

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PriceConsumer.class);

    Queue<ConsumerRecord<String, Double>> queue = new java.util.concurrent.ConcurrentLinkedQueue<>();

    @Incoming("prices")
    @Transactional
    public void consume(ConsumerRecord<String, Double> record) {

        String key = record.key();
        Double value = record.value();
        String topic = record.topic();
        int partition = record.partition();
        Price price = new Price(System.currentTimeMillis(), record.key(), record.value());
        price.persist();

        LOGGER.info("Key: " + key + ", value: " + value + ", topic: " + topic + ", partition: " + partition);
        queue.add(record);
    }

    @Path("/prices")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        return queue.toString();
    }

}