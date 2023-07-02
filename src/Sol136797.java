
import java.util.Arrays;

public class Sol136797 {

    int[][] phone =
        {{1, 7, 6, 7, 5, 4, 5, 3, 2, 3},
            {7, 1, 2, 4, 2, 3, 5, 4, 5, 6},
            {6, 2, 1, 2, 3, 2, 3, 5, 4, 5},
            {7, 4, 2, 1, 5, 3, 2, 6, 5, 4},
            {5, 2, 3, 5, 1, 2, 4, 2, 3, 5},
            {4, 3, 2, 3, 2, 1, 2, 3, 2, 3},
            {5, 5, 3, 2, 4, 2, 1, 5, 3, 2},
            {3, 4, 5, 6, 2, 3, 5, 1, 2, 4},
            {2, 5, 4, 5, 3, 2, 3, 2, 1, 2},
            {3, 6, 5, 4, 5, 3, 2, 4, 2, 1}};

    int[][][] dp;

    public int solution(String numbers) {
        dp = new int[10][10][numbers.length()];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        return touch(4, 6, 0, numbers);
    }

    int touch(int left, int right, int idx, String numbers) {
        if (idx == numbers.length()) {
            return 0;
        }

        if (dp[left][right][idx] != Integer.MAX_VALUE) {
            return dp[left][right][idx];
        }
        if (dp[right][left][idx] != Integer.MAX_VALUE) {
            return dp[right][left][idx];
        }

        int btn = numbers.charAt(idx) - '0';
        int result = Integer.MAX_VALUE;
        // 왼손으로 터치
        if (btn != right) {
            result = Math.min(result, touch(btn, right, idx + 1, numbers) + phone[left][btn]);
        }

        // 오른손으로 터치
        if (btn != left) {
            result = Math.min(result, touch(left, btn, idx + 1, numbers) + phone[right][btn]);
        }

        return dp[left][right][idx] = result;
    }

    public static void main(String[] args) {
        String numbers = "1756";
        System.out.println(new Sol136797().solution(numbers));
    }
}
