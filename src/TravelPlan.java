import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

public class TravelPlan {
    private class Edge {
        int weight;
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int weight, int cost) {
            this.from = from;
            this.to = to;
            this.weight = weight;
            this.cost = cost;
        }
    }

    private class Graph {
        public List<Edge>[] adj;
        public int edge;
        public int vertex;

        public Graph(int vertex, int edge) {
            this.edge = edge;
            adj = new List[vertex];
            for (int i = 0; i < vertex; i++) {
                adj[i] = new ArrayList<>();
            }
            this.vertex = vertex;
        }

        public void addEdge(int v, int w, int weight, int cost) {
            Edge v2W = new Edge(v, w, weight, cost);
            Edge w2V = new Edge(w, v, weight, cost);
            adj[v].add(v2W);
            adj[w].add(w2V);
        }
    }

    List<Edge>[] edgeTo;
    int[] distTo;
    int s;
    PriorityQueue<VertexAndSp> pq = new PriorityQueue<>();

    private class VertexAndSp implements Comparable<VertexAndSp> {
        int vertex;
        int sp;

        public VertexAndSp(int vertex, int sp) {
            this.vertex = vertex;
            this.sp = sp;
        }

        @Override
        public int compareTo(VertexAndSp another) {
            if (sp < another.sp) {
                return -1;
            } else if (sp > another.sp) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public boolean equals(Object another) {
            if (this == another) {
                return true;
            }

            if (!(another instanceof VertexAndSp)) {
                return false;
            } else {
                VertexAndSp vertexAndSp = (VertexAndSp) another;
                if (this.vertex == vertexAndSp.vertex && this.sp == vertexAndSp.sp) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(vertex, sp);
        }
    }

    private void initArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.MAX_VALUE;
        }
        array[s] = 0;
    }

    private void relax(Graph graph, VertexAndSp vertexAndSp) {
        for (Edge edge : graph.adj[vertexAndSp.vertex]) {
            int w = edge.to;
            if (distTo[w] > distTo[vertexAndSp.vertex] + edge.weight) {
                distTo[w] = distTo[vertexAndSp.vertex] + edge.weight;
                edgeTo[w].clear();
                edgeTo[w].add(edge);
                VertexAndSp vertexAndSp1 = new VertexAndSp(w, distTo[w]);
                if (!pq.contains(vertexAndSp1)) {
                    pq.add(vertexAndSp1);

                }
            } else if (distTo[w] == distTo[vertexAndSp.vertex] + edge.weight) {
                edgeTo[w].add(edge);
            }
        }
    }

    private void shortestPath(Graph graph) {
        edgeTo = new List[graph.vertex];
        for (int i = 0; i < edgeTo.length; i++) {
            edgeTo[i] = new ArrayList();
        }
        distTo = new int[graph.vertex];
        initArray(distTo);

        pq.add(new VertexAndSp(s, 0));
        while (!pq.isEmpty()) {
            relax(graph, pq.poll());
        }
    }

    List<Integer> path = new ArrayList<>();
    List<Integer> tmpPath = new ArrayList<>();
    int minCost = Integer.MAX_VALUE;

    private int calCost(int from, int to, Graph graph) {
        for (Edge edge : graph.adj[from]) {
            if (edge.to == to) {
                return edge.cost;
            }
        }
        return -1;
    }
    private void dfs(int v, Graph graph) {
        //System.out.println("dfs: " + v);
        tmpPath.add(v);
        if (v == s) {
            int cost = 0;
            for (int i = 0; i < tmpPath.size() - 1; i++) {
                cost += calCost(tmpPath.get(i),tmpPath.get(i + 1),graph);
            }
            if (cost < minCost) {
                minCost = cost;
                path.clear();
                for (int i = 0; i < tmpPath.size(); i++) {
                    path.add(tmpPath.get(i));
                }
            }
            tmpPath.remove(tmpPath.size() - 1);
            return;
        }

        for (int i = 0; i < edgeTo[v].size(); i++) {
            dfs(edgeTo[v].get(i).from, graph);
        }
        tmpPath.remove(tmpPath.size() - 1);
    }


    public static void main(String[] args) throws IOException {
        Read.init();

        int N = Read.nextInt();
        int M = Read.nextInt();
        int S = Read.nextInt();
        int D = Read.nextInt();
        TravelPlan travelPlan = new TravelPlan();
        travelPlan.s = S;
        Graph graph = travelPlan.new Graph(N, M);

        for (int i = 0; i < M; i++) {
            int city1 = Read.nextInt();
            int city2 = Read.nextInt();
            int distance = Read.nextInt();
            int cost = Read.nextInt();
            graph.addEdge(city1, city2, distance, cost);
        }
        travelPlan.shortestPath(graph);
        travelPlan.dfs(D, graph);
        for (int i = travelPlan.path.size() - 1; i >= 0; i--) {
            System.out.print(travelPlan.path.get(i) + " ");
        }
        System.out.print(travelPlan.distTo[D] + " ");
        System.out.print(travelPlan.minCost + "\n");
    }
}