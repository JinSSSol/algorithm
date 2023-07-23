package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol14719 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int W = Integer.parseInt(st.nextToken());

        int[] arr = new int[W];
        int max = Integer.MIN_VALUE;
        int maxIdx = -1;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i] > max) {
                max = arr[i];
                maxIdx = i;
            }
        }

        int sum = 0;
        // 왼쪽부터
        int pillar = arr[0];
        for (int i = 1; i < maxIdx; i++) {
            if (pillar > arr[i]) {
                sum += pillar - arr[i];
            } else {
                pillar = arr[i];
            }
        }

        // 오른쪽 부터
        pillar = arr[W - 1];
        for (int i = W - 2; i > maxIdx; i--) {
            if (pillar > arr[i]) {
                sum += pillar - arr[i];
            } else {
                pillar = arr[i];
            }
        }

        System.out.println(sum);
    }

}
