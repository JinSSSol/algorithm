import java.util.ArrayDeque;
import java.util.Queue;

public class Sol118667 {

    public int solution(int[] queue1, int[] queue2) {
        long sum1 = 0L;
        long sum2 = 0L;

        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();

        for (int n : queue1) {
            q1.add(n);
            sum1 += n;
        }

        for (int n : queue2) {
            q2.add(n);
            sum2 += n;
        }

        int cnt = 0;
        int c1 = q1.size();
        int c2 = q2.size();
        while (c1 > 0 || c2 > 0) {
            if (sum1 == sum2) {
                return cnt;
            }

            if (sum1 < sum2) {
                int num = q2.poll();
                q1.add(num);
                sum1 += num;
                sum2 -= num;
                c2--;
            } else {
                int num = q1.poll();
                q2.add(num);
                sum1 -= num;
                sum2 += num;
                c1--;
            }
            cnt++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] queue1 = {1, 2, 1, 2};
        int[] queue2 = {1, 10, 1, 2};

        System.out.println(new Sol118667().solution(queue1, queue2));
    }
}
