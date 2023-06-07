package org.acme.theProducer;

import io.quarkus.runtime.annotations.QuarkusMain;

import org.jboss.logging.Logger;

import io.quarkus.runtime.Quarkus;

@QuarkusMain  
public class Main {
    
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    static Logger getLogger() {
        LOGGER.info("Getting logger");
        return LOGGER;
    }

    public static void main(String ... args) {

        LOGGER.info("Running main method");
        Quarkus.run(args); 
    }
}
