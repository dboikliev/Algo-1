import javafx.util.Pair;

import java.util.Stack;

/**
 * Created by Deyan on 27.7.2015 �..
 */
public class LongestSubsequence {
    public static void main(String[] args) {
        findSequence(new int[] {6, 1, 5, 3, 7, 1, 2, 5, 7, 4});
    }

    public static void findSequence(int[] numbers) {
        int[] maxes = new int[numbers.length];
        for (int i = 0; i < maxes.length; i++) {
            maxes[i] = 1;
        }

        int[] path = new int[numbers.length];
        for (int i = 1; i < numbers.length; i++) {
            int max = -1;
            int n = -1;
            for (int j = 0; j < i; j++) {
                if (numbers[j] < numbers[i]) {
                    if (maxes[j] > max) {
                        n = j;
                        max = maxes[j];
                    }
                }
            }

            if (n > 0) {
                maxes[i] += maxes[n];
                path[i] = n;
            }

        }
    }
}
