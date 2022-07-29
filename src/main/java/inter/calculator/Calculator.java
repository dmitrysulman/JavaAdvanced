package inter.calculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    private static final Map<Character, Integer> PRIORITY = new HashMap<>();

    static {
        PRIORITY.put('~', 100);
        PRIORITY.put('+', 1);
        PRIORITY.put('-', 1);
        PRIORITY.put('*', 2);
        PRIORITY.put('/', 2);
    }

    int calculate(String mathString) {
        String string = createString(mathString);
        return calc(string);
    }

    private String createString(String mathString) {
        StringBuilder str = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < mathString.length(); i++) {
            char c = mathString.charAt(i);
            if (Character.isDigit(c)) {
                str.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                char fromTmpStack = stack.pop();
                while (fromTmpStack != '(') {
                    str.append(fromTmpStack);
                    fromTmpStack = stack.pop();
                }
            } else if (c == '-' && (i == 0 || mathString.charAt(i - 1) == '(' || mathString.charAt(i - 1) == '+' || mathString.charAt(i - 1) == '-') || mathString.charAt(i - 1) == '*' || mathString.charAt(i - 1) == '/') {
                stack.push('~');
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                Character fromTmpStack = stack.peekFirst();
                while (fromTmpStack != null && PRIORITY.containsKey(fromTmpStack) && PRIORITY.get(c) <= PRIORITY.get(fromTmpStack)) {
                    str.append(stack.pop());
                    fromTmpStack = stack.peekFirst();
                }
                stack.push(c);
                str.append(" ");
            }
        }
        while (!stack.isEmpty()) {
            str.append(stack.pop());
        }
        return str.toString();
    }

    private int calc(String string) {
        Deque<String> stack = new ArrayDeque<>();
        int position = 0;
        Pattern digit = Pattern.compile("(\\d+)\\D");
        Matcher matcher = digit.matcher(string);
        while (position < string.length()) {
            char c = string.charAt(position);
            int next = 1;
            if (Character.isDigit(c)) {
                matcher.find(position);
                String d = matcher.group(1);
                stack.push(d);
                next = d.length();
            } else if (c == '~') {
                int v = Integer.parseInt(stack.pop());
                v *= -1;
                stack.push(String.valueOf(v));
            } else if (c == '+') {
                int v1 = Integer.parseInt(stack.pop());
                int v2 = Integer.parseInt(stack.pop());
                int res = v2 + v1;
                stack.push(String.valueOf(res));
            } else if (c == '-') {
                int v1 = Integer.parseInt(stack.pop());
                int v2 = Integer.parseInt(stack.pop());
                int res = v2 - v1;
                stack.push(String.valueOf(res));
            } else if (c == '*') {
                int v1 = Integer.parseInt(stack.pop());
                int v2 = Integer.parseInt(stack.pop());
                int res = v2 * v1;
                stack.push(String.valueOf(res));
            } else if (c == '/') {
                int v1 = Integer.parseInt(stack.pop());
                int v2 = Integer.parseInt(stack.pop());
                int res = v2 / v1;
                stack.push(String.valueOf(res));
            }
            position += next;
        }
        return Integer.parseInt(stack.pop());
    }
}
