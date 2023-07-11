public class Sol131130 {

    public int solution(int[] cards) {
        boolean[] visited = new boolean[cards.length + 1];
        int[] parents = new int[cards.length + 1];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;

        for (int i = 0; i < cards.length; i++) {
            if (visited[cards[i]]) {
                continue;
            }

            int cnt = 0;
            visited[cards[i]] = true;
            int cur = cards[i];
            // 그룹 찾기
            while (find(parents, cur) != find(parents, cards[cur - 1])) {
                union(parents, i, cards[cur - 1]);
                visited[cards[cur - 1]] = true;
                cur = cards[cur - 1];
                cnt++;
            }

            if (cnt <= 0) {
                cnt = 1;
            }
            if (max1 < cnt) {
                max2 = max1;
                max1 = cnt;
            } else if (max2 < cnt) {
                max2 = cnt;
            }
        }
        return max1 * max2;
    }

    int find(int[] parents, int idx) {
        if (parents[idx] == idx) {
            return idx;
        }

        return find(parents, parents[idx]);
    }

    void union(int[] parents, int a, int b) {
        int pA = find(parents, a);
        int pB = find(parents, b);

        if (pA < pB) {
            parents[pB] = pA;
        } else {
            parents[pA] = pB;
        }
    }

    public static void main(String[] args) {
        int[] cards = {8, 6, 3, 7, 2, 5, 1, 4};
        System.out.println(new Sol131130().solution(cards));
    }
}
