import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class AllRoadsLeadToRome {
    private static class Vertex {
        int id;
        int weight;
        public Vertex(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }
    }

    private static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return from + " -> " + to + " : " + weight;
        }
    }

    private static class Vertex2Dis implements Comparable<Vertex2Dis>{
        int id;
        int dis;

        public Vertex2Dis(int id, int dis) {
            this.id = id;
            this.dis = dis;
        }

        @Override
        public int compareTo(Vertex2Dis another) {
            if (dis < another.dis) {
                return -1;
            }

            if (dis > another.dis) {
                return 1;
            }
            return 0;
        }
    }


    private static class Graph {
        int vertexes;

        List<Edge>[] adj;
        public Graph(int vertexes) {
            this.vertexes = vertexes;
            adj = new List[vertexes];

            for (int i = 0; i < vertexes; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        public void addEdge(Edge edge) {
            adj[edge.from].add(edge);
        }
    }

    private static List<Edge>[] edgeTo;
    private static int[] distTo;
    private static int[] vertexes;

    private static void dijkstra(Graph graph, int s) {
        edgeTo = new List[graph.vertexes];
        for (int i = 0; i < edgeTo.length; i++) {
            edgeTo[i] = new ArrayList<>();
        }

        distTo = new int[graph.vertexes];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = Integer.MAX_VALUE;
        }

        distTo[s] = 0;

        Queue<Vertex2Dis> queue = new PriorityQueue<>();

        queue.add(new Vertex2Dis(s, 0));

        while (!queue.isEmpty()) {
            relax(graph, queue.poll().id, queue);
        }
    }

    private static void relax(Graph graph, int v, Queue<Vertex2Dis> queue) {
        for (Edge edge : graph.adj[v]) {
            int w = edge.to;
            if (distTo[w] > distTo[v] + edge.weight) {
                distTo[w] = distTo[v] + edge.weight;
                edgeTo[w].clear();
                edgeTo[w].add(edge);
                if (!queue.contains(w)) {
                    queue.add(new Vertex2Dis(w, distTo[w]));
                }
            } else if (distTo[w] == distTo[v] + edge.weight) {
                edgeTo[w].add(edge);
            }
        }
    }

    private static Stack<Integer> path = new Stack();
    private static Stack<Integer> tmpPath = new Stack<>();
    private static int maxHappiness;
    private static int cntPath;
    private static double maxAvg;

    private static void dfs(int v) {
        tmpPath.push(v);
        if (v == 0) {
            int happiness = 0;

            for (int i = 0; i < tmpPath.size(); i++) {
                happiness += vertexes[tmpPath.get(i)];
            }

            double tmpAvg = 1.0 * happiness / (tmpPath.size() - 1);
            if (happiness > maxHappiness) {
                maxHappiness = happiness;
                maxAvg = tmpAvg;
                path.clear();
                for (int i = 0; i < tmpPath.size(); i++) {
                    path.push(tmpPath.get(i));
                }
                //path = tmpPath;
            } else if (happiness == maxHappiness && tmpAvg > maxAvg) {
                maxAvg = tmpAvg;
                path.clear();
                for (int i = 0; i < tmpPath.size(); i++) {
                    path.push(tmpPath.get(i));
                }
            }

            tmpPath.pop();
            cntPath++;
            return;
        }

        for (int i = 0; i < edgeTo[v].size(); i++) {
            dfs(edgeTo[v].get(i).from);
        }

        tmpPath.pop();
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        int K = Read.nextInt();
        String startCity = Read.next();

        Map<String, Integer> stringIntegerMap = new HashMap<>();
        Map<Integer, String> integerStringMap = new HashMap<>();
        stringIntegerMap.put(startCity, 0);
        integerStringMap.put(0, startCity);
        vertexes = new int[N];
        for (int i = 1; i < N; i++) {
            String name = Read.next();
            int weight = Read.nextInt();
            stringIntegerMap.put(name, i);
            integerStringMap.put(i, name);
            vertexes[i] = weight;
        }

        Graph graph = new Graph(N);
        for (int i = 0; i < K; i++) {
            String from = Read.next();
            String to = Read.next();
            int weight = Read.nextInt();
            Edge edge = new Edge(stringIntegerMap.get(from), stringIntegerMap.get(to), weight);
            graph.addEdge(edge);
            edge = new Edge(stringIntegerMap.get(to), stringIntegerMap.get(from), weight);
            graph.addEdge(edge);
        }

        dijkstra(graph, stringIntegerMap.get(startCity));
        int indexRom = stringIntegerMap.get("ROM");
        dfs(indexRom);
        System.out.println(cntPath + " " + distTo[indexRom] + " " + maxHappiness + " " + (int)maxAvg);
        StringBuilder sb = new StringBuilder();
        while (path.size() > 1) {
            sb.append(integerStringMap.get(path.pop())).append("->");
        }
        sb.append("ROM");
        System.out.println(sb);
    }
}
