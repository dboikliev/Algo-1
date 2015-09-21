/**
 * Created by Deyan on 27.7.2015 ã..
 */
public class Change {
    public static void main(String[] args) {
        System.out.println(countCombinations(25));
    }


    public static int countCombinations(int sum) {
        int[] coins = new int[] { 1, 2, 5, 10, 20, 50, 100 };
        int[] sums = new int[sum + 1];
        for (int i = 0; i < sums.length; i++) {
            sums[i] = 0;
        }
        sums[0] = 1;

        for (int j = 0; j < coins.length; j++) {
            for (int i = 0; i < sums.length; i++) {
                if (i + coins[j] < sums.length) {
                    sums[i + coins[j]] += sums[i];
                }
            }
        }
        return sums[sum];
    }
}
