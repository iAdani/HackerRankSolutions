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
     * Complete the 'cavityMap' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts STRING_ARRAY grid as parameter.
     */
    
    public static String setX(String s, int index) {
        char[] charArray = s.toCharArray();
        charArray[index] = 'X';
        return new String (charArray);
    }

    public static List<String> cavityMap(List<String> grid) {
        
        for (int row = 1; row < grid.size() - 1; row++) {
            for (int col = 1; col < grid.get(0).length() - 1; col++) {
                char cell = grid.get(row).charAt(col);
                if (cell > grid.get(row + 1).charAt(col) && 
                    cell > grid.get(row).charAt(col + 1) &&
                    cell > grid.get(row - 1).charAt(col) &&
                    cell > grid.get(row).charAt(col - 1)) {
                        grid.set(row, setX(grid.get(row), col));
                    }
            }
        }
        
        return grid;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> grid = IntStream.range(0, n).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        List<String> result = Result.cavityMap(grid);

        bufferedWriter.write(
            result.stream()
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
