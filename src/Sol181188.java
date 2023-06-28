import java.util.Arrays;
import java.util.Comparator;

public class Sol181188 {

    public int solution(int[][] targets) {
        // 끝나는 시간으로 정렬
        Arrays.sort(targets, Comparator.comparing(x -> x[1]));

        int cnt = 0;
        int end = 0;
        for (int[] target : targets) {

            // 현재 끝나는 시간보다 시작시간이 미만 -> 겹치는 부분이 있는 것
            // 현재 검사하는 것과 하나로 생각
            if (end > target[0]) {
                continue;
            }

            end = target[1];
            cnt++;
        }

        return cnt;
    }

    public static void main(String[] args) {
        int[][] targets = {{4, 5}, {4, 8}, {10, 14}, {11, 13}, {5, 12}, {3, 7}, {1, 4}};
        System.out.println(new Sol181188().solution(targets));
    }
}
