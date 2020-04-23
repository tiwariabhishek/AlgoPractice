package Algorithms.src.codechef.LONG.DIV2.APR20;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ANSLEAK {

    private static int findOptimalOption(int[] ops, int k) {
        int max = 0, nextMax = 0, maxId = 0, nextMaxId = 0;
        for(int i = 0; i < ops.length; i++) {
            if(max < ops[i]) {
                nextMax = max; nextMaxId = maxId;
                max = ops[i]; maxId = i;
            } else if(nextMax < ops[i]) {
                nextMax = ops[i]; nextMaxId = i;
            }
        }

        double maxPer = ((double) max/k) * 100, nextMaxPer = ((double) nextMax/k) * 100;
        if(maxPer - nextMaxPer < 10) return nextMaxId + 1;
        return maxId + 1;
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
                int n = Integer.parseInt(str[0]);
                int m = Integer.parseInt(str[1]);
                int k = Integer.parseInt(str[2]);

                StringBuilder stringBuilder = new StringBuilder();
                for(int i = 0; i < n; i++) {
                    int[] ops = new int[m];
                    str = bufferedReader.readLine().split(" ");
                    for(int j = 0; j < k; j++) {
                        ops[Integer.parseInt(str[j]) - 1]++;
                    }

                    int opt_ans = findOptimalOption(ops, k);
                    stringBuilder.append(opt_ans); if(i < n - 1) stringBuilder.append(" ");
                }

                bufferedOutputStream.write(stringBuilder.toString().getBytes());
                bufferedOutputStream.write(eolb);
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
