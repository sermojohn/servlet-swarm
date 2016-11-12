package gr.iserm.java.web;

import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.undertow.UndertowFraction;

public class Main {
    public static void main(String... args) throws Exception {
        UndertowFraction undertowFraction = UndertowFraction
                .createDefaultFraction().httpPort(8080);

        Swarm swarm = new Swarm().fraction(undertowFraction);

        swarm.start();

        swarm.deploy();
    }
}