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
     * Complete the 'biggerIsGreater' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING w as parameter.
     */
    
    private static void swap (char[] s, int index1, int index2) {
        char temp = s[index1];
        s[index1] = s[index2];
        s[index2] = temp;
    }
    
    public static String biggerIsGreater(String w) {
        int i = w.length() - 1;
        
        while (i > 0) {
            if (w.charAt(i) > w.charAt(i - 1))
                break;
            i--;
        }
        
        if (i == 0)
            return "no answer";
          
            
        int nextSmallChar = i;
        for (int j = i + 1; j < w.length(); j++)
            if (w.charAt(j) > w.charAt(i - 1) && w.charAt(j) < w.charAt(nextSmallChar))
                nextSmallChar = j;
        
        char[] wArray = w.toCharArray();
        swap(wArray, i - 1, nextSmallChar);
        Arrays.sort(wArray, i, w.length());
        
        return new String(wArray);
        

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int T = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, T).forEach(TItr -> {
            try {
                String w = bufferedReader.readLine();

                String result = Result.biggerIsGreater(w);

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
