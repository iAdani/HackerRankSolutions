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
     * Complete the 'superReducedString' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String superReducedString(String s) {
        List<Character> l = new ArrayList();
        for (char c : s.toCharArray())
            l.add(c);
        int pos = 0;
        
        while (!l.isEmpty() && pos < l.size() - 1) {
            if (l.get(pos) == l.get(pos + 1)) {
                l.remove(pos);
                l.remove(pos);
                pos = 0;
            }
            else
                pos++;
        }
        
        if (l.isEmpty())
            return "Empty String";
            
        String sol = new String();
        for (char c : l)
            sol += c;
        return sol;
        
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.superReducedString(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
