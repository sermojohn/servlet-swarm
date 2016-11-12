package gr.iserm.java.web;

import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.io.IOFraction;
import org.wildfly.swarm.undertow.UndertowFraction;

public class Main {
    public static void main(String... args) throws Exception {
        UndertowFraction undertowFraction = UndertowFraction
                .createDefaultFraction().httpPort(8080);

        IOFraction ioFraction = new IOFraction().applyDefaults();
        ioFraction.subresources().worker("default").taskMaxThreads(1);

        Swarm swarm = new Swarm().fraction(undertowFraction)
                .fraction(ioFraction);

        swarm.start();

        swarm.deploy();
    }
}