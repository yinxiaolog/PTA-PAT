import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HamiltonianCycle {
    private static int N;
    private static Map<Integer, Integer> map = new HashMap<>();

    private static class Graph {
        List<Integer>[] adj;

        public Graph(int v) {
            adj = new List[v + 1];
            for (int i = 0; i <= v; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        public void addEdge(int v, int w) {
            adj[v].add(w);
            adj[w].add(v);
        }
    }

    private static String solve(Graph graph, List<Integer> list) {
        if (list.size() != N + 1) {
            return "NO";
        }

        if (! list.get(0).equals(list.get(list.size() - 1))) {
            return "NO";
        }

        int size = list.size();

        if (list.stream().distinct().collect(Collectors.toList()).size() != size - 1) {
            return "NO";
        }

        for (int i = 0; i < list.size() - 1; i++) {
            List<Integer> tmp = graph.adj[list.get(i)];
            if (!tmp.contains(list.get(i + 1))) {
                return "NO";
            }
        }

        return "YES";
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        N = Read.nextInt();
        int M = Read.nextInt();
        Graph graph = new Graph(N);
        for (int i = 0; i < M; i++) {
            int v1 = Read.nextInt();
            int v2 = Read.nextInt();
            graph.addEdge(v1, v2);
        }

        int K = Read.nextInt();
        for (int i = 0; i < K; i++) {
            int V = Read.nextInt();
            List<Integer> list = new ArrayList<>();
            for (int v = 0; v < V; v++) {
                list.add(Read.nextInt());
            }
            System.out.println(solve(graph, list));
        }
        //System.out.println();
    }
}
