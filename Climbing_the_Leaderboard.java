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
     * Complete the 'climbingLeaderboard' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY ranked
     *  2. INTEGER_ARRAY player
     */

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        List<Integer> sol = new ArrayList();
        int plen = player.size(), rlen = ranked.size(), maxRank = 1, lastRank = ranked.get(0);
        
        // Finding the max rank of the table
        for (int rank : ranked)
            if (rank < lastRank) {
                maxRank++;
                lastRank = rank;
            }
        
        int i = 0, j = rlen - 2, score = player.get(0);
        if (score < ranked.get(rlen - 1)) {
            while (i < plen - 1 && score < ranked.get(rlen - 1)) {
                sol.add(maxRank + 1);
                i++;
                score = player.get(i);
            }
            if (i == plen - 1)
                if (player.get(i) < ranked.get(rlen - 1)) {
                    sol.add(maxRank + 1);
                    return sol;
                }
            
        }
        else if (score == ranked.get(rlen - 1) || score < ranked.get(rlen - 2)) {
            sol.add(maxRank);
            i++;
        }
        
        // Iterating backwards
        for (; i < plen; i++) {
            score = player.get(i);
            while (j >= 0 && score > ranked.get(j)) {
                if (ranked.get(j) > ranked.get(j + 1))
                    maxRank--;
                j--;
            }
            if (j < 0) {
                sol.add(1);
                continue;
            }
            if (score == ranked.get(j)) {
                sol.add(maxRank - 1);
            }
            else
                sol.add(maxRank);
        }
            
        return sol;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int rankedCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> ranked = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int playerCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> player = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = Result.climbingLeaderboard(ranked, player);

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
