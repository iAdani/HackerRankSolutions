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

    private static boolean check(List<String> G, List<String> P, int gRow, int gCol) {
        for (int i = 0; i < P.size(); i++)
            for (int j = 0; j < P.get(0).length(); j++) {
                if (G.get(gRow + i).charAt(gCol + j) != P.get(i).charAt(j))
                    return false;
            }
            return true;
    }
    
    private static int newSumCol(int sum, int row, int col,
                                 int pRows, int pCols, List<String> G) {
        for (int i = row; i < row + pRows; i++) {
            sum -= Integer.parseInt(String.valueOf(G.get(i).charAt(col - 1)));
            sum += Integer.parseInt(String.valueOf(G.get(i).charAt(col + pCols - 1)));
        }
        return sum;
    }
    
    private static int newSumRow(int sum, int row, int col,
                                 int pRows, int pCols, List<String> G) {
        for (int i = col; i < col + pCols; i++) {
            sum -= Integer.parseInt(String.valueOf(G.get(row - 1).charAt(i)));
            sum += Integer.parseInt(String.valueOf(G.get(row + pRows - 1).charAt(i)));
        }
        return sum;
    }

    public static String gridSearch(List<String> G, List<String> P) {
        int pSum = 0, gSum = 0;
        int gRows = G.size(), gCols = G.get(0).length();
        int pRows = P.size(), pCols = P.get(0).length();
        
        for (int i = 0; i < pRows; i++) {
            for(int j = 0; j < pCols; j++) {
                pSum += Integer.parseInt(String.valueOf(P.get(i).charAt(j)));
                gSum += Integer.parseInt(String.valueOf(G.get(i).charAt(j)));
            }
        }
        
        int firstSum = gSum;
        for (int row = 0; row < gRows - pRows + 1; row++) {
            if (row > 0)
                gSum = newSumRow(firstSum, row, 0, pRows, pCols, G);
            if (gSum == pSum && check(G, P, row, 0))
                return "YES";
            firstSum = gSum;
            for (int col = 1; col < gCols - pCols + 1; col++) {
                gSum = newSumCol(gSum, row, col, pRows, pCols, G);
                if (gSum == pSum && check(G, P, row, col))
                    return "YES";
            }
        }
        
        return "NO";

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

                int R = Integer.parseInt(firstMultipleInput[0]);

                int C = Integer.parseInt(firstMultipleInput[1]);

                List<String> G = IntStream.range(0, R).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                    .collect(toList());

                String[] secondMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int r = Integer.parseInt(secondMultipleInput[0]);

                int c = Integer.parseInt(secondMultipleInput[1]);

                List<String> P = IntStream.range(0, r).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                    .collect(toList());

                String result = Result.gridSearch(G, P);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
