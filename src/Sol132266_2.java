import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Sol132266_2 {

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {

        // 인접리스트 생성
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            adjList.get(road[0]).add(road[1]);
            adjList.get(road[1]).add(road[0]);
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[destination] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.dist));
        pq.offer(new Node(destination, 0));

        int[] answer = new int[sources.length];

        for (int i = 0; i < sources.length; i++) {
            int d = dijkstra(adjList, dist, pq, sources[i]);
            answer[i] = d == Integer.MAX_VALUE ? -1 : d;

        }
        return answer;
    }

    private int dijkstra(List<List<Integer>> adjList, int[] dist, PriorityQueue<Node> pq,
        int source) {

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.node == source) {
                pq.offer(cur);
                return cur.dist;
            }

            if (cur.dist > dist[cur.node]) {
                continue;
            }

            for (int adj : adjList.get(cur.node)) {
                if (dist[adj] > cur.dist + 1) {
                    dist[adj] = cur.dist + 1;
                    pq.offer(new Node(adj, dist[adj]));
                }
            }
        }

        return dist[source];
    }

    static class Node {

        int node;
        int dist;

        Node(int n, int d) {
            this.node = n;
            this.dist = d;
        }
    }

    public static void main(String[] args) {

        int[] answer = new Sol132266_2().solution(3, new int[][]{{1, 2}, {2, 3}}, new int[]{2, 3},
            1);
        System.out.println(Arrays.toString(answer));

    }
}
