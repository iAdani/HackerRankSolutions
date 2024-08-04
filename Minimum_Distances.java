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
     * Complete the 'minimumDistances' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY a as parameter.
     */

    public static int minimumDistances(List<Integer> a) {
        Map<Integer, List<Integer>> map = new HashMap();
        int min = a.size() + 1;
        
        for (int i = 0; i < a.size(); i++) {
            if (!(map.containsKey(a.get(i))))
                map.put(a.get(i), new ArrayList<Integer>());
            map.get(a.get(i)).add(i);
        }
        
        for (Integer key : map.keySet()) {
            if (map.get(key).size() > 1) {
                for (int index = 1; index < map.get(key).size(); index++) {
                    if (map.get(key).get(index) - map.get(key).get(index - 1) < min)
                        min = map.get(key).get(index) - map.get(key).get(index - 1);
                }
            }
        }
        
        return (min > a.size()) ? -1 : min;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.minimumDistances(a);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
