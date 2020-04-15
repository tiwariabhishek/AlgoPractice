package Algorithms.src.codechef.DIV2.APR20;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class STRNO {

    private static boolean isDivKTimes(int n, int k) {
        int sq = (int) Math.sqrt(n) + 1;
        for(int i = 2; i <= sq && k > 0; i++)
            while (n%i == 0) {
                n /= i; k--;
            }
        if(n > 1) k--;
        return k <= 0;
    }

    public static void main(String args[]) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(System.out);
            String eol = System.getProperty("line.separator");
            byte[] eolb = eol.getBytes();
            String str[] = bufferedReader.readLine().split(" ");
            int t = Integer.parseInt(str[0]);
            int max_k = 31;
            while (t-- > 0) {
                str = bufferedReader.readLine().split(" ");
                int x = Integer.parseInt(str[0]);
                int k = Integer.parseInt(str[1]);

                int min_x = (int) Math.pow(2, Math.min(max_k, k));
                String ans = "0";
                if(x >= min_x && (k == 1 || isDivKTimes(x, k))) ans = "1";

                bufferedOutputStream.write(ans.getBytes());
                bufferedOutputStream.write(eolb);
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
