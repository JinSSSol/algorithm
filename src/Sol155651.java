import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Sol155651 {
    public int solution(String[][] book_time) {
        // 종료시간 오름차순 정렬
        List<String[]> list = new ArrayList<>(List.of(book_time));
        list.sort(Comparator.comparing(x -> x[1]));

        int cnt = 0;
        while(!list.isEmpty()) {
            String[] cur = list.remove(0);
            cnt++;

            // 같은 방을 쓸 수 있는 경우 찾기
            String endTime = plusMinute(cur[1], 10);
            // 대실시간 오름차순 정렬
            list.sort(Comparator.comparing(x -> x[0]));

            for (int i = 0; i < list.size(); i++) {
                // 종료시간 > 대실시간
                if (endTime.compareTo(list.get(i)[0]) <= 0) {
                    String[] next = list.remove(i);
                    endTime = plusMinute(next[1], 10);
                    i--;
                }
            }

            list.sort(Comparator.comparing(x -> x[1]));
        }
        return cnt;
    }

    String plusMinute(String time, int minute) {
        int h = Integer.parseInt(time.split(":")[0]);
        int m = Integer.parseInt(time.split(":")[1]);

        m += minute;
        if (m >= 60) {
            m-= 60;
            h++;
        }

        return String.format("%02d:%02d", h, m);
    }

    public static void main(String[] args) {
        String[][] book_time = {{"05:57", "06:02"}, {"04:00", "06:59"}, {"03:56", "07:57"}, {"06:12", "08:55"}, {"07:09", "07:11"}};
        System.out.println(new Sol155651().solution(book_time));
    }
}
