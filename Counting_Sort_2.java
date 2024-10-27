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
     * Complete the 'countingSort' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */
    
    private static List<Integer> getCountArray(List<Integer> arr) {
    // Write your code here
        List<Integer> countArr = new ArrayList<>(Collections.nCopies(100, 0));
        for (Integer num : arr)
            countArr.set(num, countArr.get(num) + 1);
        return countArr;
    }
    
    public static List<Integer> countingSort(List<Integer> arr) {
    // Write your code here
         List<Integer> countArr = getCountArray(arr);
         arr.clear();
         for (int i = 0; i < countArr.size(); i++) {
             for (int j = 0; j < countArr.get(i); j++)
                arr.add(i);
         }
         
         return arr;
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

        List<Integer> result = Result.countingSort(arr);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining(" "))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
