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
     * Complete the 'absolutePermutation' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER k
     */
     
    private static boolean recursion (int n, int k, List<Integer> sol, boolean[] used) {
        int pos = sol.size();
        if (pos == n)
            return true;
        pos++;
                        
        if (pos - k > 0 && !used[pos - k]) {
            sol.add(pos - k);
            used[pos - k] = true;
            
            if (recursion(n, k, sol, used))
                return true;
            used[pos - k] = false;
            sol.remove(pos - 1);
        }
        
        if (pos + k <= n && !used[pos + k]) {
            sol.add(pos + k);
            used[pos + k] = true;
            
            if (recursion(n, k, sol, used))
                return true;
            used[pos + k] = false;
            sol.remove(pos - 1);
        }
        
        return false;     
    }

    public static List<Integer> absolutePermutation(int n, int k) {
        // Basic case
        if (k == 0) {
            List<Integer> P = new ArrayList();
            for (int i = 1; i <= n; i++)
                P.add(i);
            return P;
        }
        
        // Mathematically impossible to have that kind of permutation
        if (n % (2 * k) != 0)
            return Collections.singletonList(-1);
        
        // Normal case
        boolean[] used = new boolean[n + 1];
        List<Integer> solution = new ArrayList();
        if (!recursion(n, k, solution, used))
            return Collections.singletonList(-1);
            
        return solution;
          
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int k = Integer.parseInt(firstMultipleInput[1]);

                List<Integer> result = Result.absolutePermutation(n, k);

                bufferedWriter.write(
                    result.stream()
                        .map(Object::toString)
                        .collect(joining(" "))
                    + "\n"
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
