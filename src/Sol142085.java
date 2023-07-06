import java.util.PriorityQueue;

public class Sol142085 {

    public int solution(int n, int k, int[] enemy) {
        if (enemy.length <= k) {
            return enemy.length;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            pq.offer(enemy[i]);
        }

        for (int i = k; i < enemy.length; i++) {
            if (pq.peek() < enemy[i]) {
                n -= pq.poll();
                pq.offer(enemy[i]);
            } else {
                n -= enemy[i];
            }

            if (n < 0) {
                return i;
            }
        }
        return enemy.length;
    }


    public static void main(String[] args) {
        int n = 7;
        int k = 3;
        int[] enemy = {4, 2, 4, 5, 3, 3, 1};
        System.out.println(new Sol142085().solution(n, k, enemy));
    }
}
