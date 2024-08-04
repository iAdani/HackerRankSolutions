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
     * Complete the 'surfaceArea' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY A as parameter.
     */
     
    private static int oneRowSolution(List<List<Integer>> A) {
        int sum = 0, rowSize = A.get(0).size();
        
        for (int i = 0; i < rowSize; i++) {
            // Sides
            if (i == 0 || i == rowSize - 1)
                sum += A.get(0).get(i);
            sum += 2 * A.get(0).get(i);
            
            // In-line difference
            if (i > 0) {
                if (A.get(0).get(i) > A.get(0).get(i - 1))
                    sum += A.get(0).get(i) - A.get(0).get(i - 1);
                else
                    sum += A.get(0).get(i - 1) - A.get(0).get(i);
            }
        }
        
        // Adding top & bottom
        return sum + 2 * rowSize;
    }
    
    private static int oneColSolution (List<List<Integer>> A) {
        int sum = 0; int rowsNumber = A.size();
        
        for (int i = 0; i < rowsNumber; i++) {
            // Sides
            if (i == 0 || i == rowsNumber - 1)
                sum += A.get(i).get(0);
            sum += 2 * A.get(i). get(0);
            
            // In-line difference
            if (i > 0) {
                if (A.get(i).get(0) > A.get(i - 1).get(0))
                    sum += A.get(i).get(0) - A.get(i - 1).get(0);
                else
                    sum += A.get(i - 1).get(0) - A.get(i).get(0);
            }
        }
        // Adding top & bottom
        return sum + 2 * rowsNumber;
    }

    public static int surfaceArea(List<List<Integer>> A) {
        int rowsNumber = A.size(), rowSize = A.get(0).size(), solution = 0, sum = 0;
        
        // Edge cases
        if (rowsNumber == 1) {
            if (rowSize == 1)
                return 4 * A.get(0).get(0) + 2;
            else
                return oneRowSolution(A);
        }
        if (rowSize == 1)
            return oneColSolution(A);
        
        // Normal cases
        for (int row = 0; row < rowsNumber; row++) {
            sum = 0;
            for (int cell = 0; cell < rowSize; cell++) {
                // External Sides
                if (cell == 0 || cell == rowSize - 1)
                    sum += A.get(row).get(cell);
                
                // First and last rows
                if (row == 0 || row == rowsNumber - 1)
                    sum += A.get(row).get(cell);
                
                // In-line difference
                if (cell > 0) {
                    if (A.get(row).get(cell) > A.get(row).get(cell - 1))
                        sum += A.get(row).get(cell) - A.get(row).get(cell - 1);
                    else
                        sum += A.get(row).get(cell - 1) - A.get(row).get(cell);
                }
                
                // Previous-line difference
                if (row > 0 && A.get(row).get(cell) > A.get(row - 1).get(cell)) {
                    sum += A.get(row).get(cell) - A.get(row - 1).get(cell);
                }
                
                // Next-line difference
                if (row < rowsNumber - 1 && A.get(row).get(cell) > A.get(row + 1).get(cell)) {
                    sum += A.get(row).get(cell) - A.get(row + 1).get(cell);
                }
                
            }
            solution += sum;
        }
        
        // Adding top & bottom
        return solution + (2 * rowsNumber * rowSize);
    }    
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int H = Integer.parseInt(firstMultipleInput[0]);

        int W = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> A = new ArrayList<>();

        IntStream.range(0, H).forEach(i -> {
            try {
                A.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.surfaceArea(A);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
