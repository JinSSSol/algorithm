public class Sol87946 {

    public int solution(int k, int[][] dungeons) {
        dfs(k, dungeons, 0, new boolean[dungeons.length]);
        return max;
    }

    int max = Integer.MIN_VALUE;

    void dfs(int k, int[][] dungeons, int idx, boolean[] visited) {
        max = Math.max(idx, max);
        if (idx >= dungeons.length) return;

        for (int i = 0; i < dungeons.length; i++) {
            if (visited[i]) continue;

            if (k >= dungeons[i][0]) {
                visited[i] = true;
                dfs(k - dungeons[i][1], dungeons, idx + 1, visited);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = {{80,20},{50,40},{30,10}};
        System.out.println(new Sol87946().solution(k, dungeons));
    }
}
