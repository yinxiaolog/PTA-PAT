import java.util.Arrays;

public class DFS {
    private static char[][] pic = {{'*', '*', '*', '*', '@'},
            {'*', '@', '@', '*', '@'},
            {'*', '@', '*', '*', '@'},
            {'@', '@', '@', '*', '@'},
            {'@', '@', '*', '*', '@'}};
    private static int m = pic.length;
    private static int n = pic[0].length;
    private static int index[][] = new int[pic.length][pic[0].length];

    public static void main(String[] args) {
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //System.out.println("i:" + i + "j:" + j);
                if (index[i][j] == 0 && pic[i][j] == '@') {
                    cnt++;
                    dfs(i, j, cnt);
                }
            }
        }
        System.out.println(cnt);
    }

    public static void dfs(int row, int column, int id) {
        if (row < 0 || row >= m || column < 0 || column >= n) {
            return;
        }
        if (index[row][column] > 0 || pic[row][column] != '@') {
            return;
        }
        index[row][column] = id;
        for (int dRow = -1; dRow <= 1; dRow++) {
            for (int dColumn = -1; dColumn <= 1; dColumn++) {
                if (!(dRow == 0 && dColumn == 0)) {
                    dfs(row + dRow, column + dColumn, id);
                }
            }
        }
    }
}
