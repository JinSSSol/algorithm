import java.util.Arrays;
import java.util.Stack;

public class Sol154539 {
    public int[] solution(int[] numbers) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[numbers.length];

        // 뒤에서부터 검사
        for (int i = numbers.length - 1; i >= 0 ; i--) {
            // 나보다 뒤에있고 작은 수는 쓸모없음 (pop)
            while (!stack.isEmpty() && stack.peek() <= numbers[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                result[i] = -1;
            } else {
                result[i] = stack.peek();
            }
            stack.push(numbers[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] numbers = {2,3,3,5};
        System.out.println(Arrays.toString(new Sol154539().solution(numbers)));
    }
}
