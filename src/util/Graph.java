package util;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    int V;
    int E;
    List<Integer>[] adj;

    public Graph(int vertex) {
        for (int i = 0; i < vertex; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public Graph(int vertex, int edge) {
        this(vertex);
        V = vertex;
        E = edge;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    public Iterable<Integer> getAdj(int v) {
        return adj[v];
    }
}
