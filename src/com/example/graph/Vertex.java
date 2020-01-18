package com.example.graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private Character name;
    private List<Vertex> neighbors;

    public Vertex(Character name) {
        this.name = name;
        this.neighbors = new ArrayList<>();
    }

    public void addNeighbor(Vertex vertex) {
        this.neighbors.add(vertex);
    }

    public Character getName() {
        return name;
    }

    public List<Vertex> getNeighbors() {
        return neighbors;
    }
}
