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
     * Complete the 'countSort' function below.
     *
     * The function accepts 2D_STRING_ARRAY arr as parameter.
     */

    public static void countSort(List<List<String>> arr) {
    // Write your code here
        int dashs = arr.size() / 2;
        
        List<List<String>> countArr = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            countArr.add(new ArrayList<String>());
            
        for (int i = 0; i < arr.size(); i++) {
            int index = Integer.valueOf(arr.get(i).get(0));
            if (dashs > 0) {
                countArr.get(index).add("-");
                dashs--;
            }
            else
                countArr.get(index).add(arr.get(i).get(1));
        }
        
        System.out.println(
        countArr.stream()
            .map(item -> String.join(" ", item))
            .collect(Collectors.joining(" "))
            .trim()
    );

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> arr = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                arr.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Result.countSort(arr);

        bufferedReader.close();
    }
}
