package com.example.graph;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedList;

public class Graph {
private Vertex root;

    public void dfs() {
        ArrayDeque<Vertex> stack = new ArrayDeque<>();
        HashSet<Vertex> visited = new HashSet<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Vertex current = stack.pop();
            System.out.println(current.getName());
            visited.add(current);
            for (Vertex vertex: current.getNeighbors()) {
                if (!visited.contains(vertex)) {
                    stack.push(vertex);
                }
            }
        }
    }

    public void bfs() {
        LinkedList<Vertex> queue = new LinkedList<>();
        HashSet<Vertex> visited = new HashSet<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Vertex current = queue.remove();
            System.out.println(current.getName());
            visited.add(current);
            for (Vertex vertex: current.getNeighbors()) {
                if (!visited.contains(vertex)) {
                    queue.add(vertex);
                }
            }
        }
    }

    private void dfsRecursive(Vertex vertex) {
        HashSet<Vertex> visited = new HashSet<>();
        visited.add(vertex);
        for (Vertex v: vertex.getNeighbors()) {
            if (!visited.contains(v)) {
                dfsRecursive(v);
            }
        }
    }
}
