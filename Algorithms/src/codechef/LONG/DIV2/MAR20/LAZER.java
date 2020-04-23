package Algorithms.src.codechef.LONG.DIV2.MAR20;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Point {
    int type, i, y;

    public Point(int type, int i, int y) {
        this.type = type;
        this.i = i;
        this.y = y;
    }
}

public class LAZER {

    static void upd(int[] tree, int n, int i, int v) {
        for(++i; i<n; i += i&-i) tree[i] += v;
    }

    static long qry(int[] tree, int n, int i) {
        long sum = 0;
        for(; i>0; i -= i&-i) sum += tree[i];
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
            int[] tree = new int[(int)1e5];
            List<Point> points;
            while (t-- > 0) {
                points = new ArrayList<>();
                str = bufferedReader.readLine().split(" ");
                int n = Integer.parseInt(str[0]);
                int q = Integer.parseInt(str[1]);
                str = bufferedReader.readLine().split(" ");
                int[] a = new int[n];
                int[] ql = new int[q];
                int[] qr = new int[q];
                long[] ans = new long[q];
                for(int i=0;i<n;i++) {
                    a[i] = Integer.parseInt(str[i]);
                    if(i>0) {
                        int min = Math.min(a[i], a[i-1]);
                        int max = Math.max(a[i], a[i-1]);
                        points.add(new Point(1, i-1, min));
                        points.add(new Point(3, i-1, max));
                    }
                }
                for(int i=0;i<q;i++) {
                    str = bufferedReader.readLine().split(" ");
                    ql[i] = Integer.parseInt(str[0]) - 1;
                    qr[i] = Integer.parseInt(str[1]) - 1;
                    int y = Integer.parseInt(str[2]);
                    points.add(new Point(2, i, y));
                }

                Collections.sort(points, new Comparator<Point>() {
                    @Override
                    public int compare(Point o1, Point o2) {
                        return o1.y == o2.y ? o1.type - o2.type : o1.y - o2.y;
                    }
                });

                for(Point point: points) {
                    switch (point.type) {
                        case 1:
                            upd(tree, n, point.i, 1);
                            break;
                        case 2:
                            ans[point.i] = qry(tree, n, qr[point.i]) - qry(tree, n, ql[point.i]);
                            break;
                        case 3:
                            upd(tree, n, point.i, -1);
                    }
                }

                for(int i=0; i<q; i++) {
                    bufferedOutputStream.write(Long.toString(ans[i]).getBytes());
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
