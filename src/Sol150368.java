import java.util.Arrays;

public class Sol150368 {

    public int[] solution(int[][] users, int[] emoticons) {
        int[] discount = new int[emoticons.length];
        dfs(0, discount, users, emoticons);
        return result;
    }

    int[] result = new int[2];

    void dfs(int idx, int[] discount, int[][] users, int[] emoticons) {
        if (idx >= emoticons.length) {
            // 계산
            int cnt = 0;
            int amount = 0;
            for (int[] user : users) {
                int curMoney = 0;

                for (int j = 0; j < emoticons.length; j++) {
                    if (discount[j] >= user[0]) {
                        curMoney += emoticons[j] / 100 * (100 - discount[j]);
                        if (curMoney >= user[1]) {
                            cnt++;
                            curMoney = 0;
                            break;
                        }
                    }
                }
                amount += curMoney;
            }

            if ((result[0] == cnt && result[1] < amount) || result[0] < cnt) {
                result[0] = cnt;
                result[1] = amount;
            }
            return;
        }

        for (int i = 1; i <= 4; i++) {
            discount[idx] = i * 10;
            dfs(idx + 1, discount, users, emoticons);
        }
    }

    public static void main(String[] args) {
        int[][] users = {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200},
            {32, 6900}};
        int[] emoticons = {1300, 1500, 1600, 4900};
        System.out.println(Arrays.toString(new Sol150368().solution(users, emoticons)));
    }

}
