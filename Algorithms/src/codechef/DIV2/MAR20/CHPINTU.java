package Algorithms.src.codechef.DIV2.MAR20;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CHPINTU {

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
                str = bufferedReader.readLine().split(" ");
                String str2[] = bufferedReader.readLine().split(" ");
                Map<Integer, Integer> fp = new HashMap<>();
                for(int i=0;i<n;i++) {
                    int f = Integer.parseInt(str[i]) - 1;
                    int p = Integer.parseInt(str2[i]);
                    if(!fp.containsKey(f)) {
                        fp.put(f, 0);
                    }
                    p += fp.get(f);
                    fp.put(f, p);
                }
                int min = Integer.MAX_VALUE;
                for(int i=0;i<m;i++) if(fp.containsKey(i)) min = Math.min(min, fp.get(i));
                bufferedOutputStream.write(Integer.toString(min).getBytes());
                bufferedOutputStream.write(eolb);
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
