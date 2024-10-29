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
     * Complete the 'weightedUniformStrings' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. INTEGER_ARRAY queries
     */

    public static List<String> weightedUniformStrings(String s, List<Integer> queries) {
    // Write your code here
        List<String> sol = new ArrayList<>();
        char[] arr = s.toCharArray();
        int len = arr.length;
        if (len < 2) {
            for (int i = 0; i < queries.size(); i++)
                sol.add("No");
            if (s.length() > 0) {
                if (queries.contains((int)arr[0] - 'a' + 1))
                    sol.set(queries.indexOf((int)arr[0] - 'a' + 1), "Yes");
            }
            return sol;
        }

        
        
        Map<Character, Integer> freq = new HashMap<>();
        int idx = 0;
        while (idx < len - 1) {
            int count = 1;
            while (idx < len - 1 && arr[idx] == arr[idx + 1]) {
                count++;
                idx++;
            }
            
            if (freq.containsKey(arr[idx])) {
                if (freq.get(arr[idx]) < count)
                    freq.put(arr[idx], count);
            } else
                freq.put(arr[idx], count);
               
            idx++;
        }
        if (arr[len - 1] != arr[len - 2]) {
            if (!freq.containsKey(arr[len - 1]))
                freq.put(arr[len - 1], 1);
        }
        
        Set<Integer> subs = new HashSet<>();
        for (Character key : freq.keySet()) {
            for (int i = freq.get(key); i > 0; i--) {
                subs.add(((int)key - 'a' + 1) * i);
            }
        }
        
        for (Integer q : queries) {
            if (subs.contains(q))
                sol.add("Yes");
            else
                sol.add("No");
        }
        
        return sol;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        List<String> result = Result.weightedUniformStrings(s, queries);

        bufferedWriter.write(
            result.stream()
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
