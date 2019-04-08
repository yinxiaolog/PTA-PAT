import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DeepestRoot {
    private static boolean[] marked;
    private static int[] edgeTo;
    private static int[] id;
    private static int count;
    private static int s;

    public static Graph readGraph(int edgeCount, int vertexCount) throws IOException{
        Graph graph = new Graph(edgeCount, vertexCount);
        return graph;
    }

    private static void dfs(Graph graph, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : graph.getAdj(v)) {
            if(!marked[w]) {
                dfs(graph, w);
            }
        }
    }

    public static int connectedComponent(Graph graph) {
        marked = new boolean[graph.getVertex()];
        id = new int[graph.getVertex()];

        for (int i = 0; i < graph.getVertex(); i++) {
            if(!marked[i]) {
                dfs(graph, i);
                count++;
            }
        }
        return count;
    }

    public static void dfsPaths(Graph graph, int v) {
        marked[v] = true;
        for (int w : graph.getAdj(v)) {
            if(!marked[w]) {
                edgeTo[w] = v;
                dfsPaths(graph, w);
            }
        }
    }

    public static boolean hasPathTo(int v) {
        return marked[v];
    }

    public static Stack<Integer> pathTo(int v) {
        if(!hasPathTo(v)){
            return null;
        }

        Stack<Integer> stack = new Stack<>();
        for(int i = v; i != s; i = edgeTo[i]) {
            stack.push(i);
        }
        stack.push(s);
        return stack;
    }

    private static List removeRepetition(List<Integer> list) {
        List<Integer> ans = new ArrayList<>();

        for (int i : list) {
            if(!ans.contains(i)) {
                ans.add(i);
            }
        }

        return ans;
    }

    public static List<Integer> deepestRoot(Graph graph) {
        List<Integer> nodeOfDeepest = new ArrayList<>();
        int maxDepth = 0;
        for(int i = 0; i < graph.getVertex(); i++) {
            if(hasPathTo(i)) {
                //System.out.println("size: " + pathTo(i).size());
                if(pathTo(i).size() - 1 > maxDepth){
                    nodeOfDeepest.clear();
                    maxDepth = pathTo(i).size() - 1;
                    nodeOfDeepest.add(i);
                } else if (pathTo(i).size() - 1 == maxDepth){
                    nodeOfDeepest.add(i);
                }
            }
        }

        return nodeOfDeepest;
    }
    public static void main(String[] args) throws IOException{
        Read.init();
        int vertexCount = Read.nextInt();
        int edgeCount = vertexCount - 1;
        Graph graph = new Graph(vertexCount, edgeCount);
        for(int i = 0; i < edgeCount; i++) {
            int v = Read.nextInt() - 1;
            int w = Read.nextInt() - 1;
            graph.addEdge(v, w);
        }
        edgeTo = new int[vertexCount];

        count = connectedComponent(graph);
        if (count > 1) {
            System.out.printf("Error: %d components", count);
        } else {
            s = 0;
            List<Integer> ans = new ArrayList<>();
            marked = new boolean[graph.getVertex()];
            id = new int[graph.getVertex()];
            edgeTo = new int[graph.getVertex()];
            dfsPaths(graph, s);

            ans.addAll(deepestRoot(graph));

            s = ans.get(0);
            marked = new boolean[graph.getVertex()];
            id = new int[graph.getVertex()];
            edgeTo = new int[graph.getVertex()];
            dfsPaths(graph, s);
            ans.addAll(deepestRoot(graph));

            Collections.sort(ans);
            ans = removeRepetition(ans);
            for (int v : ans){
                System.out.println(v + 1);
            }
        }
    }
}

class Graph {
    private int edge;
    private int vertex;
    private List<Integer>[] adj;

    public Graph(int vertexCount) {
        adj = new List[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public Graph(int vertexCount, int edgeCount) throws IOException {
        this(vertexCount);
        this.edge = edgeCount;
        this.vertex = vertexCount;
    }

    public int getEdge() {
        return edge;
    }

    public int getVertex() {
        return vertex;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    public Iterable<Integer> getAdj(int v) {
        return adj[v];
    }
}
