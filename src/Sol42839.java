import java.util.HashSet;
import java.util.Set;

public class Sol42839 {

    public int solution(String numbers) {
        nums = numbers.toCharArray();
        set = new HashSet<>();

        for (int i = 1; i <= numbers.length(); i++) {
            boolean[] visited = new boolean[numbers.length()];
            dfs(visited, i, 0, 0);
        }

        return set.size();
    }

    char[] nums;
    Set<Integer> set;

    // cnt : 뽑은 카드의 개수
    private void dfs(boolean[] visited, int n, int cnt, int num) {
        if (cnt == n) {
            // 소수 인지 확인
            if (isPrime(num)) {
                set.add(num);
            }
            return;
        }

        // 안뽑은 카드 중에서 하나씩 뽑는다.
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(visited, n, cnt + 1, num * 10 + (nums[i] - '0'));
                // i번째 카드를 뽑은 상태에서 n개를 뽑는 경우를 모두 탐색한후
                // 다음 번째 카드를 뽑는 경우로 돌아가기 전에 visited 배열을 dfs 하기 전 상태로 복원
                visited[i] = false;
            }
        }

    }

    private boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        if (num == 2) {
            return true;
        }

        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Sol42839().solution("011"));
    }
}

