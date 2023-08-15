import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Sol49189 {
    public int solution(int n, int[][] edge) {
        List<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            adjList.add(new ArrayList<>());
        }

        for(int[] e: edge) {
            adjList.get(e[0]).add(e[1]);
            adjList.get(e[1]).add(e[0]);
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x[1]));
        pq.offer(new int[]{1, 0});

        int max = 0;
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (cur[1] > dist[cur[0]]) continue;

            // 최대값
            max = Math.max(max, cur[1]);

            for(int adj: adjList.get(cur[0])) {
                if (dist[adj] > cur[1] + 1) {
                    dist[adj] = cur[1] + 1;
                    pq.offer(new int[]{adj, dist[adj]});
                }
            }
        }

        int cnt = 0;
        for (int i = 1; i < n + 1 ; i++) {
            if (dist[i] == max) cnt++;
        }

        return cnt;
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] edge = {{3,6},{4,3},{3,2},{1,3},{1,2},{2,4},{5,2}};

        System.out.println(new Sol49189().solution(n, edge));
    }
}
