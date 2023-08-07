import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Sol12978 {
    public int solution(int N, int[][] road, int K) {

        List<ArrayList<int[]>> adjList = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            adjList.add(new ArrayList<>());
        }

        for(int[] r: road) {
            adjList.get(r[0]).add(new int[]{r[1], r[2]});
            adjList.get(r[1]).add(new int[]{r[0], r[2]});
        }

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.dist));
        pq.offer(new Node(1, 0));

        int cnt = 0;
        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.dist > K) break;
            if (cur.dist > dist[cur.node]) continue;

            cnt++;
            for(int[] adj: adjList.get(cur.node)) {
                if (dist[cur.node] + adj[1] < dist[adj[0]]) {
                    dist[adj[0]] = dist[cur.node] + adj[1];
                    pq.offer(new Node(adj[0], dist[adj[0]]));
                }
            }
        }

        return cnt;
    }

    static class Node{
        int node;
        int dist;
        Node(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    public static void main(String[] args) {
        int N = 5;
        int[][] road = {{1,2,1},{2,3,3},{5,2,2,},{1,4,2},{5,3,1},{5,4,2}};
        int K = 3;
        System.out.println(new Sol12978().solution(N, road, K));
    }
}
