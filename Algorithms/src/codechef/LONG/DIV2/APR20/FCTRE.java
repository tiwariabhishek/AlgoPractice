package Algorithms.src.codechef.LONG.DIV2.APR20;


// Source1 - https://codeforces.com/blog/entry/43230
// Source2 - https://ideone.com/6NVoPD -> implementation of source1
// Source3 - https://codeforces.com/blog/entry/68271

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;

public class FCTRE implements Runnable {

    private boolean testCases = true;
    private boolean console = false;
    private final long MOD = 1000_000_007L;
    private final int MAX = 100001;
    private final int PRIMEMAX = 1000001;

    private int[][] par;
    private int[] depth;
    private int level = 20;
    private ArrayList<Integer>[] tree;
    private int[] weight;
    private int[] start;
    private int[] end;
    private int[] flat;
    private int time;
    private final int BUCKET = 1001;
    private List<Pair>[] primeCount = new ArrayList[PRIMEMAX];
    private long[] inv = new long[(int)2e6 + 1];
    private boolean[] vis;
    private long ans;
    private int[] arr;
    private int[] primeFreq;
    private int[] primes = new int[PRIMEMAX];

    private void initVal(int n) {
//        BUCKET = (int) Math.sqrt(n*1.0) + 1;
        tree = new ArrayList[n+1];
        weight = new int[n+1];
        start = new int[n+1];
        end = new int[n+1];
        flat = new int[n*2+1];
        vis = new boolean[n+1];
        arr = new int[n+1];
        primeFreq = new int[PRIMEMAX];
        par = new int[200001][20];
        depth = new int[200001];
        time = 0; ans = 1;
        for(int i = 1; i <= n; i++) tree[i] = new ArrayList<>();
    }

    private void lca_dfs(int current, int previous) {
        start[current] = ++time;
        flat[time] = current;
        depth[current] = depth[previous] + 1;
        par[current][0] = previous;

        for(int i = 0; i < tree[current].size(); i++) {
            if(tree[current].get(i) != previous) lca_dfs(tree[current].get(i), current);
        }

        end[current] = ++time;
        flat[time] = current;
    }

    private void buildLCA(int n) {
        for(int i = 1; i < level; i++) {
            for(int node = 1; node <= n; node++) {
                if(par[node][i-1] != -1) par[node][i] = par[par[node][i-1]][i-1];
            }
        }
    }

    private int getLCA(int u, int v) {
        if(depth[v] < depth[u]) { u ^= v; v ^= u; u ^= v;}
        int diff = depth[v] - depth[u];
        for(int i = 0; i < level; i++) if(((diff >> i) & 1) > 0) v = par[v][i];

        if(u == v) return u;

        for(int i = level - 1; i >=0; i--)
            if(par[u][i] != par[v][i]) {
                u = par[u][i]; v = par[v][i];
            }
        return par[u][0];
    }

    private void sort(List<Query> queries, int n) {
        if(n <= 1000) {
            queries.sort((q1, q2) -> {
                if(q1.l/BUCKET == q2.l/BUCKET)
                    return q1.r/BUCKET - q2.r/BUCKET;
                return q1.l/BUCKET - q2.l/BUCKET;
            });
        } else {
            queries.sort((q1, q2) -> {
                if(q1.l/BUCKET == q2.l/BUCKET)
                    return (q1.r - q2.r) ^ ((q1.l/BUCKET) % 2);
                return q1.l/BUCKET - q2.l/BUCKET;
            });
        }
    }

    private void compute(int idx) {
        vis[idx] = !vis[idx];
        if(vis[idx]) {
            primeCount[arr[idx]].forEach(pair -> {
                int num = pair.num;
                ans = (ans * inv[primeFreq[num] + 1]) % MOD;
                primeFreq[num] += pair.expCt;
                ans = (ans * (primeFreq[num] + 1)) % MOD;
            });
        } else {
            primeCount[arr[idx]].forEach(pair -> {
                int num = pair.num;
                ans = (ans * inv[primeFreq[num] + 1]) % MOD;
                primeFreq[num] -= pair.expCt;
                ans = (ans * (primeFreq[num] + 1)) % MOD;
            });
        }
    }

    public void solve() {
        int n = in.ni();

        initVal(n);

        for(int i = 1; i < n; i++) {
            int u = in.ni(), v = in.ni();
            tree[u].add(v); tree[v].add(u);
        }

        for(int i = 1; i <= n; i++) arr[i] = in.ni();

        primeInit();

        lca_dfs(1, 0);
        buildLCA(n);
        int q = in.ni();
        List<Query> queries = new ArrayList<>();
        for(int i = 0; i < q; i++) {
            int u = in.ni(), v = in.ni();
            if(start[v] < start[u]) {
                u ^= v; v ^= u; u ^= v;
            }
            int lca = getLCA(u, v);
            if(u == lca || v == lca) queries.add(new Query(start[u], start[v], i, lca));
            else queries.add(new Query(end[u], start[v], i, lca));
        }

        sort(queries, n);

        long[] ans_q = new long[q];

        int l = queries.get(0).l, r = queries.get(0).l - 1;
        for(Query query: queries) {
            while (l < query.l) compute(flat[l++]);
            while (l > query.l) compute(flat[--l]);
            while (r < query.r) compute(flat[++r]);
            while (r > query.r) compute(flat[r--]);

            if(flat[l] != query.lca && flat[r] != query.lca) compute(query.lca);

            ans_q[query.idx] = ans;

            if(flat[l] != query.lca && flat[r] != query.lca) compute(query.lca);
        }

        for(int i = 0; i < q; i++) out.println(ans_q[i]);
    }

    class Pair {
        int num, expCt;

        public Pair(int num, int expCt) {
            this.num = num; this.expCt = expCt;
        }

        @Override
        public String toString() { return this.num + " " + this.expCt; }
    }

    private void primeInit() {
        primeCount[1]=new ArrayList<>();
        for(int i: arr) {
            if(i==0 || primeCount[i] != null) continue;
            primeCount[i] = new ArrayList<>();
            int num = i;
            // Pair of prime number and exponent count that is a factor of i
            Pair pair = new Pair(-1, 0);
            while (num > 1) {
                int primeFactor = primes[num];
                if(primeFactor != pair.num) {
                    if(pair.num != -1) primeCount[i].add(new Pair(pair.num, pair.expCt));
                    pair.num = primeFactor;
                    pair.expCt = 0;
                }
                pair.expCt++;
                num /= primes[num];
            }
            primeCount[i].add(pair);
        }
    }

    private void initInv() {
        for(int i = 1; i < inv.length; i++) inv[i] = ModPow(i, MOD-2, MOD);
    }

    private void sieve() {
        for(int i = 1; i < PRIMEMAX; i++) primes[i] = i;
        for(int i = 4; i < PRIMEMAX; i+=2) primes[i] = 2;
        for(int i = 3; i*i < PRIMEMAX; i+=2)
            for(int j = i*i; j < PRIMEMAX; j+=i)
                if(primes[j] == j) primes[j] = i;
    }

    class Query {
        int l, r, idx, lca;
        public Query(int l, int r, int idx, int lca) {
            this.l = l; this.r = r; this.idx = idx; this.lca = lca;
        }
        @Override
        public String toString() { return l + " " + r;}
    }

    @Override
    public void run() {
//        long time = System.currentTimeMillis();
        init();
        int t = testCases ? in.ni() : 1;
        initInv(); sieve();
        while(t-- > 0) solve();
        out.flush();
//        System.out.println(System.currentTimeMillis() - time);
    }

    private FastInput in;
    private PrintWriter out;
    public static void main(String[] args) {
        new Thread(null, new FCTRE(), "Main", 1 << 27).start();
    }
    private void init() {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;

        out = new PrintWriter(outputStream);
        in = new FastInput(inputStream);
    }
    private void maualAssert(int a,int b,int c){
        if(a<b || a>c)
            throw new RuntimeException();
    }
    private void maualAssert(long a,long b,long c){
        if(a<b || a>c)
            throw new RuntimeException();
    }
    private void sort(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int object : arr) list.add(object);
        Collections.sort(list);
        for (int i = 0; i < list.size(); ++i) arr[i] = list.get(i);
    }
    private void sort(long[] arr) {
        List<Long> list = new ArrayList<>();
        for (long object : arr) list.add(object);
        Collections.sort(list);
        for (int i = 0; i < list.size(); ++i) arr[i] = list.get(i);
    }
    public long ModPow(long x, long y, long MOD) {
        long res = 1L;
        x = x % MOD;
        while (y >= 1) {
            if ((y & 1) > 0) res = (res * x) % MOD;
            x = (x * x) % MOD;
            y >>= 1;
        }
        return res;
    }
    public int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }
    public long gcd(long a, long b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }
    private int[] intArr(int n){
        int[] arr=new int[n];for(int i=0;i<n;++i)arr[i]=in.ni();
        return arr;
    }
    private long[] longArr(int n){
        long[] arr=new long[n];for(int i=0;i<n;++i)arr[i]=in.nl();
        return arr;
    }
    static class FastInput {
        InputStream obj;
        public FastInput(InputStream obj) {
            this.obj = obj;
        }
        byte inbuffer[] = new byte[1024];
        int lenbuffer = 0, ptrbuffer = 0;
        int readByte() {
            if (lenbuffer == -1) throw new InputMismatchException();
            if (ptrbuffer >= lenbuffer) { ptrbuffer = 0;
                try { lenbuffer = obj.read(inbuffer);
                } catch (IOException e) { throw new InputMismatchException(); } }
            if (lenbuffer <= 0) return -1;return inbuffer[ptrbuffer++]; }
        String ns() { int b = skip();StringBuilder sb = new StringBuilder();
            while (!(isSpaceChar(b))) // when nextLine, (isSpaceChar(b) && b!=' ')
            { sb.appendCodePoint(b);b = readByte(); }return sb.toString();}
        int ni() {
            int num = 0, b;boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
            if (b == '-') { minus = true;b = readByte(); }
            while (true) { if (b >= '0' && b <= '9') { num = num * 10 + (b - '0'); } else {
                return minus ? -num : num; }b = readByte(); }}
        long nl() { long num = 0;int b;boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
            if (b == '-') { minus = true;b = readByte(); }
            while (true) { if (b >= '0' && b <= '9') { num = num * 10L + (b - '0'); } else {
                return minus ? -num : num; }b = readByte(); } }
        boolean isSpaceChar(int c) {
            return (!(c >= 33 && c <= 126));
        }
        int skip() { int b;while ((b = readByte()) != -1 && isSpaceChar(b)) ;return b; }
        float nf() {return Float.parseFloat(ns());}
        double nd() {return Double.parseDouble(ns());}
        char nc() {return (char) skip();}
    }
}
