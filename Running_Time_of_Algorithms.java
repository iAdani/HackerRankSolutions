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
     * Complete the 'runningTime' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    // Counts shifts in Insertion Sort    
    public static int runningTime(List<Integer> arr) {
    // Write your code here
        if (arr.size() == 1)
            return 0;
        int n = arr.size(), count = 0;
            
        int pos = n - 1;
        while (pos > 0) {
            if (arr.get(pos) < arr.get(pos - 1)) {
                int selected = arr.get(pos);
                while (pos > 0 && arr.get(pos - 1) > selected) {           
                    arr.set(pos, arr.get(pos - 1));
                    pos--;
                    count++;
                    
                }
                arr.set(pos, selected);
                pos = n - 1;
            }
            else
                pos--;
        }
        return count;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.runningTime(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
