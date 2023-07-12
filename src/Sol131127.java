import java.util.HashMap;
import java.util.Map;

public class Sol131127 {

    public int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < want.length; i++) {
            map.put(want[i], number[i]);
        }

        int cnt = 0;
        Loop:
        for (int i = 0; i <= discount.length - want.length; i++) {
            Map<String, Integer> map1 = new HashMap<>(map);
            for (int j = i; j < i + 10 && j < discount.length; j++) {
                if (map1.containsKey(discount[j])) {
                    map1.put(discount[j], map1.get(discount[j]) - 1);

                    if (map1.get(discount[j]) <= 0) {
                        map1.remove(discount[j]);
                    }
                }

                if (map1.size() == 0) {
                    cnt++;
                    continue Loop;
                }
            }
        }

        return cnt;
    }

    public static void main(String[] args) {
        String[] want = {"banana", "apple", "rice", "pork", "pot"};
        int[] number = {3, 2, 2, 2, 1};
        String[] discount = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};
        System.out.println(new Sol131127().solution(want, number, discount));
    }
}
