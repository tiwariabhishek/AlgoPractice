package Algorithms.src.codechef.COOKOFF.DIV2.APR20;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class KPERM {

    private static final long INF = (long) 1e18;

    private static final int MAXN = 500 + 5;
    private static final int MAXR = (int) 2e5 + 5;
    private static final int MOD = (int) 1e9 + 7;
    private static long dp[][] = new long[MAXN][MAXR];

    static {
        dp[1][0] = 1;
        for(int i = 2; i < MAXN; i++) {
            int maxi = i*(i-1) >> 1;
            for(int j = 0; j <= maxi; j++) {
                long sum = 0;
                for(int k = 0; k < i && j-k>=0; k++) {
                    if(sum > INF || dp[i-1][j-k] == -1) {sum = -1; break;}
                    sum += dp[i-1][j-k];
                }
                dp[i][j] = sum > INF ? -1: sum;
            }
        }
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
                int r = Integer.parseInt(str[1]);
                long k = Long.parseLong(str[2]);

                StringBuilder sb = new StringBuilder();

                if(dp[n][r] > -1 && dp[n][r] < k) sb.append(-1);
                else {
                    List<Integer> ans = new ArrayList<>();
                    for (int i = n; i > 0; i--) {
                        if (i == 1) ans.add(r);
                        else {
                            for (int j = 0; j < i; j++) {
                                if (dp[i - 1][r] == -1 || dp[i - 1][r] >= k) {
                                    ans.add(j);
                                    break;
                                } else k -= dp[i - 1][r--];
                            }
                        }
                    }

                    boolean[] taken = new boolean[n];

                    for (int i = 0; i < n; i++) {
                        int ctr = 0;
                        for (int j = 0; j < n; j++) {
                            if (!taken[j]) ctr++;
                            if (ctr > ans.get(i)) {
                                taken[j] = true;
                                sb.append(j + 1).append(" ");
                                break;
                            }
                        }
                    }
                }

                bufferedOutputStream.write(sb.toString().getBytes());
                bufferedOutputStream.write(eolb);
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
