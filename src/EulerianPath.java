import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EulerianPath {
    private static class Graph {
        List<Integer>[] adj;

        public Graph(int v) {
            adj = new List[v];
            for (int i = 0; i < v; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        public void addEdge(int v, int w) {
            adj[v].add(w);
            adj[w].add(v);
        }
    }

    private static List<Integer> getDegrees(Graph graph) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < graph.adj.length; i++) {
            ans.add(graph.adj[i].size());
        }
        return ans;
    }

    private static boolean[] marked;
    private static int count = 0;

    private static void dfs(Graph graph, int v) {
        count++;
        marked[v] = true;
        for (Integer i : graph.adj[v]) {
            if (!marked[i]) {
                dfs(graph, i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        int M = Read.nextInt();
        Graph graph = new Graph(N + 1);
        marked = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            int v = Read.nextInt();
            int w = Read.nextInt();
            graph.addEdge(v, w);
        }

        List<Integer> list = getDegrees(graph);

        int odd = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(' ');
            if (list.get(i) % 2 != 0) {
                odd++;
            }
        }
        System.out.println(sb.toString().trim());
        dfs(graph, 1);
        if (count != N) {
            System.out.println("Non-Eulerian");
            return;
        }

        if (odd == 0) {
            System.out.println("Eulerian");
        } else if (odd == 2) {
            System.out.println("Semi-Eulerian");
        } else {
            System.out.println("Non-Eulerian");
        }
    }
}
