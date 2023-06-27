
import java.util.ArrayDeque;
import java.util.Queue;

public class Sol169199 {

    public int solution(String[] board) {
        int n = board.length;
        int m = board[0].length();
        int startX = 0, startY = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i].charAt(j) == 'R') {
                    startX = i;
                    startY = j;
                }
            }
        }

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean[][] visited = new boolean[n][m];

        // bfs
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{startX, startY, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            visited[cur[0]][cur[1]] = true;

            if (board[cur[0]].charAt(cur[1]) == 'G') {
                return cur[2];
            }

            for (int i = 0; i < 4; i++) {
                int[] pos = this.getPos(cur[0], cur[1], dirs[i], board, n, m);

                if (!visited[pos[0]][pos[1]]) {
                    queue.add(new int[]{pos[0], pos[1], cur[2] + 1});
                }
            }
        }

        return -1;
    }

    int[] getPos(int x, int y, int[] d, String[] board, int n, int m) {
        int newX = x;
        int newY = y;
        while (newX >= 0 && newY >= 0 && newX < n && newY < m && board[newX].charAt(newY) != 'D') {
            newX += d[0];
            newY += d[1];
        }

        return new int[]{newX - d[0], newY - d[1]};
    }

    public static void main(String[] args) {
        String[] board = {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."};
        System.out.println(new Sol169199().solution(board));
    }
}
