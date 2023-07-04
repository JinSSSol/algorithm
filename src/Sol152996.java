import java.util.HashMap;
import java.util.Map;

public class Sol152996 {

    public long solution(int[] weights) {
        Map<Integer, Long> map = new HashMap<>();

        for (int weight : weights) {
            map.put(weight, map.getOrDefault(weight, 0L) + 1);
        }

        boolean[] visited = new boolean[1001];
        long result = 0L;
        for (int weight : weights) {
            // 자신과 동일
            long cnt = map.get(weight);
            if (!visited[weight] && cnt > 1) {
                result += (cnt * (cnt - 1)) / 2;
                visited[weight] = true;
            }

            // 자신의 2배
            result += map.getOrDefault(weight * 2, 0L);

            // 자신의 3/2 배
            if (weight % 2 == 0) {
                result += map.getOrDefault(weight / 2 * 3, 0L);
            }
            // 자신의 4/3 배
            if (weight % 3 == 0) {
                result += map.getOrDefault(weight / 3 * 4, 0L);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] weights = {100,180,360,100,270};
        System.out.println(new Sol152996().solution(weights));
    }
}
