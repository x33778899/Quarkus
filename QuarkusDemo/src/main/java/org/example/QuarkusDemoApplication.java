package org.example;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class QuarkusDemoApplication {

    public static void main(String[] args) {
        Quarkus.run(args);
    }
}