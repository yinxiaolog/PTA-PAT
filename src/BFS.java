import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFS {
    private class Vertex {
        int id;
        int weight;

        Vertex(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }
    }

    private static int m = 5;
    private static int n;
    private static int[][] G = {{0, 1, 2, 1, 0},
            {1, 0, 1, 0, 0},
            {2, 1, 0, 0, 1},
            {1, 0, 0, 0, 1},
            {0, 0, 1, 1, 0}};
    private static boolean[] marked = new boolean[m];
    private static int[] edgeTo = new int[m];
    private static final int s = 2;

    private static void bfs(int[][] G, int s) {
        Queue<Integer> queue = new LinkedList<Integer>();
        marked[s] = true;
        queue.add(s);
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (int i = 0; i < m; i++) {
                if (G[v][i] != 0) {
                    if (!marked[i]) {
                        edgeTo[i] = v;
                        marked[i] = true;
                        queue.add(i);
                    }
                }
            }
        }
    }

    public static boolean hasPathTo(int v) {
        return marked[v];
    }

    public static Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }

        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    public static void main(String[] args) {
        bfs(G, s);
        System.out.println(pathTo(3));
    }
}
