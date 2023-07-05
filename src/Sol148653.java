public class Sol148653 {
    public int solution(int storey) {
        dfs(storey, 0);
        return result;
    }

    int result = Integer.MAX_VALUE;
    void dfs (int num, int cnt) {

        if (cnt >= result) return;

        if (num == 0) {
            result = cnt;
            return;
        }

        // 0 제거
        while (num % 10 == 0) {
            num /= 10;
        }

        int f = num % 10;
        // 내림
        dfs(num - f, cnt + f);

        // 올림
        dfs(num + 10 - f, cnt + 10 - f);
    }

    public static void main(String[] args) {
        System.out.println(new Sol148653().solution(2554));
    }
}
