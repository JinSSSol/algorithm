public class Sol150369 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        int capD = 0;
        int capP = 0;
        long dist = 0L;

        // 제일 먼 곳부터 배달, 회수
        for (int i = n - 1; i >= 0; i--) {
            capD -= deliveries[i];
            capP -= pickups[i];

            // 배달이나 회수하지 못한것이 하나라도 있으면 다시 방문해야함
            while (capD < 0 || capP < 0) {
                capD += cap;
                capP += cap;

                dist += 2L * (i + 1);
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int cap = 4;
        int n = 5;
        int[] deliveries = {1,0,3,1,2};
        int[] pickups = {0,3,0,4,0};
        System.out.println(new Sol150369().solution(cap, n, deliveries, pickups));
    }
}
