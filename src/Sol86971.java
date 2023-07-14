import java.util.ArrayList;
import java.util.List;

public class Sol86971 {

    public int solution(int n, int[][] wires) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] wire : wires) {
            adjList.get(wire[0]).add(wire[1]);
            adjList.get(wire[1]).add(wire[0]);
        }

        int min = Integer.MAX_VALUE;
        for (int[] wire : wires) {
            adjList.get(wire[0]).remove((Integer) wire[1]);
            adjList.get(wire[1]).remove((Integer) wire[0]);

            cnt = 0;
            search(adjList, wire[0], new boolean[n + 1]);
            min = Math.min(min, Math.abs(2 * cnt - n));
            if (min == 0) {
                return 0;
            }

            adjList.get(wire[0]).add(wire[1]);
            adjList.get(wire[1]).add(wire[0]);
        }
        return min;
    }

    int cnt;

    int search(List<List<Integer>> adjList, int root, boolean[] visited) {
        visited[root] = true;
        cnt++;

        for (Integer adj : adjList.get(root)) {
            if (visited[adj]) {
                continue;
            }
            cnt = search(adjList, adj, visited);
        }
        return cnt;
    }

    public static void main(String[] args) {
        int n = 9;
        int[][] wires = {{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}};
        System.out.println(new Sol86971().solution(n, wires));
    }
}
