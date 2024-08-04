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
     * Complete the 'timeInWords' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER h
     *  2. INTEGER m
     */
     
     final static String[] hours = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve"};
     final static String[] minutes = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty", "twenty one", "twenty two", "twenty three", "twenty four", "twenty five", "twenty six", "twenty seven", "twenty eight", "twenty nine"};

    public static String timeInWords(int h, int m) {
        
        // o' clock
        if (m == 0)
            return hours[h - 1] + " o' clock";
        
        // past
        if (m == 1)
            return "one minute past " + hours[h - 1];
        
        if (m == 15)
            return "quarter past " + hours[h - 1];
        
        if (m < 30)
            return minutes[m - 1] + " minutes past " + hours[h - 1];   
                 
        if (m == 30)
            return "half past " + hours[h - 1];
        
        // to
        if (m == 45)
            return "quarter to " + hours[h];
        
        if (m == 59)
            return "one minute to " + hours[h];
        
        return minutes[(60 - m) - 1] + " minutes to " + hours[h];
        
        

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int h = Integer.parseInt(bufferedReader.readLine().trim());

        int m = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Result.timeInWords(h, m);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
