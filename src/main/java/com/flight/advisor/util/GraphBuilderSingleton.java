package com.flight.advisor.util;

import es.usc.citius.hipster.graph.GraphBuilder;

public final class GraphBuilderSingleton {

    public static GraphBuilder<Integer, Double> instance;

    private GraphBuilderSingleton() {
    }

    public static GraphBuilder<Integer, Double> getInstance() {
        if (instance == null) {
            synchronized (GraphBuilder.class) {
                if (instance == null) {
                    instance = GraphBuilder.create();
                }
            }
        }

        return instance;
    }
}
