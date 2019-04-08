import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PublicBikeManagement {
    private static int inf = Integer.MAX_VALUE;
    private static int cMax, n, sp, m;
    private static int minNeed = inf, minBack = inf;
    private static int[][] e = new int[510][510];
    private static int[] dis = new int[510];
    private static int[] weight = new int[510];
    private static boolean[] visit = new boolean[510];
    private static List<Integer>[] pre = new List[510];
    private static List<Integer> path = new ArrayList<>();
    private static List<Integer> tempPath = new ArrayList<>();

    private static void dfs(int v){
        tempPath.add(v);
        if(v == 0){
            int need = 0, back = 0;
            for(int i = tempPath.size() - 1;i >= 0;i--){
                int id = tempPath.get(i);
                if(weight[id] > 0){
                    back += weight[id];
                } else {
                    if(back > (0 - weight[id])){
                        back += weight[id];
                    } else {
                        need += ((0 - weight[id]) - back);
                        back = 0;
                    }
                }
            }

            if(need < minNeed){
                minNeed = need;
                minBack = back;
                path = tempPath;
            } else if(need == minNeed && back < minBack){
                minBack = back;
                path = tempPath;
            }
            int index = tempPath.size() - 1;
            tempPath.remove(index);
            return;
        }

        for(int i = 0; i < pre[v].size();i++){
            dfs(pre[v].get(i));
        }
        tempPath.remove(tempPath.size() - 1);
    }

    private static void fill(int[] array){
        for(int i = 0; i < array.length; i++){
            array[i] = inf;
        }
    }

    private static void fill(int[][] array){
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[0].length; j++){
                array[i][j] = inf;
            }
        }
    }

    private static void fill(List<Integer>[] array){
        for(int i = 0; i < array.length; i++){
            List<Integer> list = new ArrayList<>();
            array[i] = list;
        }
    }
    public static void main(String[] args) throws IOException {
        Read.init();
        fill(e);
        fill(dis);
        fill(pre);
        cMax = Read.nextInt();
        n = Read.nextInt();
        sp = Read.nextInt();
        m = Read.nextInt();
        for(int i = 1; i <= n; i++){
            weight[i] = Read.nextInt();
            weight[i] = weight[i] - cMax / 2;
        }

        for(int i = 0; i < m; i++){
            int a, b;
            a = Read.nextInt();
            b = Read.nextInt();
            e[b][a] = e[a][b];
        }
        dis[0] = 0;
        for(int i = 0; i <= n; i++){
            int u = -1, minN = inf;
            for(int j = 0; j <= n; j++){
                if(visit[j] == false && dis[j] < minN){
                    u = j;
                    minN = dis[j];
                }

            }
            //System.out.println(u);
            if(u == -1){
                break;
            }
            visit[u] = true;
            for(int v = 0; v <= n; v++){
                if(visit[v] == false && e[u][v] != inf){
                    if(dis[v] > dis[u] + e[u][v]){
                        dis[v] = dis[u] + e[u][v];
                        pre[v].clear();
                        pre[v].add(u);
                    }else if(dis[v] == dis[u] + e[u][v]){
                        pre[v].add(u);
                    }
                }
            }
        }
        Arrays.toString(pre);

        dfs(sp);
        System.out.format("%d 0",minNeed);
        for(int i = path.size() - 2; i >=0; i--){
            System.out.format("->%d",path.get(i));
        }
        System.out.format(" %d",minBack);
    }
}
