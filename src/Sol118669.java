import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Sol118669 {

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // 그래프 생성
        List<List<int[]>> adjList = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] path : paths) {
            if (isGate(gates, path[0]) || isSummit(summits, path[1])) {
                adjList.get(path[0]).add(new int[]{path[1], path[2]});
            } else if (isGate(gates, path[1]) || isSummit(summits, path[0])) {
                adjList.get(path[1]).add(new int[]{path[0], path[2]});
            } else {
                adjList.get(path[0]).add(new int[]{path[1], path[2]});
                adjList.get(path[1]).add(new int[]{path[0], path[2]});
            }
        }

        return dijkstra(n, adjList, gates, summits);
    }

    int[] dijkstra(int n, List<List<int[]>> adjList, int[] gates, int[] summits) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.dist));
        for (int gate : gates) {
            dist[gate] = 0;
            pq.offer(new Node(gate, 0));
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.dist > dist[cur.node]) {
                continue;
            }

            for (int[] adj : adjList.get(cur.node)) {
                // 현재 경로에서의 intensity
                int max = Math.max(adj[1], dist[cur.node]);

                // 현재 경로와 다른경로 중 더 최소 intensity 갱신 된 것만 큐에 넣음 (유의미)
                if (dist[adj[0]] > max) {
                    dist[adj[0]] = max;
                    pq.offer(new Node(adj[0], dist[adj[0]]));
                }
            }
        }

        Arrays.sort(summits);
        int min = Integer.MAX_VALUE;
        int minIdx = -1;
        for (int s : summits) {
            if (min > dist[s]) {
                min = dist[s];
                minIdx = s;
            }
        }

        return new int[]{minIdx, min};

    }

    boolean isGate(int[] gates, int gate) {
        for (int g : gates) {
            if (gate == g) {
                return true;
            }
        }
        return false;
    }

    boolean isSummit(int[] summits, int summit) {
        for (int s : summits) {
            if (summit == s) {
                return true;
            }
        }
        return false;
    }

    private static class Node {

        int node;
        int dist;

        Node(int node, int dist) {
            this.dist = dist;
            this.node = node;
        }
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] paths = {{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3},
            {4, 6, 1}, {5, 6, 1}};
        int[] gates = {1, 3};
        int[] summits = {5};

        System.out.println(Arrays.toString(new Sol118669().solution(n, paths, gates, summits)));
    }
}
