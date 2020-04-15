package Algorithms.src.codechef.DIV2.APR20;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UNITGCD {

    public static void main(String args[]) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(System.out);
            String eol = System.getProperty("line.separator");
            byte[] eolb = eol.getBytes();
            byte[] space = " ".getBytes();
            String str[] = bufferedReader.readLine().split(" ");
            int t = Integer.parseInt(str[0]);
            while (t-- > 0) {
                str = bufferedReader.readLine().split(" ");
                int n = Integer.parseInt(str[0]);

                if(n < 4) {
                    bufferedOutputStream.write(Integer.toString(1).getBytes());
                    bufferedOutputStream.write(eolb);
                    bufferedOutputStream.write(Integer.toString(n).getBytes());
                    bufferedOutputStream.write(space);
                    for(int i = 0; i < n; i++) {
                        bufferedOutputStream.write(Integer.toString(i+1).getBytes());
                        bufferedOutputStream.write(i == n-1 ? eolb: space);
                    }
                } else {
                    bufferedOutputStream.write(Integer.toString(n/2).getBytes());
                    bufferedOutputStream.write(eolb);
                    bufferedOutputStream.write(Integer.toString(3).getBytes());
                    bufferedOutputStream.write(space);
                    for(int i = 1; i <= 3; i++) {
                        bufferedOutputStream.write(Integer.toString(i).getBytes());
                        bufferedOutputStream.write(i==3 ? eolb : space);
                    }
                    for(int i = 4; i <= n; i+=2) {
                        bufferedOutputStream.write(Integer.toString(i+1 <= n ? 2: 1).getBytes());
                        bufferedOutputStream.write(space);
                        bufferedOutputStream.write(Integer.toString(i).getBytes());
                        if(i+1 <= n) {
                            bufferedOutputStream.write(space);
                            bufferedOutputStream.write(Integer.toString(i + 1).getBytes());
                        }
                        bufferedOutputStream.write(eolb);
                    }
                }
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
