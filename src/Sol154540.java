import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Sol154540 {

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    boolean[][] visited;

    public int[] solution(String[] maps) {
        List<Integer> list = new ArrayList<>();
        visited = new boolean[maps.length][maps[0].length()];

        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                if (maps[i].charAt(j) != 'X' && !visited[i][j]) {
                    list.add(dfs(i, j, 0, maps));
                }
            }
        }

        if (list.size() == 0) {
            return new int[]{-1};
        }
        list.sort(Comparator.naturalOrder());
        return list.stream().mapToInt(x -> x).toArray();
    }

    public int dfs(int x, int y, int sum, String[] maps) {
        visited[x][y] = true;
        sum += maps[x].charAt(y) - '0';

        for (int[] dir : dirs) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            if (newX >= 0 && newX < maps.length && newY >= 0 && newY < maps[0].length()
                && !visited[newX][newY] && maps[newX].charAt(newY) != 'X') {
                sum = dfs(newX, newY, sum, maps);
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        String[] maps = {"X591X", "X1X5X", "X231X", "1XXX1"};
        System.out.println(Arrays.toString(new Sol154540().solution(maps)));

    }
}
