package Algorithms.src.codechef.DIV2.MAR20;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ENGXOR {

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
                int q = Integer.parseInt(str[1]);
                str = bufferedReader.readLine().split(" ");
                int o = 0;
                for(int i=0;i<n;i++) o += Integer.bitCount(Integer.parseInt(str[i])) % 2;
                int e = n - o;
                while (q-->0) {
                    str = bufferedReader.readLine().split(" ");
                    int p = Integer.parseInt(str[0]);
                    int tmp_o = o;
                    int tmp_e = e;
                    if(Integer.bitCount(p) % 2 > 0) {
                        int tmp = tmp_e;
                        tmp_e = tmp_o;
                        tmp_o = tmp;
                    }
                    bufferedOutputStream.write(Integer.toString(tmp_e).getBytes());
                    bufferedOutputStream.write(" ".getBytes());
                    bufferedOutputStream.write(Integer.toString(tmp_o).getBytes());
                    bufferedOutputStream.write(eolb);
                }
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
