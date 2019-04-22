import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class PathOfEqualWeight {
    private static boolean[] marked;
    private static int[] edgeTo;
    private static final int root = 0;
    private static int[] weights;
    private static int target;

    private static class Graph {
        List<Integer>[] adj;
        public Graph(int v) {
            adj = new List[v];
            for (int i = 0; i < v; i++) {
                adj[i] = new ArrayList<>();
            }
        }
    }

    private static class Sort implements Comparable<Sort>{
        List<Integer> list;

        public Sort(List list) {
            this.list = list;
        }

        @Override
        public int compareTo(Sort another) {
            List<Integer> anotherList = another.list;
            for (int i = 0; i < Math.min(list.size(), anotherList.size()); i++) {
                if (list.get(i) < anotherList.get(i)) {
                    return 1;
                } else if (list.get(i) > anotherList.get(i)) {
                    return -1;
                } else {
                    continue;
                }
            }
            if (list.size() == anotherList.size()) {
                return 0;
            }
            return 0;
        }

        @Override
        public String toString() {
            return list.toString().replaceAll("[,\\[\\]]","");
        }
    }

    private static void dfs(Graph graph, int v) {
        marked[v] = true;

        for (Integer w : graph.adj[v]) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(graph, w);
            }
        }
    }

    private static List<Integer> pathTo(int v) {
        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        for (int x = v; x != root; x = edgeTo[x]) {
            sum += weights[x];
            stack.push(weights[x]);
        }
        sum += weights[root];
        stack.push(weights[root]);
        if (sum != target) {
            return null;
        } else {
            List<Integer> ans = new ArrayList<>();
            while (!stack.isEmpty()){
                ans.add(stack.pop());
            }
            return ans;
        }
    }
    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        int M = Read.nextInt();
        target = Read.nextInt();
        weights = new int[N];
        marked = new boolean[N];
        edgeTo = new int[N];
        for (int i = 0; i < N; i++) {
            weights[i] = Read.nextInt();
        }

        Graph graph = new Graph(N);
        for (int i = 0; i < M; i++) {
            List<Integer> list = new ArrayList<>();
            int id = Read.nextInt();
            int K = Read.nextInt();
            for (int j = 0; j < K; j++) {
                list.add(Read.nextInt());
            }
            graph.adj[id] = list;
        }
        dfs(graph, root);
        List<Sort> ans = new ArrayList<>();
        for (int i = 0; i < graph.adj.length; i++) {
            if (graph.adj[i].isEmpty()) {
                List list = pathTo(i);
                if (list != null) {
                    Sort sort = new Sort(list);
                    ans.add(sort);
                }
            }
        }

        Collections.sort(ans);
        for (Sort sort : ans) {
            System.out.println(sort);
        }
    }
}
