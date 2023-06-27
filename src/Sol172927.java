public class Sol172927 {

    // 다이아몬드를 먼저사용하면 나중에 비싼 광물이 나왔을때 피로도가 올라감
    // 한번 선택하면 5개 연속으로 사용후 다른것 선택 가능

    final int[][] fatigue = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
    int min = Integer.MAX_VALUE;

    public int solution(int[] picks, String[] minerals) {
        dfs(0, 0, picks, minerals);
        return min;
    }

    void dfs(int idx, int cnt, int[] picks, String[] minerals) {
        if (idx >= minerals.length) {
            min = Math.min(min, cnt);
            return;
        }
        // 3개중 하나 선택
        boolean flag = false;
        for (int i = 0; i < 3; i++) {
            int newCnt = cnt;
            if (picks[i] > 0) {
                flag = true;
                int j;
                for (j = idx; j < idx + 5 && j < minerals.length; j++) {
                    if (minerals[j].equals("diamond")) {
                        newCnt += fatigue[i][0];
                    } else if (minerals[j].equals("iron")) {
                        newCnt += fatigue[i][1];
                    } else {
                        newCnt += fatigue[i][2];
                    }
                }
                picks[i]--;
                dfs(j, newCnt, picks, minerals);
                picks[i]++;
            }
        }

        // 곡괭이가 없는 경우
        if (!flag) {
            min = Math.min(min, cnt);
        }
    }

    public static void main(String[] args) {
        int[] picks = {1, 1, 0};
        String[] minerals = {"stone", "stone", "iron", "stone", "diamond", "diamond", "diamond",
            "diamond", "diamond", "diamond"};
        System.out.println(new Sol172927().solution(picks, minerals));
    }
}
