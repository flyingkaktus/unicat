package org.acme.theConsumer;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Queue;

import org.apache.kafka.clients.consumer.ConsumerRecord;

@Path("/api")
@ApplicationScoped
public class PriceConsumer {

    // füge einen logger hinzu
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PriceConsumer.class);

    // Erstelle eine Queue mit 100 Einträgen
    Queue<ConsumerRecord<String, Double>> queue = new java.util.concurrent.ConcurrentLinkedQueue<>();

    @Incoming("prices")
    public void consume(ConsumerRecord<String, Double> record) {
        String key = record.key(); // Can be `null` if the incoming record has no key
        Double value = record.value(); // Can be `null` if the incoming record has no value
        String topic = record.topic();
        int partition = record.partition();

        // füge einen logger hinzu
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