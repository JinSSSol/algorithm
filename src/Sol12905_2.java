public class Sol12905_2 {

    public int solution(int[][] board) {
        int answer = 0;
        int[][] dp = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i == 0) {
                    dp[0][j] = board[0][j];
                } else if (j == 0) {
                    dp[i][0] = board[i][0];
                } else if (board[i][j] != 0) {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
                answer = Math.max(answer, dp[i][j]);
            }
        }

        return answer * answer;
    }

    public static void main(String[] args) {
        int[][] board = {{0, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {0, 0, 1, 0}};
        System.out.println(new Sol12905_2().solution(board));
    }

}
