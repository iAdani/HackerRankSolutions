import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'kaprekarNumbers' function below.
     *
     * The function accepts following parameters:
     *  1. INTEGER p
     *  2. INTEGER q
     */
     
     public static int findMidIndex(int len) {
         if (len % 2 == 0)
            return len;
        return len - 1;
     }
     
    public static boolean isKaprekar(String start, String end, int n) {
        int first = Integer.parseInt(start), second = Integer.parseInt(end);
        if (first + second == n)
            return true;
        return false;
    }

    public static void kaprekarNumbers(int p, int q) {
        boolean found = false;
        if (p < 10) {
            found = true;
            if (p < 2)
                System.out.print("1 9 ");
            else 
                System.out.print("9 ");
            p = 10;
        }
        while (p <= q) {
            int pLength = Integer.toString(p).length();
            long squared = Long.valueOf(p) * Long.valueOf(p);
            String squaredString = Long.toString(squared);
            String start = squaredString.substring(0, squaredString.length() - pLength);
            String end = squaredString.substring(squaredString.length() - pLength, squaredString.length());
            if (isKaprekar(start, end, p)) {
                System.out.print(p + " ");
                found = true;
            }
            p++;
        }
        if (!found)
            System.out.println("INVALID RANGE");
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int p = Integer.parseInt(bufferedReader.readLine().trim());

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        Result.kaprekarNumbers(p, q);

        bufferedReader.close();
    }
}
