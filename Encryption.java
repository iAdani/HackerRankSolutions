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
     * Complete the 'encryption' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String encryption(String s) {        
        int len = s.length();
        int rows = (int)Math.sqrt(len);
        int cols = (rows * rows == len) ? rows : rows + 1;
        if (rows * cols < len)
            rows++;
        
        List<String> split = new ArrayList();
        int i;
        for (i = 0; i < rows - 1; i++) {
            split.add(s.substring(i * cols, (i + 1) * cols));
        }
        split.add(s.substring(i * cols, len));
        
        List<Character> encrypt = new ArrayList();
        for(int j = 0; j < cols; j++) {
            for (String row : split) {
                if (j < row.length())
                    encrypt.add(row.charAt(j));
            }
            encrypt.add(' ');
        }
        
        return encrypt.stream().map(Object::toString)
                        .collect(Collectors.joining(""));

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.encryption(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
