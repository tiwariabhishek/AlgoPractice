package Algorithms.src.codechef.LTIME.DIV1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class TRIQRY {

    private static int BIT[];

    public static void update(int x, int val) {
        for (x++; x < 2 * 1000005; x += x & -x)
            BIT[x] += val;
    }

    public static int query(int x) {
        int sum = 0;
        for (x++; x > 0; x -= x & -x)
            sum += BIT[x];
        return sum;
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
                BIT = new int[2 * 1000005];
                str = bufferedReader.readLine().split(" ");
                int n = Integer.parseInt(str[0]);
                int q = Integer.parseInt(str[1]);
                int[][] pts = new int[n][2];

                for(int i = 0; i < n; i++) {
                    str = bufferedReader.readLine().split(" ");
                    pts[i][0] = Integer.parseInt(str[0]);
                    pts[i][1] = Integer.parseInt(str[1]);
                    update(pts[i][0] + pts[i][1], 1);
                }

                int[][] qr = new int[q][3];
                for(int i = 0; i < q; i++) {
                    str = bufferedReader.readLine().split(" ");
                    qr[i][0] = Integer.parseInt(str[0]);
                    qr[i][1] = Integer.parseInt(str[1]);
                    qr[i][2] = i;
                }

                Arrays.sort(pts, Comparator.comparingInt(o -> (o[0] - o[1])));
                Arrays.sort(qr, Comparator.comparingInt(o -> o[0]));

                int[] ans = new int[q];
                int pointer = 0;
                for(int i = 0; i < q; i++) {
                    while(pointer < n && pts[pointer][0] - pts[pointer][1] < qr[i][0]) {
                        update(pts[pointer][0] + pts[pointer][1], -1); pointer++;
                    }
                    ans[qr[i][2]] = query(qr[i][1]);
                }

                for(int i = 0; i < q; i++)
                    bufferedOutputStream.write((ans[i] + " ").getBytes());
                bufferedOutputStream.write(eolb);
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
