import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Sol92341 {

    public int[] solution(int[] fees, String[] records) {
        Map<String, String> in = new HashMap<>();
        Map<String, Integer> time = new HashMap<>();

        for (String recorde : records) {
            String[] r = recorde.split(" ");
            if (r[2].equals("IN")) {
                in.put(r[1], r[0]);
                continue;
            }

            time.put(r[1], time.getOrDefault(r[1], 0) + getTime(in.get(r[1]), r[0]));
            in.remove(r[1]);
        }

        for (Entry<String, String> i : in.entrySet()) {
            time.put(i.getKey(), time.getOrDefault(i.getKey(), 0) + getTime(i.getValue(), "23:59"));
        }

        // 요금 계산
        List<Entry<String, Integer>> list = time.entrySet().stream()
            .sorted(Entry.comparingByKey()).collect(
                Collectors.toList());
        int[] answer = new int[list.size()];
        int i = 0;

        for (Entry<String, Integer> t : list) {
            int fee = fees[1];
            if (t.getValue() > fees[0]) {
                fee += Math.ceil((t.getValue() - fees[0]) / (double) fees[2]) * fees[3];
            }
            answer[i++] = fee;
        }

        return answer;
    }

    int getTime(String in, String out) {
        int h1 = Integer.parseInt(in.split(":")[0]);
        int m1 = Integer.parseInt(in.split(":")[1]);
        int h2 = Integer.parseInt(out.split(":")[0]);
        int m2 = Integer.parseInt(out.split(":")[1]);

        if (m2 < m1) {
            h2--;
            m2 += 60;
        }
        return (h2 - h1) * 60 + m2 - m1;
    }

    public static void main(String[] args) {
        int[] fees = {120, 0, 60, 591};
        String[] records = {"16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT",
            "23:58 3961 IN"};
        System.out.println(Arrays.toString(new Sol92341().solution(fees, records)));
    }
}
