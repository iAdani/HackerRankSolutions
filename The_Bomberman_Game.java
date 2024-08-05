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
     * Complete the 'bomberMan' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. STRING_ARRAY grid
     */
     
    private static char[][] gridToArr(List<String> grid) {
        char[][] arr = new char[grid.size()][grid.get(0).length()];
        for (int i = 0; i < grid.size(); i++) {
            arr[i] = grid.get(i).toCharArray();
        }
        return arr;
    }
    
    private static List<String> arrToGrid(char[][] arr, int rowsNumber) {
        List<String> ret = new ArrayList();
        for (int i = 0; i < rowsNumber; i++)
            ret.add(new String(arr[i]));
        return ret;
    }
    
    public static List<String> bomberMan(int n, List<String> grid) {
        if (n == 1)
            return grid;
            
        int rowsNumber = grid.size(), rowLength = grid.get(0).length();
        List<String> allBombs = new ArrayList();
        for (int i = 0; i < rowsNumber; i++)
               allBombs.add("O".repeat(rowLength));
        
        if (n % 2 == 0) {
            return allBombs;
        }
        
        char[][] solution = new char[rowsNumber][rowLength];
        char[][] last = gridToArr(grid);
        
        // Pattern reduction
        n = (n % 4 == 1) ? 5 : 3;
        for (; n > 1; n -= 2) {
            for (int row = 0; row < rowsNumber; row++)
                Arrays.fill(solution[row], 'O');
            
            for (int row = 0; row < rowsNumber; row++) {
                for (int cell = 0; cell < rowLength; cell++) {
                    if (last[row][cell] == 'O') {
                        solution[row][cell] = '.';
                        if (row > 0)
                            solution[row - 1][cell] = '.';
                        if (cell > 0)
                            solution[row][cell - 1] = '.';
                        if (row < rowsNumber - 1)
                            solution[row + 1][cell] = '.';
                        if (cell < rowLength - 1)
                            solution[row][cell + 1] = '.';
                    }
                }
            }
            if (Arrays.deepEquals(solution, last))
                return arrToGrid(solution, rowsNumber);
            for (int i = 0; i < rowsNumber; i++)
                System.arraycopy(solution[i], 0, last[i], 0, rowLength);
        }
        
        return arrToGrid(solution, rowsNumber);

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int r = Integer.parseInt(firstMultipleInput[0]);

        int c = Integer.parseInt(firstMultipleInput[1]);

        int n = Integer.parseInt(firstMultipleInput[2]);

        List<String> grid = IntStream.range(0, r).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        List<String> result = Result.bomberMan(n, grid);

        bufferedWriter.write(
            result.stream()
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
