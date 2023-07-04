import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Sol132266 {

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];

        // 인접 리스트 생성
        List<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            adjList.get(road[0]).add(road[1]);
            adjList.get(road[1]).add(road[0]);
        }

        // Dijkstra
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(x -> x.dist));
        pq.offer(new Node(destination, 0));

        Loop:
        for (int i = 0; i < sources.length; i++) {
            int target = sources[i];
            if (dist[target] != Integer.MAX_VALUE) {
                answer[i] = dist[target];
                continue;
            }

            while (!pq.isEmpty()) {
                Node cur = pq.poll();

                if (cur.dist > dist[cur.node]) {
                    continue;
                }

                for (int adj : adjList.get(cur.node)) {
                    if (dist[adj] > cur.dist + 1) {
                        dist[adj] = cur.dist + 1;
                        pq.offer(new Node(adj, dist[adj]));
                    }
                }

                if (cur.node == target) {
                    answer[i] = cur.dist;
                    continue Loop;
                }
            }
            answer[i] = -1;
        }
        return answer;
    }

    static class Node {

        int dist;
        int node;

        Node(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] roads = {{1, 2}, {2, 3}};
        int[] sources = {2, 3};
        int dest = 1;
        System.out.println(Arrays.toString(new Sol132266().solution(n, roads, sources, dest)));

    }
}
