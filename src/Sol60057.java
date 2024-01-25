public class Sol60057 {
    public int solution(String s) {
        answer = s.length();
        for (int i = 1; i <= s.length() / 2; i++) {
            result = "";
            dfs(s, 1, s.substring(0, i), i, i);
            if (result.equals("-1")) continue;
            answer = Math.min(answer, result.length());
        }

        return answer;
    }

    // idx : 검사하는 시작 인덱스
    int answer;
    String result;
    private void dfs(String word, int cnt, String preWord, int idx, int unit) {
        if (result.length() >= answer) {
            result =  "-1";
            return;
        }
        if (idx + unit > word.length()) {
            if (cnt != 1) result += cnt;
            result += preWord + word.substring(idx);
            return;
        }

        String curWord = word.substring(idx, idx + unit);
        if (preWord.equals(curWord)) {
            dfs(word, cnt + 1, preWord, idx + unit, unit);
            return;
        }

        if (cnt != 1) result += cnt;

        result += preWord;
        dfs(word, 1, curWord, idx + unit, unit);
    }

    public static void main(String[] args) {
        System.out.println(new Sol60057().solution("aabbaccc"));
    }
}

