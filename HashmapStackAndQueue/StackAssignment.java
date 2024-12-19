package HashmapStackAndQueue;
import java.util.*;

public class StackAssignment {

    // Q1. Largest Rectangle in a Binary Matrix
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int maxArea = 0;
        int[] heights = new int[matrix[0].length];

        for (char[] row : matrix) {
            for (int i = 0; i < row.length; i++) {
                heights[i] = row[i] == '1' ? heights[i] + 1 : 0;
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

    private int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0, n = heights.length;

        for (int i = 0; i <= n; i++) {
            int h = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        return maxArea;
    }

    // Q2. Decode String
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resultStack = new Stack<>();
        String result = "";
        int i = 0;

        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                int count = 0;
                while (Character.isDigit(s.charAt(i))) {
                    count = count * 10 + (s.charAt(i) - '0');
                    i++;
                }
                countStack.push(count);
            } else if (s.charAt(i) == '[') {
                resultStack.push(result);
                result = "";
                i++;
            } else if (s.charAt(i) == ']') {
                StringBuilder temp = new StringBuilder(resultStack.pop());
                int count = countStack.pop();
                for (int j = 0; j < count; j++) {
                    temp.append(result);
                }
                result = temp.toString();
                i++;
            } else {
                result += s.charAt(i);
                i++;
            }
        }
        return result;
    }

    // Q3. Baseball Game
    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();
        for (String op : ops) {
            if (op.equals("+")) {
                int top = stack.pop();
                int newTop = top + stack.peek();
                stack.push(top);
                stack.push(newTop);
            } else if (op.equals("D")) {
                stack.push(2 * stack.peek());
            } else if (op.equals("C")) {
                stack.pop();
            } else {
                stack.push(Integer.parseInt(op));
            }
        }
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }

    // Q4. Asteroids Collision
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int asteroid : asteroids) {
            while (!stack.isEmpty() && asteroid < 0 && stack.peek() > 0) {
                if (stack.peek() > -asteroid) {
                    asteroid = 0;
                } else if (stack.peek() < -asteroid) {
                    stack.pop();
                    continue;
                } else {
                    stack.pop();
                    asteroid = 0;
                }
            }
            if (asteroid != 0) {
                stack.push(asteroid);
            }
        }
        int[] result = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return result;
    }

    // Q5. Daily Temperatures
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                result[idx] = i - idx;
            }
            stack.push(i);
        }
        return result;
    }

    // Main method for testing all five problems
    public static void main(String[] args) {
        StackAssignment stackObj = new StackAssignment();

        // Test Q1: Maximal Rectangle
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.println("Maximal Rectangle Area: " + stackObj.maximalRectangle(matrix));

        // Test Q2: Decode String
        String s1 = "3[a]2[bc]";
        String s2 = "3[a2[c]]";
        String s3 = "2[abc]3[cd]ef";
        System.out.println("Decoded String 1: " + stackObj.decodeString(s1));
        System.out.println("Decoded String 2: " + stackObj.decodeString(s2));
        System.out.println("Decoded String 3: " + stackObj.decodeString(s3));

        // Test Q3: Baseball Game
        String[] ops1 = {"5", "2", "C", "D", "+"};
        String[] ops2 = {"5", "-2", "4", "C", "D", "9", "+", "+"};
        System.out.println("Baseball Game Result 1: " + stackObj.calPoints(ops1));
        System.out.println("Baseball Game Result 2: " + stackObj.calPoints(ops2));

        // Test Q4: Asteroids Collision
        int[] asteroids1 = {5, 10, -5};
        int[] asteroids2 = {8, -8};
        int[] asteroids3 = {10, 2, -5};
        System.out.println("Asteroids Collision Result 1: " + Arrays.toString(stackObj.asteroidCollision(asteroids1)));
        System.out.println("Asteroids Collision Result 2: " + Arrays.toString(stackObj.asteroidCollision(asteroids2)));
        System.out.println("Asteroids Collision Result 3: " + Arrays.toString(stackObj.asteroidCollision(asteroids3)));

        // Test Q5: Daily Temperatures
        int[] temperatures1 = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] temperatures2 = {30, 40, 50, 60};
        int[] temperatures3 = {30, 60, 90};
        System.out.println("Daily Temperatures Result 1: " + Arrays.toString(stackObj.dailyTemperatures(temperatures1)));
        System.out.println("Daily Temperatures Result 2: " + Arrays.toString(stackObj.dailyTemperatures(temperatures2)));
        System.out.println("Daily Temperatures Result 3: " + Arrays.toString(stackObj.dailyTemperatures(temperatures3)));
    }
}

