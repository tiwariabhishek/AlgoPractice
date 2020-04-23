package Algorithms.src.codechef.COOKOFF.DIV2;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class TOWCNT {

    public static boolean isGreater(Node initial, Node toCheck, Node already) {
        return (toCheck.x - initial.x) * (already.y - initial.y) < (already.x - initial.x) * (toCheck.y - initial.y);
    }

    public static boolean isSmaller(Node initial, Node toCheck, Node already) {
        return (toCheck.x - initial.x) * (already.y - initial.y) > (already.x - initial.x) * (toCheck.y - initial.y);
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
                int h = Integer.parseInt(str[0]); int n = Integer.parseInt(str[1]);

                Node[] towers = new Node[n];
                for(int i = 0; i < n; i++) {
                    str = bufferedReader.readLine().split(" ");
                    towers[i] = new Node(i, Integer.parseInt(str[0]),
                            Integer.parseInt(str[1]) ,Integer.parseInt(str[2]));
                }

                Arrays.sort(towers, Comparator.comparingLong(o -> o.x));

                for(int i = 0; i < n; i++) {
                    Node lower = new Node(), higher = new Node();
                    for(int j = i+1; j < n; j++) {
                        if(towers[i].x == towers[j].x) {
                            towers[i].answer++; towers[j].answer++;
                            continue;
                        }
                        boolean flag = true;
                        if(towers[j].t == 0) {
                            if(lower.id == -1) lower = towers[j];
                            else if(isGreater(towers[i], towers[j], lower)) lower = towers[j];
                            else flag = false;
                        } else {
                            if(higher.id == -1) higher = towers[j];
                            else if(isSmaller(towers[i], towers[j], higher)) higher = towers[j];
                            else flag = false;
                        }

                        if (flag && (lower.id == -1 || higher.id == -1 || isSmaller(towers[i], lower, higher))) {
                            towers[i].answer++; towers[j].answer++;
                        }
                    }
                }

                Arrays.sort(towers, Comparator.comparingLong(o -> o.id));

                for(Node node: towers) {
                    bufferedOutputStream.write((node.answer + " ").getBytes());
                }
                bufferedOutputStream.write(eolb);

            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    static class Node {
        long id, t, x, y, answer;
        public Node(int id, int t, int x, int y) {
            this.id = id; this.t = t;
            this.x = x; this.y = y;
            this.answer = 0;
        }
        public Node() {
            this.id = -1;
        }
    }
}
