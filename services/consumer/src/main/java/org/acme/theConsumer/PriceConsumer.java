package org.acme.theConsumer;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.kafka.clients.consumer.ConsumerRecord;

@ApplicationScoped
public class PriceConsumer {

    @Incoming("prices")
    public void consume(ConsumerRecord<String, Double> record) {
        String key = record.key(); // Can be `null` if the incoming record has no key
        Double value = record.value(); // Can be `null` if the incoming record has no value
        String topic = record.topic();
        int partition = record.partition();

        printAll(key, value, topic, partition);
    
    }

    void printAll(String key, Double value, String topic, int partition) {
        System.out.println("Key: " + key + ", value: " + value + ", topic: " + topic + ", partition: " + partition);
    }

}