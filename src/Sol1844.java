import java.util.Queue;
import java.util.ArrayDeque;

public class Sol1844 {
    public int solution(int[][] maps) {
        int[][] dirs = {{1,0},{0,1},{-1,0},{0,-1}};
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0,0,1});
        visited[0][0] = true;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == maps.length - 1 && cur[1] == maps[0].length -1) {
                return cur[2];
            }

            for(int[] dir: dirs) {
                int newX = cur[0] + dir[0];
                int newY = cur[1] + dir[1];

                if (newX >= 0 && newX < maps.length && newY >= 0 && newY < maps[0].length && maps[newX][newY] != 0 && !visited[newX][newY]) {
                    queue.offer(new int[]{newX, newY, cur[2] + 1});
                    visited[newX][newY] = true;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
        System.out.println(new Sol1844().solution(maps));
    }
}
