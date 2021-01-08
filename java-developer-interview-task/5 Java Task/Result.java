package stockmarketprediction;

import java.util.*;
import java.util.stream.IntStream;

public class Result {

    public static List<Integer> predictAnswer(List<Integer> stockData, List<Integer> queries) {
        List<Integer> result = new ArrayList<>();
        Stack<Integer> left = new Stack<>();
        Stack<Integer> right = new Stack<>();
        int [] minimumsLeft = new int[stockData.size()];
        int [] minimumsRight = new int[stockData.size()];
        for (int i = 0; i < stockData.size(); i++) {
            // left elements
            while (!left.isEmpty() && stockData.get(i) <= stockData.get(left.peek())) {
                left.pop();
            }
            if (left.isEmpty()) {
                minimumsLeft[i] = -1;
            }
            else {
                minimumsLeft[i] = left.peek();
            }
            left.push(i);

            // right elements
            while (!right.isEmpty() && stockData.get(stockData.size() - i - 1) <= stockData.get(right.peek())) {
                right.pop();
            }
            if (right.isEmpty()) {
                minimumsRight[stockData.size() - i - 1] = -1;
            }
            else {
                minimumsRight[stockData.size() - i - 1] = right.peek();
            }
            right.push(stockData.size() - i - 1);
        }

        for (Integer query: queries) {
            result.add(getMin(query - 1, minimumsLeft[query - 1], minimumsRight[query - 1]));
        }
        return result;
    }

    public static int getMin(int queryIndex, int leftIndex, int rightIndex) {
        return leftIndex == -1 && rightIndex == -1 ? -1
                 : leftIndex == -1 ? rightIndex + 1
                 : rightIndex == -1 ? leftIndex + 1
                 : Math.abs(rightIndex - queryIndex) <
                   Math.abs(leftIndex - queryIndex) ? rightIndex + 1
                 : leftIndex + 1;
    }

}