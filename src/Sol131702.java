public class Sol131702 {

    int result;

    public int solution(int[][] clockHands) {
        result = Integer.MAX_VALUE;
        dfs(clockHands, 0, 0);
        return result;
    }

    private void dfs(int[][] clockHands, int idx, int count) {
        // 첫번째 줄의 회전수 고정 완료
        if (idx >= clockHands.length) {

            // 윗줄이 0이 되도록 아래줄 회전
            count = makeMapZero(clockHands, count);
            // 맨 아래줄이 모두 0이 되는지 확인
            if (checkZero(clockHands)) {
                result = Math.min(result, count);
            }
            return;
        }

        int[][] exMap = clone(clockHands);
        for (int i = 0; i < 4; i++) {
            rotate(i, 0, idx, clockHands);
            dfs(clockHands, idx + 1, count + i);
            clockHands = clone(exMap);
        }
    }

    // 2차원 배열 깊은 복사
    private int[][] clone(int[][] map) {
        int[][] copy = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            copy[i] = map[i].clone();
        }
        return copy;
    }

    private void rotate(int cnt, int x, int y, int[][] map) {
        if (x > 0) {
            map[x - 1][y] = (map[x - 1][y] + cnt) % 4;
        }

        if (x < map.length - 1) {
            map[x + 1][y] = (map[x + 1][y] + cnt) % 4;
        }

        if (y > 0) {
            map[x][y - 1] = (map[x][y - 1] + cnt) % 4;
        }
        if (y < map.length - 1) {
            map[x][y + 1] = (map[x][y + 1] + cnt) % 4;
        }

        map[x][y] = (map[x][y] + cnt) % 4;
    }

    // 윗 줄이 0이 되도록 아래줄을 회전 (윗 줄은 아래줄과 무조건 같이 회전하기 때문)
    // 맨 아래줄까지 회전 후 회전 수 반환
    private int makeMapZero(int[][] map, int count) {
        for (int i = 1; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                int r = (4 - map[i - 1][j]) % 4;
                rotate(r, i, j, map);
                count += r;
            }
        }
        return count;
    }

    private boolean checkZero(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            if (map[map.length - 1][i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] clockHands = {{0, 1, 3, 0}, {1, 2, 0, 0}, {3, 0, 2, 2}, {0, 2, 0, 0}};

        System.out.println(new Sol131702().solution(clockHands));
    }
}
