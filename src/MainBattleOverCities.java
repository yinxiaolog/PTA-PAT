import util.Read;

import java.io.IOException;

public class MainBattleOverCities {
    private static int[][] graph = new int[1010][1010];
    private static boolean[] visit = new boolean[1010];

    static int N, M, K;
    private static void dfs(int node){
        visit[node] = true;
        for(int i = 1; i <=N;i++){
            if(visit[i] == false && graph[node][i] == 1){
                dfs(i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        N = Read.nextInt();
        M = Read.nextInt();
        K = Read.nextInt();
        for(int i = 0; i < M; i++){
            int x = Read.nextInt();
            int y = Read.nextInt();
            graph[x][y] = graph[y][x] = 1;
        }

        for(int i = 0; i < K; i++){
            for(int k = 0; k < visit.length;k++){
                visit[k] = false;
            }
            int node = Read.nextInt();
            int count = 0;
            visit[node] = true;
            for(int j = 1; j <= N; j++){
                if(visit[j] == false){
                    dfs(j);
                    count++;
                }
            }
            System.out.println(count - 1);
        }
    }
}
