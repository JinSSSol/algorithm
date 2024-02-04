import java.util.HashMap;

public class Sol1835 {

    public int solution(int n, String[] data) {
        HashMap<Character, Integer> map = new HashMap<>();
        dfs(data, map, 1);
        return result;
    }

    char[] name = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    int result = 0;

    private void dfs(String[] data, HashMap<Character, Integer> map, int idx) {
        if (idx > 8) {
            result++;
            return;
        }

        Loop:
        for (int i = 0; i < 8; i++) {
            if (!map.containsKey(name[i])) {
                // 조건 검사
                map.put(name[i], idx);
                for (String condition : data) {
                    int pos1 = map.getOrDefault(condition.charAt(0), -1);
                    int pos2 = map.getOrDefault(condition.charAt(2), -1);

                    // 해당 조건에 포함된 프렌즈의 위치가 정해지지 않은 경우
                    if (pos1 == -1 || pos2 == -1) {
                        continue;
                    }

                    int dist = Math.abs(pos1 - pos2) - 1;
                    if (condition.charAt(3) == '=' && dist != condition.charAt(4) - '0') {
                        map.remove(name[i]);
                        continue Loop;
                    }

                    if (condition.charAt(3) == '>' && dist <= condition.charAt(4) - '0') {
                        map.remove(name[i]);
                        continue Loop;

                    }
                    if (condition.charAt(3) == '<' && dist >= condition.charAt(4) - '0') {
                        map.remove(name[i]);
                        continue Loop;
                    }
                }

                //조건 검사 통과
                map.put(name[i], idx);
                dfs(data, map, idx + 1);
                map.remove(name[i]);
            }
        }
    }

    public static void main(String[] args) {
        int n = 2;
        String[] data = {"N~F=0", "R~T>2"};
        System.out.println(new Sol1835().solution(n, data));
    }

}
