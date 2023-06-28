import java.util.Queue;
import java.util.ArrayDeque;
public class Sol159993 {
    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public int solution(String[] maps) {
        int startX = -1;
        int startY = -1;
        int targetX = -1;
        int targetY = -1;
        int leverX = -1;
        int leverY = -1;
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                if (maps[i].charAt(j) == 'S') {
                    startX = i;
                    startY = j;
                } else if (maps[i].charAt(j) == 'E') {
                    targetX = i;
                    targetY = j;
                } else if (maps[i].charAt(j) == 'L') {
                    leverX = i;
                    leverY = j;
                }
                if (startX != -1 && targetX != -1 && leverX != -1) break;
            }
        }

        // 시작점 ~ 레버
        int result = bfs(startX, startY, leverX, leverY, maps);
        if (result == -1) return -1;

        // 레버 ~ 출구
        int result2 = bfs(leverX, leverY, targetX, targetY, maps);
        if (result2 == -1) return -1;
        return result + result2;
    }

    int bfs(int startX, int startY, int targetX, int targetY, String[] maps) {
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        Queue<int[]> queue = new ArrayDeque<>();
        visited[startX][startY] = true;
        queue.add(new int[]{startX, startY, 0});

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == targetX && cur[1] == targetY) {
                return cur[2];
            }
            for(int[] dir: dirs) {
                int newX = cur[0] + dir[0];
                int newY = cur[1] + dir[1];

                if (newX >= 0 && newX < maps.length && newY >= 0 && newY < maps[0].length() && !visited[newX][newY]) {
                    if (maps[newX].charAt(newY) == 'X') continue;
                    visited[newX][newY] = true;
                    queue.add(new int[]{newX, newY, cur[2] + 1});
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        String[] maps = {"SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"};
        System.out.println(new Sol159993().solution(maps));
    }
}
