package Algorithms.src.codechef.LONG.DIV2.APR20;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CARSELL {

    public static void main(String args[]) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(System.out);
            String eol = System.getProperty("line.separator");
            byte[] eolb = eol.getBytes();
            String str[] = bufferedReader.readLine().split(" ");
            int t = Integer.parseInt(str[0]);
            int mod = (int) 1e9 + 7;
            while (t-- > 0) {
                str = bufferedReader.readLine().split(" ");
                int n = Integer.parseInt(str[0]);
                str = bufferedReader.readLine().split(" ");
                int[] a = new int[n];
                for(int i=0;i<n;i++) a[i] = Integer.parseInt(str[i]);
                Arrays.sort(a);

                int dec = 0;
                long ans = 0;
                for(int i = n-1; i>=0; i--) {
                    ans += (Math.max(0, a[i]-dec)); ans %= mod;dec++;
                }

                bufferedOutputStream.write(Long.toString(ans).getBytes());
                bufferedOutputStream.write(eolb);
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
