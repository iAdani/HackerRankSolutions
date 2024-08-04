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
     * Complete the 'acmTeam' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts STRING_ARRAY topic as parameter.
     */
     
    public static int numOfOnes(String first, String second) {
        int count = 0, pos = 0;
        while (pos < first.length() && pos < second.length()) {
            if (first.charAt(pos) == '1' || second.charAt(pos) == '1')
                count++;
            pos++;
        }
        
        while (pos < first.length()) {
            if (first.charAt(pos) == '1')
                count++;
            pos++;
        }
        
        while (pos < second.length()) {
            if (second.charAt(pos) == '1')
                count++;
            pos++;
        }
        return count;
    }

    public static List<Integer> acmTeam(List<String> topic) {
        List<Integer> sol = Arrays.asList(0, 0);
        for (int i = 0; i < topic.size() - 1; i++) {
            for (int j = i + 1; j < topic.size(); j++) {
                int ones = numOfOnes(topic.get(i), topic.get(j));

                if (ones == sol.get(0))
                    sol.set(1, sol.get(1) + 1);

                if (ones > sol.get(0)) {
                    sol.set(0, ones);
                    sol.set(1, 1);
                }
            }
        }
        return sol;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<String> topic = IntStream.range(0, n).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        List<Integer> result = Result.acmTeam(topic);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
