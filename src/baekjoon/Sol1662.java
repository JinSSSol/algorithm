package baekjoon;

import java.util.Scanner;
import java.util.Stack;

public class Sol1662 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split("");

        Stack<String> stack = new Stack<>();

        for (String s : str) {
            if (s.equals(")")) {
                int len = 0;
                while (!stack.peek().equals("(")) {
                    if (stack.pop().equals("*")) {
                        len += Integer.parseInt(stack.pop());
                        continue;
                    }
                    len++;
                }
                stack.pop();
                len *= Integer.parseInt(stack.pop());
                stack.push(String.valueOf(len));
                stack.push("*");
            } else {
                stack.push(s);
            }
        }

        int result = 0;
        while(!stack.isEmpty()) {
            if (stack.pop().equals("*")) {
                result += Integer.parseInt(stack.pop());
            } else {
                result++;
            }
        }

        System.out.println(result);
    }
}
