import java.util.Stack;

public class Sol131704 {
    public int solution(int[] order) {
        Stack<Integer> stack = new Stack<>();

        int cur = 1;
        for (int i = 0; i < order.length; i++) {
            while (cur < order[i]) {
                stack.push(cur);
                cur++;
            }

            // 컨테이너
            if (order[i] == cur) {
                cur++;
                continue;
            }

            // 보조 컨테이너
            if (!stack.isEmpty() && order[i] == stack.peek()) {
                stack.pop();
                continue;
            }
            return i;
        }
        return order.length;
    }

    public static void main(String[] args) {
        int[] order = {4,3,1,2,5};
        System.out.println(new Sol131704().solution(order));
    }
}
