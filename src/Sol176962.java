import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class Sol176962 {

    public String[] solution(String[][] plans) {
        List<String> list = new ArrayList<>();
        PriorityQueue<Work> pq = new PriorityQueue<>(Comparator.comparing(x -> x.startTime));
        Stack<Work> stack = new Stack<>();

        for (String[] plan : plans) {
            pq.offer(new Work(plan[0], plan[1], plan[2]));
        }

        for (int i = 0; i < plans.length; i++) {
            Work cur = pq.poll();

            int between = 0;
            if (!stack.isEmpty()) {
                between = stack.peek().minus(cur.startTime);
            }

            while (!stack.isEmpty() && between > 0) {
                // 현재 시작하는 시간에 따라 남은 시간 갱신, 끝난 과제들 pop()
                Work pre = stack.pop();

                if (between < pre.time) {
                    stack.push(new Work(pre.name, pre.startTime, pre.time - between));
                    break;
                }

                list.add(pre.name);
                between -= pre.time;
            }
            stack.push(cur);
        }

        while (!stack.isEmpty()) {
            list.add(stack.pop().name);
        }

        return list.toArray(String[]::new);
    }

    static class Work {

        String name;
        LocalTime startTime;
        int time;

        Work(String name, String startTime, String time) {
            this.name = name;
            this.startTime = LocalTime.of(Integer.parseInt(startTime.split(":")[0]),
                Integer.parseInt(startTime.split(":")[1]));
            this.time = Integer.parseInt(time);
        }

        Work(String name, LocalTime startTime, int time) {
            this.name = name;
            this.startTime = startTime;
            this.time = time;
        }

        int minus(LocalTime endTime) {
            int minute = endTime.getMinute() - this.startTime.getMinute();
            int hour = endTime.getHour() - this.startTime.getHour();
            if (minute < 0) {
                minute += 60;
                hour--;
            }

            return (hour * 60) + minute;
        }
    }

    public static void main(String[] args) {
        String[][] plans = {{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}};
        System.out.println(Arrays.toString(new Sol176962().solution(plans)));
    }

}
