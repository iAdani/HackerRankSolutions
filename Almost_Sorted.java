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
     * Complete the 'almostSorted' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void almostSorted(List<Integer> arr) {
        List<Integer> sorted = new ArrayList<Integer>(arr);
        Collections.sort(sorted);
        if (sorted.equals(arr)){
            System.out.print("yes");
            return;
        }
        
        int index1 = 0, index2 = arr.size() - 1;
        while (index1 < arr.size() - 1) {
            if (arr.get(index1) > arr.get(index1 + 1))
                break;
            index1++;
        }
        while (index2 > 0) {
            if (arr.get(index2) < arr.get(index2 - 1))
                break;
            index2--;
        }

        // swap
        int temp = arr.get(index1);
        arr.set(index1, arr.get(index2));
        arr.set(index2, temp);
        
        if (sorted.equals(arr)){
            System.out.print("yes\nswap " + (index1 + 1) + " " + (index2 + 1));
            return;
        }
        
        // swap back
        temp = arr.get(index1);
        arr.set(index1, arr.get(index2));
        arr.set(index2, temp);
        
        // reverse
        int idx1 = index1, idx2 = index2;
        while (idx1 < idx2) {
            temp = arr.get(idx1);
            arr.set(idx1, arr.get(idx2));
            arr.set(idx2, temp);
            idx1++;
            idx2--;
        }
        
        if (sorted.equals(arr)){
            System.out.print("yes\nreverse " + (index1 + 1) + " " + (index2 + 1));
            return;
        }
        
        System.out.print("no");

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        Result.almostSorted(arr);

        bufferedReader.close();
    }
}
