import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class HeadOfAGang {
    private static int count = 0;
    private static int K = 0;
    private static boolean[] marked;
    private static int[] id;

    private static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    private static class Graph {
        int V;
        int E;
        List<Edge>[] adj;

        public Graph(int vertex) {
            adj = new List[vertex];
            for (int i = 0; i < vertex; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        public Graph(int vertex, int edge) {
            this(vertex);
            this.V = vertex;
            this.E = edge;
        }

        public void add(int v, int w, int weight) {
            if (adj[v].size() > 0) {
                for (Edge edge : adj[v]) {
                    if (edge.to == w) {
                        edge.weight += weight;
                    }
                }

                for (Edge edge : adj[w]) {
                    if (edge.to == v) {
                        edge.weight += weight;
                        return;
                    }
                }
            }
            Edge edge1 = new Edge(v, w, weight);
            adj[v].add(edge1);
            Edge edge2 = new Edge(w, v, weight);
            adj[w].add(edge2);
        }
    }

    private static int connectedComponent(Graph graph) {
        id = new int[graph.V];
        marked = new boolean[graph.V];

        for (int i = 0; i < graph.V; i++) {
            if (!marked[i]) {
                dfs(graph, i);
                count++;
            }
        }

        return count;
    }

    private static Map<Integer, List<Integer>> component(int count) {
        Map<Integer, List<Integer>> ans = new HashMap<>();
        for (int i = 0; i < count; i++) {
            List<Integer> list = new ArrayList<>();
            ans.put(i, list);
        }

        for (int i = 0; i < id.length; i++) {
            ans.get(id[i]).add(i);
        }

        return ans;
    }

    private static void init() {
        for (int i = 0; i < marked.length; i++) {
            marked[i] = false;
        }
    }

    private static int sumWeight(Graph graph, List<Integer> list) {
        int sum = 0;
        for (Integer i : list) {
            for (Edge edge : graph.adj[i]) {
                sum += edge.weight;
            }
        }
        return sum / 2;
    }

    private static Map<Integer, Integer> assembleResult(Graph graph, Map<Integer, List<Integer>> map) {
        Map<Integer, Integer> ans = new TreeMap<>();
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> list = entry.getValue();
            init();
            dfs(graph, list.get(0));
            if (list.size() > 2 && sumWeight(graph, list) > K) {
                int gang = gang(graph, list);
                ans.put(gang, list.size());
            }
        }
        return ans;
    }

    private static int gang(Graph graph, List<Integer> list) {
        int max = Integer.MIN_VALUE;
        int ans = -1;
        for (Integer i : list) {
            int tmpMax = 0;
            for (Edge edge : graph.adj[i]) {
                tmpMax += edge.weight;
            }
            if (tmpMax > max) {
                max = tmpMax;
                ans = i;
            }
        }

        return ans;
    }

    private static void dfs(Graph graph, int v) {
        marked[v] = true;
        id[v] = count;

        for (Edge edge : graph.adj[v]) {
            if (!marked[edge.to]) {
                dfs(graph, edge.to);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        String[] strings = new String[N * 3];
        K = Read.nextInt();
        Set<String> set = new TreeSet<>();
        for (int i = 0; i < N; i++) {
            String s1 = Read.next();
            String s2 = Read.next();
            String s3 = Read.next();
            strings[i * 3] = s1;
            strings[i * 3 + 1] = s2;
            strings[i * 3 + 2] = s3;
            set.add(s1);
            set.add(s2);
        }
        Map<Integer, String> integerStringHashMap = new HashMap<>();
        Iterator<String> iterator = set.iterator();
        for (int i = 0; i < set.size(); i++) {
            integerStringHashMap.put(i, iterator.next());
        }
        Map<String, Integer> stringIntegerMap = new HashMap<>();
        for (Map.Entry<Integer, String> entry : integerStringHashMap.entrySet()) {
            stringIntegerMap.put(entry.getValue(), entry.getKey());
        }

        Graph graph = new Graph(set.size(), N);

        for (int i = 0; i < strings.length; i += 3) {
            graph.add(stringIntegerMap.get(strings[i]), stringIntegerMap.get(strings[i + 1]), Integer.parseInt(strings[i + 2]));
        }

        Map<Integer, Integer> ans = assembleResult(graph, component(connectedComponent(graph)));

        System.out.println(ans.size());
        for (Map.Entry<Integer, Integer> entry : ans.entrySet()) {
            System.out.println(integerStringHashMap.get(entry.getKey()) + " " + entry.getValue());
        }
    }
}
