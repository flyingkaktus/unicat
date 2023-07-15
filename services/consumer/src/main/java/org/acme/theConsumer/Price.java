package org.acme.theConsumer;

import jakarta.persistence.Entity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Price extends PanacheEntity {

    private long time;
    private String key;
    private double price;

    public Price() {
    }

    public Price(long time, String key, double price) {
        this.time = time;
        this.key = key;
        this.price = price;
    }

    public long getTime() {
        return time;
    }

    public String getKey() {
        return key;
    }

    public double getPrice() {
        return price;
    }

}