package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Sol2304 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] polygon = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            polygon[i][0] = Integer.parseInt(st.nextToken());
            polygon[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(polygon, Comparator.comparingInt(x -> x[0]));
        int max = Integer.MIN_VALUE;
        int maxIdx = 0;

        for (int i = 0; i < polygon.length; i++) {
            if (max <= polygon[i][1]) {
                maxIdx = i;
                max = polygon[i][1];
            }
        }

        int sum = max;
        max = polygon[0][1];
        for (int i = 0; i < maxIdx; i++) {
            int idx = i;
            while (i < maxIdx && max >= polygon[i][1]) {
                i++;
            }

            sum += (polygon[i][0] - polygon[idx][0]) * max;
            max = polygon[i][1];
            i--;
        }

        max = polygon[polygon.length - 1][1];
        for (int i = polygon.length - 1; i > maxIdx; i--) {
            int idx = i;
            while (i > maxIdx && max >= polygon[i][1]) {
                i--;
            }

            sum += (polygon[idx][0] - polygon[i][0]) * max;
            max = polygon[i][1];
            i++;
        }
        System.out.println(sum);
    }

}
