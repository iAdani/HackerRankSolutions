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
     * Complete the 'minimumNumber' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. STRING password
     */
     
    private static String numbers = "0123456789";
    private static String lower_case = "abcdefghijklmnopqrstuvwxyz";
    private static String upper_case = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String special_characters = "!@#$%^&*()-+";

    public static int minimumNumber(int n, String password) {
        if (n < 3)
            return 6 - n;
        
        boolean[] constraints = new boolean[4];
        
        for (Character c : password.toCharArray()) {
            if (numbers.indexOf(c) > -1) 
                constraints[0] = true;
            else if (lower_case.indexOf(c) > -1)
                constraints[1] = true;
            else if (upper_case.indexOf(c) > -1)
                constraints[2] = true;
            else if (special_characters.indexOf(c) > -1)
                constraints[3] = true;
        }
        
        int count = 0;
        for (int i = 0; i < 4; i++)
            if (!constraints[i])
                count++;
    
        return Math.max(count, (6 - n));
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String password = bufferedReader.readLine();

        int answer = Result.minimumNumber(n, password);

        bufferedWriter.write(String.valueOf(answer));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
