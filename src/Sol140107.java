public class Sol140107 {

    public long solution(int k, int d) {
        long sum = 0L;

        for (int i = 0; i <= d; i += k) {
            long y = (long) Math.sqrt(Math.pow(d, 2) - Math.pow(i, 2));
            sum += Math.floor(y / k) + 1;
        }

        return sum;
    }

    public static void main(String[] args) {
        int k = 2;
        int d = 4;
        System.out.println(new Sol140107().solution(k, d));
    }

}
