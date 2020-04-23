package Algorithms.src.codechef.LONG.DIV2.MAR20;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ADASHOP2 {

    private static boolean valid(int a, int b) {
        return a > 0 && b > 0 && a < 9 && b < 9;
    }

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
                int r = Integer.parseInt(str[0]);
                int c = Integer.parseInt(str[1]);
                boolean rev = false;
                int[][] ans = new int[64][2];
                int iter = 0, incIter = 0;
                int[] incr = {1,2,3};

                ans[iter][0] = (r+c)>>1; ans[iter++][1] = (r+c)>>1;

                for(int i=1;i<9;i++) {
                    int x = i, y = i;
                    ans[iter][0] = y; ans[iter++][1] = x;
                    y += incr[Math.max(0, incIter)]; x -= incr[Math.max(0, incIter)];
                    if(valid(x, y)) {
                        ans[iter][0] = y; ans[iter++][1] = x;
                        ans[iter][0] = x; ans[iter++][1] = y;
                        ans[iter][0] = ans[iter-3][0]; ans[iter][1] = ans[iter-3][1];;
                        incIter = rev ? incIter - 1 : incIter + 1;
                        iter++;
                    }
                    if(incIter >= incr.length) {rev = true; incIter--;}

                }

                bufferedOutputStream.write(Integer.toString(iter).getBytes());
                bufferedOutputStream.write(eolb);
                for(int i=0;i<iter;i++) {
                    bufferedOutputStream.write(Integer.toString(ans[i][0]).getBytes());
                    bufferedOutputStream.write(" ".getBytes());
                    bufferedOutputStream.write(Integer.toString(ans[i][1]).getBytes());
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
