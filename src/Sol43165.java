public class Sol43165 {

    public int solution(int[] numbers, int target) {
        dfs(0, 0, numbers, target);
        return cnt;
    }

    int cnt = 0;
    void dfs(int idx, int sum, int[] numbers, int target) {
        if (idx == numbers.length) {
            if (sum == target) cnt++;
            return;
        }

        dfs(idx+1, sum + numbers[idx], numbers, target);

        dfs(idx + 1, sum - numbers[idx], numbers, target);
    }

    public static void main(String[] args) {
        int[] numbers = {1,1,1,1,1};
        int target = 3;
        System.out.println(new Sol43165().solution(numbers, target));
    }
}
