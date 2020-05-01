package Algorithms.src.codechef.LTIME.DIV1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DOTTIME {

    long MOD = 998244353;

    void solve(int TC) throws Exception {
        int n = ni(), m = ni(), q = ni();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = nl();
        long[] window = new long[m];
        long windowSum = 0;
        for (int i = 0; i < n - m; i++) windowSum = add(windowSum, a[i]);
        for (int i = 0; i < m; i++) {
            windowSum = add(windowSum, a[i + n - m]);
            window[i] = windowSum;
            windowSum = add(windowSum, MOD - a[i]);
        }
        SegTree t = new SegTree(window);
        while (q-- > 0) {
            int p = ni() - 1;
            long delta = nl() - a[p];
            a[p] = add(a[p], delta);
            int left = Math.max(0, p - (n - m)), right = Math.min(p, m - 1);
            t.u(left, right, delta);
            pn(t.sum());
        }
    }

    long add(long x, long y) {
        if (x < 0) x += MOD;
        if (y < 0) y += MOD;
        return x + y >= MOD ? (x + y - MOD) : (x + y);
    }

    long mul(long x, long y) {
        if (x < 0) x += MOD;
        if (y < 0) y += MOD;
        return (x * y) % MOD;
    }

    class SegTree {
        int m = 1;
        long[] t, t2, lazy;

        public SegTree(long[] a) {
            while (m < a.length) m <<= 1;
            t = new long[m << 1];
            t2 = new long[m << 1];
            lazy = new long[m << 1];
            for (int i = 0; i < a.length; i++) {
                t[i + m] = a[i];
                t2[i + m] = mul(a[i], a[i]);
            }
            for (int i = m - 1; i > 0; i--) {
                t[i] = add(t[i << 1], t[i << 1 | 1]);
                t2[i] = add(t2[i << 1], t2[i << 1 | 1]);
            }
        }

        void push(int i, int ll, int rr) {
            if (lazy[i] != 0) {
                long sumX = t[i];
                long sumX2 = t2[i];
                long delta = lazy[i], sz = rr - ll + 1;
                t[i] = add(sumX, mul(delta, sz));
                t2[i] = add(sumX2, add(mul(sz, mul(delta, delta)), mul(2, mul(delta, sumX))));
                if (i < m) {
                    lazy[i << 1] = add(lazy[i << 1], lazy[i]);
                    lazy[i << 1 | 1] = add(lazy[i << 1 | 1], lazy[i]);
                }
                lazy[i] = 0;
            }
        }

        void u(int l, int r, long x) {
            u(l, r, x, 0, m - 1, 1);
        }

        void u(int l, int r, long x, int ll, int rr, int i) {
            push(i, ll, rr);
            if (l == ll && r == rr) {
                lazy[i] = add(lazy[i], x);
                push(i, ll, rr);
                return;
            }
            int mid = (ll + rr) / 2;
            if (r <= mid) {
                u(l, r, x, ll, mid, i << 1);
                push(i << 1 | 1, mid + 1, rr);
            } else if (l > mid) {
                push(i << 1, ll, mid);
                u(l, r, x, mid + 1, rr, i << 1 | 1);
            } else {
                u(l, mid, x, ll, mid, i << 1);
                u(mid + 1, r, x, mid + 1, rr, i << 1 | 1);
            }
            t[i] = add(t[i << 1], t[i << 1 | 1]);
            t2[i] = add(t2[i << 1], t2[i << 1 | 1]);
        }

        long sum() {
            return t2[1];
        }
    }

    static boolean multipleTC = true;
    FastReader in;
    PrintWriter out;

    void run() throws Exception {
        in = new FastReader();
        out = new PrintWriter(System.out);
        int T = (multipleTC) ? ni() : 1;
        for (int t = 1; t <= T; t++) solve(t);
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws Exception {
        new DOTTIME().run();
    }

    void pn(Object o) {
        out.println(o);
    }

    int ni() throws Exception {
        return Integer.parseInt(in.next());
    }

    long nl() throws Exception {
        return Long.parseLong(in.next());
    }

    class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws Exception {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new Exception(e.toString());
                }
            }
            return st.nextToken();
        }
    }
}
