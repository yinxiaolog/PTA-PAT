import util.Read;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ForwardsOnWeibo {
    private static int vertex;

    private static class Graph {
        int vertex;
        int edge;
        List<Integer>[] adj;

        public Graph(int vertex) {
            this.vertex = vertex;
            adj = new List[vertex];
            for (int i = 0; i < vertex; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        public void addEdge(int v, int w) {
            adj[v].add(w);
            edge++;
        }

        public List<Integer> getAdj(int v) {
            return adj[v];
        }
    }

    private static int bfs(Graph graph, int v, int maxLevel) {
        boolean[] marked = new boolean[vertex];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(v);
        marked[v] = true;
        int ans = 0;
        for (int i = 0; i < maxLevel; i++) {
            Queue tmpQueue = new ArrayDeque();
            while (!queue.isEmpty()) {
                int w = queue.poll();
                //marked[w] = true;
                //ns++;

                for (Integer integer : graph.getAdj(w)) {
                    if (!marked[integer]) {
                        ans++;
                        tmpQueue.add(integer);
                        marked[integer] = true;
                    }
                }
            }
            queue = tmpQueue;
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        vertex = N;
        int maxLevel = Read.nextInt();
        Graph graph = new Graph(vertex);
        for (int i = 0; i < N; i++) {
            int M = Read.nextInt();
            for (int j = 0; j < M; j++) {
                graph.addEdge(Read.nextInt() - 1, i);
            }
        }

        int K = Read.nextInt();
        for (int i = 0; i < K; i++) {
            int v = Read.nextInt() - 1;
            System.out.println(bfs(graph, v, maxLevel));
        }
    }
}
