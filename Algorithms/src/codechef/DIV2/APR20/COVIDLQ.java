package Algorithms.src.codechef.DIV2.APR20;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class COVIDLQ {

    public static void main(String args[]) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(System.out);
            String eol = System.getProperty("line.separator");
            byte[] eolb = eol.getBytes();
            String str[] = bufferedReader.readLine().split(" ");
            int t = Integer.parseInt(str[0]);
            int dist = 6;
            while (t-- > 0) {
                str = bufferedReader.readLine().split(" ");
                int n = Integer.parseInt(str[0]);
                str = bufferedReader.readLine().split(" ");
                int[] a = new int[n];
                for(int i=0;i<n;i++) a[i] = Integer.parseInt(str[i]);
                String ans = "YES";
                int last_i = -1;
                for(int i=0; i<n; i++) {
                    if(a[i] > 0) {
                        if(last_i == -1 || i - last_i >= dist) last_i = i;
                        else {
                            ans = "NO"; break;
                        }
                    }
                }

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
