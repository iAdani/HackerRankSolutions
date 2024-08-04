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
     * Complete the 'happyLadybugs' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING b as parameter.
     */
     
     private static String checkHappy(String b) {
         if (b.charAt(0) != b.charAt(1) ||
             b.charAt(b.length() - 2) != b.charAt(b.length() - 1))
            return "NO";
         for (int i = 1; i < b.length() - 1; i++) {
             if (b.charAt(i - 1) != b.charAt(i) && b.charAt(i) != b.charAt(i + 1))
                return "NO";
         }
         return "YES";
     }

    public static String happyLadybugs(String b) {
        // Count colors
        Map<Character, Integer> colors = new HashMap<>();
        int spaces = 0;
        
        for (Character color : b.toCharArray()) {
            if (color == '_') {
                spaces++;
                continue;
            }
            if (!colors.containsKey(color))
                colors.put(color, 1);
            else
                colors.put(color, colors.get(color) + 1);
        }
        
        if (colors.containsValue(1))
            return "NO";
        if (spaces == 0)
            return checkHappy(b);
        return "YES";

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int g = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, g).forEach(gItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                String b = bufferedReader.readLine();

                String result = Result.happyLadybugs(b);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
