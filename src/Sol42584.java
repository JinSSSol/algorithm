import java.util.Arrays;
import java.util.Stack;

public class Sol42584 {

    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            int day = 0;
            for (int j = i + 1; j < prices.length; j++) {
                day++;
                if (prices[i] > prices[j]) {
                    break;
                }
            }
            answer[i] = day;
        }

        return answer;
    }

    public int[] solution2(int[] prices) {
        int[] answer = new int[prices.length];

        Stack<Integer> stack = new Stack<>();
        // 검사하기 전 동안 계속 증가하고 있던 시점들
        stack.push(0);

        for (int i = 1; i < prices.length; i++) {
            // 현재 i 시점에서 감소하고 있는 경우
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int idx = stack.pop();
                answer[idx] = i - idx;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int idx = stack.pop();
            answer[idx] = prices.length - idx - 1;
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Sol42584().solution2(new int[]{1, 2, 3, 2, 3})));
    }
}
