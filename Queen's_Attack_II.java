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
     * Complete the 'queensAttack' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER k
     *  3. INTEGER r_q
     *  4. INTEGER c_q
     *  5. 2D_INTEGER_ARRAY obstacles
     */

    public static boolean isObstacle(HashMap<Integer, HashSet<Integer>> obstacles, 
                                        int r_q, int c_q) {
                                            
        if (obstacles.containsKey(r_q) && obstacles.get(r_q).contains(c_q))
            return true;
        
        return false;
    }
     
    public static int directionalCheck(int n, int r_q, int c_q,
                             HashMap<Integer, HashSet<Integer>> obstacles, int r_d, int c_d) {
                                
        r_q += r_d;
        c_q += c_d;
        
        if (r_q > n || r_q < 1 || c_q > n || c_q < 1 ||
            isObstacle(obstacles, r_q, c_q)) {
                
                return 0;
            }
        
        return 1 + directionalCheck(n, r_q, c_q, obstacles, r_d, c_d);
        
    }
    
    public static int queensAttack(int n, int k, int r_q, int c_q, List<List<Integer>> obstacles) {
        List<Integer> directions = Arrays.asList(-1, 0, 1);
        int sol = 0;
        
        HashMap<Integer, HashSet<Integer>> obstaclesMap = new HashMap<>();
        for (int i = 0; i < k; i++) {
            if (obstaclesMap.containsKey(obstacles.get(i).get(0))) {
                obstaclesMap.get(obstacles.get(i).get(0)).add(obstacles.get(i).get(1));
            } else {
                obstaclesMap.put(obstacles.get(i).get(0), new HashSet<Integer>());
                obstaclesMap.get(obstacles.get(i).get(0)).add(obstacles.get(i).get(1));
            }
         }
        
        for (Integer r_d : directions)
            for (Integer c_d : directions)
                if (r_d != 0 || c_d != 0)
                    sol += directionalCheck(n, r_q, c_q, obstaclesMap, r_d, c_d);
                
        return sol;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        String[] secondMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int r_q = Integer.parseInt(secondMultipleInput[0]);

        int c_q = Integer.parseInt(secondMultipleInput[1]);

        List<List<Integer>> obstacles = new ArrayList<>();

        IntStream.range(0, k).forEach(i -> {
            try {
                obstacles.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.queensAttack(n, k, r_q, c_q, obstacles);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
