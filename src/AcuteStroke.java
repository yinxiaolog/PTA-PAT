import util.Read;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class AcuteStroke {
    private static class Node {
        int x;
        int y;
        int z;

        public Node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    private static int[] dx = {1, 0, 0, -1, 0, 0};
    private static int[] dy = {0, 1, 0, 0, -1, 0};
    private static int[] dz = {0, 0, 1, 0, 0, -1};

    private static int M, N, L, T;
    private static int[][][] image;
    private static boolean[][][] marked;

    private static boolean judge(int x, int y, int z) {
        if (x < 0 || x >= M || y < 0 || y >= N || z < 0 || z >= L) {
            return false;
        }

        if (image[x][y][z] == 0 || marked[x][y][z]) {
            return false;
        }

        return true;
    }

    private static int bfs(int x, int y, int z) {
        int count = 0;
        Node node = new Node(x, y, z);
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        marked[x][y][z] = true;
        while (!queue.isEmpty()) {
            Node tmpNode = queue.poll();
            count++;

            for (int i = 0; i < 6; i++) {
                int tx = tmpNode.x + dx[i];
                int ty = tmpNode.y + dy[i];
                int tz = tmpNode.z + dz[i];

                if (judge(tx, ty, tz)) {
                    marked[tx][ty][tz] = true;
                    node.x = tx;
                    node.y = ty;
                    node.z = tz;
                    queue.add(new Node(tx, ty, tz));
                }
            }
        }

        if (count >= T) {
            return count;
        } else {
            return 0;
        }
    }

    private static int solve() {
        int ans = 0;
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    if (image[j][k][i] == 1 && !marked[j][k][i]) {
                        ans += bfs(j, k, i);
                    }
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        M = Read.nextInt();
        N = Read.nextInt();
        L = Read.nextInt();
        T = Read.nextInt();

        image = new int[M][N][L];
        marked = new boolean[M][N][L];
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    image[j][k][i] = Read.nextInt();
                }
            }
        }

        System.out.println(solve());
    }
}
