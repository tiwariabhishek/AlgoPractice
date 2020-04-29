package Algorithms.src.codechef.LTIME.DIV1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.BitSet;
import java.util.StringTokenizer;

public class HXR {

    int B = 60;

    void pre() throws Exception {
    }

    void solve(int TC) throws Exception {
        int N = ni();
        long K = nl() - 1;
        long[] a = new long[N];
        for (int i = 0; i < N; i++) a[i] = nl();
        BitSet[] matrix = new BitSet[N];
        for (int i = 0; i < N; i++) matrix[i] = new BitSet(N);

        for (int i = 0; i < N; i++) {
            int l = ni() - 1, r = ni() - 1;
            for (int j = l; j <= r; j++) matrix[i].set(j);
        }
        matrix = pow(N, matrix, K);
        long[] ans = new long[N];
        for (int valueBit = 0; valueBit < B; valueBit++) {
            BitSet vec = new BitSet(N);
            for (int i = 0; i < N; i++) vec.set(i, (a[i] & (1L << valueBit)) > 0);
            vec = mul(N, matrix, vec);
            for (int i = 0; i < N; i++) if (vec.get(i)) ans[i] |= 1L << valueBit;
        }
        for (int i = 0; i < N; i++) p(ans[i] + " ");
        pn("");
    }

    BitSet[] pow(int N, BitSet[] matrix, long K) {
        BitSet[] ans = new BitSet[N];
        for (int i = 0; i < N; i++) {
            ans[i] = new BitSet(N);
            ans[i].set(i);
        }
        for (; K > 0; K >>= 1) {
            if ((K & 1) == 1) ans = mul(N, ans, matrix);
            matrix = mul(N, matrix, matrix);
        }
        return ans;
    }

    BitSet mul(int N, BitSet[] matrix, BitSet column) {
        BitSet res = new BitSet(N);
        for (int i = 0; i < N; i++) {
            BitSet row = matrix[i].get(0, N);
            row.and(column);
            if (row.cardinality() % 2 == 1) res.set(i);
        }
        return res;
    }

    BitSet getColumn(int N, BitSet[] matrix, int idx) {
        BitSet col = new BitSet(N);
        for (int i = 0; i < N; i++) col.set(i, matrix[i].get(idx));
        return col;
    }

    BitSet[] mul(int N, BitSet[] A, BitSet[] B) {
        BitSet[] C = new BitSet[N];
        for (int i = 0; i < N; i++) C[i] = new BitSet(N);
        for (int i = 0; i < N; i++) {
            BitSet col = mul(N, A, getColumn(N, B, i));
            for (int j = 0; j < N; j++) C[j].set(i, col.get(j));
        }
        return C;
    }

    DecimalFormat df = new DecimalFormat("0.00000000000");
    static boolean multipleTC = true;
    FastReader in;
    PrintWriter out;

    void run() throws Exception {
        in = new FastReader();
        out = new PrintWriter(System.out);
        int T = (multipleTC) ? ni() : 1;
        pre();
        for (int t = 1; t <= T; t++) solve(t);
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws Exception {
        new HXR().run();
    }

    int bit(long n) {
        return (n == 0) ? 0 : (1 + bit(n & (n - 1)));
    }

    void p(Object o) {
        out.print(o);
    }

    void pn(Object o) {
        out.println(o);
    }

    void pni(Object o) {
        out.println(o);
        out.flush();
    }

    String n() throws Exception {
        return in.next();
    }

    String nln() throws Exception {
        return in.nextLine();
    }

    int ni() throws Exception {
        return Integer.parseInt(in.next());
    }

    long nl() throws Exception {
        return Long.parseLong(in.next());
    }

    double nd() throws Exception {
        return Double.parseDouble(in.next());
    }

    class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws Exception {
            br = new BufferedReader(new FileReader(s));
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

        String nextLine() throws Exception {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                throw new Exception(e.toString());
            }
            return str;
        }
    }
}
