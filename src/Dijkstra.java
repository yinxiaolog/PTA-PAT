import util.Read;

import java.io.IOException;

public class Dijkstra {
    private static int[][] G = new int[500][500];
    private static int[] weight = new int[500];
    private static int[] dis = new int[500];
    private static int[] pathNum = new int[500];
    private static int[] w = new int[500];
    private static boolean[] visit = new boolean[500];
    private static int N = 0,M = 0;
    private static int C1 = 0, C2 = 0;

    private static void init(int[][] array, int value) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = value;
            }
        }
    }

    private static void init(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            array[i] = value;
        }
    }

    public static void dijkstra() {
        dis[C1] = 0;
        w[C1] = weight[C1];
        pathNum[C1] = 1;

        for (int i = 0; i < N; i++) {
            int u = -1;
            int minPath = Integer.MAX_VALUE;
            for (int j = 0; j < N; j++) {
                if (visit[j] == false && dis[j] < minPath) {
                    u = j;
                    minPath = dis[j];
                }
            }
            if (u == -1) {
                break;
            }

            visit[u] = true;
            for (int v = 0; v < N; v++) {
                if (visit[v] == false && G[u][v] != Integer.MAX_VALUE) {
                    if (dis[u] + G[u][v] < dis[v]) {
                        dis[v] = dis[u] + G[u][v];
                        pathNum[v] = pathNum[u];
                        w[v] = w[u] + weight[v];
                    } else if (dis[u] + G[u][v] == dis[v]) {
                        pathNum[v] = pathNum[v] + pathNum[u];
                        if (w[u] + weight[v] > w[v]) {
                            w[v] = w[u] + weight[v];
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init(G, Integer.MAX_VALUE);
        init(dis, Integer.MAX_VALUE);
        Read.init();
        N = Read.nextInt();
        M = Read.nextInt();
        C1 = Read.nextInt();
        C2 = Read.nextInt();
        for(int i = 0;i < N; i++){
            weight[i] = Read.nextInt();
        }

        for(int i = 0; i < M; i++){
            int a = Read.nextInt();
            int b = Read.nextInt();
            int c = Read.nextInt();
            G[a][b] = G[b][a] = c;
        }
        dijkstra();
        System.out.println(pathNum[C2] + " " + w[C2]);
    }
}
