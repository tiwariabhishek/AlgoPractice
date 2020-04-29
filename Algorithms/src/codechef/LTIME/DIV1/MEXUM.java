package Algorithms.src.codechef.LTIME.DIV1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class MEXUM {

    private static final long mod =  998_244_353l;

    public static long ModPow(long x, long y, long MOD) {
        long res = 1L;
        x = x % MOD;
        while (y >= 1) {
            if ((y & 1) > 0) res = (res * x) % MOD;
            x = (x * x) % MOD;
            y >>= 1;
        }
        return res;
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
                int[] a = new int[n];
                str = bufferedReader.readLine().split(" ");
                for(int i = 0; i < n; i++) a[i] = Integer.parseInt(str[i]);

                Map<Integer, Integer> ctMap = new HashMap<>();
                for(int i = 0; i < n; i++) {
                    if(!ctMap.containsKey(a[i])) ctMap.put(a[i], 0);
                    int val = ctMap.get(a[i]); ctMap.put(a[i], val + 1);
                }

                int min = 1, minOccCt = ctMap.getOrDefault(min, 0);
                long ans = ModPow(2, n - ctMap.getOrDefault(min, 0), mod);
                long minCombCt = ModPow(2, minOccCt, mod) - 1;
                while(ctMap.containsKey(min++)) {
                    ans = (ans + (min * ((minCombCt *
                            (ModPow(2, ctMap.containsKey(min) ? n - ctMap.get(min) - minOccCt: n - minOccCt, mod)))
                            % mod)) % mod) % mod;
                    minCombCt = (minCombCt * (ModPow(2, ctMap.getOrDefault(min, 0), mod) - 1)) % mod;
                    minOccCt += ctMap.getOrDefault(min, 0);
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
