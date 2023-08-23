import java.util.Arrays;
import java.util.Comparator;

public class Sol42861 {
    public int solution(int n, int[][] costs) {

        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        Arrays.sort(costs, Comparator.comparingInt(x -> x[2]));
        int cnt = 0;
        int sum = 0;
        for (int[] cost: costs) {
            if (cnt >= n - 1) break;

            if (find(parents, cost[0]) != find(parents, cost[1])) {
                union(cost[0], cost[1], parents);
                sum += cost[2];
                cnt++;
            }
        }

        return sum;
    }

    public int find(int[] parents, int node) {
        if (parents[node] == node)
            return node;

        return find(parents, parents[node]);
    }

    public void union(int a, int b, int[] parents) {
        int pA = find(parents, a);
        int pB = find(parents, b);

        if (pA > pB) {
            parents[pA] = pB;
        } else {
            parents[pB] = pA;
        }
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] costs = {{0,1,1}, {0,2,2}, {1,2,5}, {1,3,1}, {2,3,8}};
        System.out.println(new Sol42861().solution(n, costs));
    }
}
