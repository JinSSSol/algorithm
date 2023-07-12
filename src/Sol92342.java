import java.util.Arrays;

public class Sol92342 {

    public int[] solution(int n, int[] info) {
        dfs(info, new int[11], n, 0);
        return answer;
    }

    int max = Integer.MIN_VALUE;
    int[] answer = {-1};

    void dfs(int[] info, int[] lion, int cnt, int idx) {
        if (cnt == 0) {
            int score = 0;
            int score2 = 0;
            // 점수 구하기
            for (int i = 0; i < 11; i++) {
                if (info[i] == lion[i] && info[i] == 0) {
                    continue;
                }

                if (info[i] < lion[i]) {
                    score += 10 - i;
                } else {
                    score2 += 10 - i;
                }
            }

            if (score <= score2) {
                return;
            }

            if (max < (score - score2)) {
                max = (score - score2);
                answer = lion.clone();
                return;
            }

            if (max == (score - score2)) {
                for (int i = 10; i >= 0; i--) {
                    if (lion[i] > answer[i]) {
                        answer = lion.clone();
                        return;
                    } else if (lion[i] < answer[i]) {
                        return;
                    }
                }
            }
            return;
        }

        if (idx == 10) {
            lion[idx] = cnt;
            dfs(info, lion.clone(), 0, idx);
            return;
        }

        for (int i = 0; i <= cnt; i++) {
            lion[idx] = i;
            dfs(info, lion.clone(), cnt - i, idx + 1);
        }

    }

    public static void main(String[] args) {
        int n = 10;
        int[] info = {0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3};
        System.out.println(Arrays.toString(new Sol92342().solution(n, info)));
    }
}
