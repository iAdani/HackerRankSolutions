import java.io.*;
import java.util.*;

public class Solution {
    private static void printArr(List<Integer> arr) {
        for (Integer n : arr)
            System.out.print(n + " ");
        System.out.print("\n");
    }
    
    private static void insertionSort1(int n, List<Integer> arr) {
        if (arr.size() == 1)
            return;
            
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
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> second;
        List<Integer> secondInt = new ArrayList<>();
        try {
            int n = Integer.parseInt(br.readLine());
            second = Arrays.asList(br.readLine().split(" "));
            for (String s : second)
                secondInt.add(Integer.parseInt(s));
            insertionSort1(n, secondInt);
            printArr(secondInt);
        } catch(IOException e) {
            System.out.println(e);
        }
    }
}