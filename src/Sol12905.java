public class Sol12905 {
    public int solution(int [][]board) {
        int result = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[0][i] == 1) {
                result = 1;
                break;
            }

            if (board[i][0] == 1) {
                result = 1;
                break;
            }
        }

        // board[i][j] = (i,j)가 왼쪽 젤 아래일 때 만들 수 있는 가장 큰 정사각형
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[0].length; j++) {
                if (board[i][j] != 0) {
                    int min = Math.min(Math.min(board[i - 1][j], board[i][j - 1]),
                        board[i - 1][j - 1]);
                    board[i][j] = min + 1;
                    if (board[i][j] > result)
                        result = board[i][j];
                }
            }
        }
        return result * result;
    }

    public static void main(String[] args) {
        int[][] board = {{0,1,1,1,}, {1,1,1,1},{1,1,1,1,},{0,0,1,0}};
        System.out.println(new Sol12905().solution(board));
    }
}
