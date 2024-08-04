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
     * Complete the 'beautifulTriplets' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER d
     *  2. INTEGER_ARRAY arr
     */

    public static int beautifulTriplets(int d, List<Integer> arr) {
        if (arr.size() < 3)
            return 0;
            
        int count = 0, pos1 = 0, pos2 = 1, pos3 = 2;
        while(pos1 < arr.size() - 2) {
            while (pos2 < arr.size() && arr.get(pos2) - arr.get(pos1) < d)
                pos2++;
            if (pos2 >= arr.size()) {
                pos1++;
                pos2 = pos1 + 1;
                pos3 = pos2 + 1;
                continue;
            }
            
            while (pos3 < arr.size() && arr.get(pos3) - arr.get(pos2) < d)
                pos3++;
            if (pos3 >= arr.size()) {
                pos1++;
                pos2 = pos1 + 1;
                pos3 = pos2 + 1;
                continue;
            }
            
            if (arr.get(pos2) - arr.get(pos1) == d && arr.get(pos3) - arr.get(pos2) == d)
                count++;
            pos1++; 
        }
        return count;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int d = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.beautifulTriplets(d, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
