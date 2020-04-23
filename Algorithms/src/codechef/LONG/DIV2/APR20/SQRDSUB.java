package Algorithms.src.codechef.LONG.DIV2.APR20;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SQRDSUB {

    public static void main(String args[]) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(System.out);
            String eol = System.getProperty("line.separator");
            byte[] eolb = eol.getBytes();
            String str[] = bufferedReader.readLine().split(" ");
            int t = Integer.parseInt(str[0]);
            while (t-- > 0) {
                str = bufferedReader.readLine().split(" ");
                int n = Integer.parseInt(str[0]);

                int[] a = new int[n];
                str = bufferedReader.readLine().split(" ");
                for(int i = 0; i < n; i++) a[i] = Integer.parseInt(str[i]);

                int last_even_index = -1, last_odd_index = -1, last_div4_index = -1;
                int[] dp = new int[n]; long sum = 0l;
                for(int i = 0; i < n; i++) {
                    if(a[i] % 2 == 0) {
                        last_odd_index = -1;
                        if(a[i] % 4 == 0) {
                            dp[i] = i + 1;
                            last_div4_index = i;
                        } else {
                            if(last_even_index == -1) {
                                last_even_index = i;
                                if(last_div4_index >= 0) dp[i] = dp[last_div4_index];
                            }
                            else {
                                if(last_even_index > last_div4_index) dp[i] = last_even_index + 1;
                                else dp[i] = dp[last_div4_index];
                                last_even_index = i;
                            }
                        }
                    } else {
                        if(last_odd_index == -1) {
                            dp[i] = 1;
                            last_odd_index = i;
                        } else dp[i] = i - last_odd_index + 1;
                        int last_even = Math.max(last_even_index, last_div4_index);
                        if(last_even > -1) dp[i] += dp[last_even];
                    }
                    sum += dp[i];
                }

                bufferedOutputStream.write(Long.toString(sum).getBytes());
                bufferedOutputStream.write(eolb);
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
