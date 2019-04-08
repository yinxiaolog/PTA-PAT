import util.Read;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class MainCountingLeaves {
    private static int N, M;
    private static int[][] nonLeafNodes = new int[100 + 1][100 + 1];

    private static int[] level = new int[100 + 1];
    private static int[] book = new int[100 + 1];
    private static int maxLevel = 0;

    private static void bfs() {
        //bfs，设立两个数组，第一个level，保存i结点的层数，
        // 为了bfs的时候可以让当前结点的层数是它的父结点层数+1，
        // 第二个数组book，保存i层所拥有的叶子结点的个数。变量maxlevel保存最大的层数～
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(1);
        level[1] = 1;

        while (!queue.isEmpty()) {
            int top = queue.remove();
            maxLevel = Math.max(maxLevel, level[top]);
            if (nonLeafNodes[top][0] == 0) {
                book[level[top]]++;
            }
            for (int i = 0; i <= nonLeafNodes.length; i++) {
                if(nonLeafNodes[top][i] == 0){
                    break;
                }
                if (nonLeafNodes[top][i] > 0) {
                    queue.add(nonLeafNodes[top][i]);
                    level[nonLeafNodes[top][i]] = level[top] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        N = Read.nextInt();
        M = Read.nextInt();
        for (int i = 0; i < M; i++) {
            int index = Read.nextInt();
            int countLeaves = Read.nextInt();
            for (int j = 0; j < countLeaves; j++) {
                nonLeafNodes[index][j] = Read.nextInt();
            }
        }
        bfs();
        System.out.print(book[1]);
        for (int i = 2; i <= maxLevel; i++) {
            System.out.print(" " + book[i]);
        }
    }
}
