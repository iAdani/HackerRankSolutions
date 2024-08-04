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
     * Complete the 'formingMagicSquare' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY s as parameter.
     */
     
     private static final List<List<List<Integer>>> squares = Arrays.asList(
         Arrays.asList(Arrays.asList(8, 1, 6), Arrays.asList(3, 5, 7), Arrays.asList(4, 9, 2)),
         Arrays.asList(Arrays.asList(6, 1, 8), Arrays.asList(7, 5, 3), Arrays.asList(2, 9, 4)),
         Arrays.asList(Arrays.asList(4, 9, 2), Arrays.asList(3, 5, 7), Arrays.asList(8, 1, 6)),
         Arrays.asList(Arrays.asList(2, 9, 4), Arrays.asList(7, 5, 3), Arrays.asList(6, 1, 8)),
         Arrays.asList(Arrays.asList(8, 3, 4), Arrays.asList(1, 5, 9), Arrays.asList(6, 7, 2)),
         Arrays.asList(Arrays.asList(4, 3, 8), Arrays.asList(9, 5, 1), Arrays.asList(2, 7, 6)),
         Arrays.asList(Arrays.asList(6, 7, 2), Arrays.asList(1, 5, 9), Arrays.asList(8, 3, 4)),
         Arrays.asList(Arrays.asList(2, 7, 6), Arrays.asList(9, 5, 1), Arrays.asList(4, 3, 8))
     );
     
    public static int formingMagicSquare(List<List<Integer>> s) {
        int[] costs = new int[8];
        int idx = 0;
        for (int sq = 0; sq < 8; sq++)
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    costs[sq] += Math.abs(squares.get(sq).get(i).get(j) - s.get(i).get(j));

        return Collections.min(Arrays.stream(costs).boxed().collect(Collectors.toList()));
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        List<List<Integer>> s = new ArrayList<>();

        IntStream.range(0, 3).forEach(i -> {
            try {
                s.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.formingMagicSquare(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
