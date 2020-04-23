package Algorithms.src.codechef.COOKOFF.DIV2;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MINOPS {

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
                char[] ch1 = str[0].toCharArray();
                str = bufferedReader.readLine().split(" ");
                char[] ch2 = str[0].toCharArray();


                boolean f = ch1[0] == ch2[0];
                List<Integer> gaps = new ArrayList<>(); int gap = 0; int sum = 0;
                for(int i = 0; i < ch1.length; i++) {
                    if(ch1[i] != ch2[i]) {
                        if(gap > 0) {
                            if(f) f = false;
                            else gaps.add(gap);
                            gap = 0;
                        }
                        sum++;
                    } else gap++;
                }
                int k = gaps.size() + 1;
                long ans = 1l* k * sum;
                Collections.sort(gaps);
                for(Integer i: gaps) {
                    k--; sum += i;
                    ans = Math.min(ans, 1l * k * sum);
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
