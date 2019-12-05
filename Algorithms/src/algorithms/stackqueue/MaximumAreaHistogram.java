package Algorithms.src.algorithms.stackqueue;

import java.util.Stack;

/**
 * 14/01/2018
 *
 * @author tiwariabhishek
 * <p>
 * <p>
 * Given an array representing height of bar in bar graph, find max histogram
 * area in the bar graph. Max histogram will be max rectangular area in the
 * graph.
 * <p>
 * Maintain a stack
 * <p>
 * If stack is empty or value at index of stack is less than or equal to value at current
 * index, push this into stack.
 * Otherwise keep removing values from stack till value at index at top of stack is
 * less than value at current index.
 * While removing value from stack calculate area
 * if stack is empty
 * it means that till this point value just removed has to be smallest element
 * so area = input[top] * i
 * if stack is not empty then this value at index top is less than or equal to
 * everything from stack top + 1 till i. So area will
 * area = input[top] * (i - stack.peek() - 1);
 * Finally maxArea is area if area is greater than maxArea.
 * <p>
 * <p>
 * Time complexity is O(n)
 * Space complexity is O(n)
 * <p>
 * References:
 * http://www.geeksforgeeks.org/largest-rectangle-under-histogram/
 */

public class MaximumAreaHistogram {

    private int maxHistogramArea(int[] input) {
        Stack<Integer> indexStack = new Stack<>();
        int arrayIndex = 0, maxArea = 0;
        while (arrayIndex < input.length) {
            if (indexStack.isEmpty() || input[arrayIndex] >= input[indexStack.peek()]) {
                indexStack.push(arrayIndex++);
            } else {
                int index = indexStack.pop(), area = 0;
                area = indexStack.isEmpty() ? arrayIndex * input[index] :
                        (arrayIndex - indexStack.peek() - 1) * input[index];
                if (area > maxArea) maxArea = area;
            }
        }
        while (!indexStack.isEmpty()) {
            int index = indexStack.pop(), area = 0;
            area = indexStack.isEmpty() ? arrayIndex * input[index] :
                    (arrayIndex - indexStack.peek() - 1) * input[index];
            if (area > maxArea) maxArea = area;
        }
        return maxArea;
    }

    public static void main(String[] args) {
        MaximumAreaHistogram mh = new MaximumAreaHistogram();
        int input[] = {2, 2, 2, 6, 1, 5, 4, 2, 2, 2, 2};
        int maxArea = mh.maxHistogramArea(input);
        System.out.println(maxArea);
    }
}
