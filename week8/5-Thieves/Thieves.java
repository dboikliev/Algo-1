/**
 * Created by Deyan on 30.7.2015 ã..
 */
public class Thieves {
    public static void main(String[] args) {
        System.out.println(countValue(5, 5, new int[] { 5, 100, 1, 1, 3 }, new int[] {3, 7, 1, 1, 2 }));
    }

    private static int countValue(int n, int w, int[] values, int[] capacities) {
        int[][] matrix = new int[n + 1][w + 1];
        for (int i = 0; i <= w; i++) {
            matrix[0][i] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= w; j++) {
                if (capacities[i - 1] <= j) {
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i - 1][j - capacities[i - 1]] + values[i - 1]);
                }
                else {
                    matrix[i][j] = matrix[i - 1][j];
                }
            }
        }

        return matrix[matrix.length - 1][matrix.length - 1];
    }
}
