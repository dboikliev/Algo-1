import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Deyan on 30.7.2015 ã..
 */
public class LongestCommonSubstring {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        System.out.println(findString(str1, str2));
    }

    public static String findString(String a, String b) {
        int[][] lengths = new int[a.length()][b.length()];
        int max = -1;
        int maxI = -1;
        for (int i = 1; i < a.length(); i++) {
            for (int j = 1; j < b.length(); j++) {
                if (Character.compare(a.charAt(i), b.charAt(j)) == 0) {
                    lengths[i][j] = lengths[i - 1][j - 1] + 1;
                    if ( max < lengths[i][j]) {
                        max = lengths[i][j];
                        maxI = i;
                    }
                }
                else {
                    lengths[i][j] = 0;
                }
            }
        }
        return a.substring(maxI - max + 1, maxI + 1);
    }
}
