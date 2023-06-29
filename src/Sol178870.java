import java.util.Arrays;

public class Sol178870 {

    public int[] solution(int[] sequence, int k) {
        // 길이가 가장 짧은 연속 부분 수열
        // 뒤에서부터 찾기
        int left = sequence.length - 1;
        int right = sequence.length - 1;

        int sum = sequence[right];
        while (left > 0) {
            if (sum == k) {
                // 시작 인덱스가 가장 짧게 하기
                while (left > 0 && sequence[left - 1] == sequence[right]) {
                    left--;
                    right--;
                }
                break;
            }

            if (sum < k) {
                left--;
                sum += sequence[left];
            } else {
                sum -= sequence[right];
                right--;
            }
        }

        return new int[]{left, right};
    }

    public static void main(String[] args) {
        int[] sequence = {1,2,3,4,5};
        int k = 7;
        System.out.println(Arrays.toString(new Sol178870().solution(sequence, k)));
    }
}
