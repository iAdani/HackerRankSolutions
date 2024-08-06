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
     * Complete the 'insertionSort2' function below.
     *
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY arr
     */
    
    private static void printArr(List<Integer> arr) {
        for (Integer n : arr)
            System.out.print(n + " ");
        System.out.print("\n");
    }
    
    public static void insertionSort1(int n, List<Integer> arr) {
        int pos = n - 1;
        while (pos > 0) {
            if (arr.get(pos) < arr.get(pos - 1)) {
                int selected = arr.get(pos);
                while (pos > 0 && arr.get(pos - 1) > selected) {           
                    arr.set(pos, arr.get(pos - 1));
                    pos--;
                    
                }
                arr.set(pos, selected);
                pos = n - 1;
            }
            else
                pos--;
        }        
    }
    
    public static void insertionSort2(int n, List<Integer> arr) {
        if (arr.size() == 1)
            printArr(arr);
            
        for (int i = 1; i < n; i++) {
            if (arr.get(i) < arr.get(i - 1))
                insertionSort1(i + 1, arr);
            printArr(arr);
        }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        Result.insertionSort2(n, arr);

        bufferedReader.close();
    }
}
