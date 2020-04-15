package Algorithms.src.codechef.DIV2.APR20;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

//public class FCTRE {
//
//    private  final long mod = (long) 1e9 + 7;
//    private  final int root = 0, N = (int) 1e5 + 100, LN = 18;
//    private  int chainNumber = -1, pointer = -1;
//    private  List[] adj = new ArrayList[N];
//    private  int[] costs = new int[N];
//    private  Map[] baseArray = new Map[N];
//    private  int[] chainInd = new int[N];
//    private  int[] chainHead = new int[N];
//    private  int[] posInBase = new int[N];
//    private  int[] depth = new int[N];
//    private  int[] subSize = new int[N];
//    private  Map[] segTree = new Map[N + 10];
//    private  int[][] parent = new int[LN][N];
//
//    private  final Map<Integer, Map<Integer, Integer>> globalHashMap = new HashMap<>();
//
//    private  void cleanup(int n) {
//        for(int i = 0; i < n; i++) {
//            chainHead[i] = -1; costs[i] = 0;
//            adj[i] = new ArrayList();
//            for(int j = 0; j < LN; j++) parent[j][i] = -1;
//        }
//    }
//
//    private  Map<Integer, Integer> mergeMaps(Map<Integer, Integer> m1, Map<Integer, Integer> m2) {
//        if(m1 != null && m2 != null) {
//            Map<Integer, Integer> result = new HashMap<>();
//            for (Map.Entry<Integer, Integer> entry : m1.entrySet()) {
//                int val = entry.getValue();
//                if (m2.containsKey(entry.getKey())) val += m2.get(entry.getKey());
//                result.put(entry.getKey(), val);
//            }
//
//            for (Map.Entry<Integer, Integer> entry : m2.entrySet()) {
//                if (!result.containsKey(entry.getKey())) result.put(entry.getKey(), entry.getValue());
//            }
//            return result;
//        } else if(m1 == null) return m2;
//        else return m1;
//    }
//
//    private  void constructTree(int currentNode, int start, int end) {
//        if(start == end - 1) {
//            segTree[currentNode] = baseArray[start];
//            return;
//        }
//        int left = currentNode << 1, right = left | 1, middle = (start + end) >> 1;
//        constructTree(left, start, middle);
//        constructTree(right, middle, end);
//        segTree[currentNode] = mergeMaps(segTree[left], segTree[right]);
//    }
//
//    private  Map<Integer, Integer> querySegTree(int currentNode, int start, int end, int segSt, int segEnd) {
//        if(start >= segEnd || end <= segSt) {
//            return null;
//        } else if (start >= segSt && end <= segEnd) {
//            return segTree[currentNode];
//        }
//        int left = currentNode << 1, right = left | 1, middle = (start + end) >> 1;
//        return mergeMaps(querySegTree(left, start, middle, segSt, segEnd),
//                querySegTree(right, middle, end, segSt, segEnd));
//    }
//
//    private  Map<Integer, Integer> factorize(int value) {
//
//        if(globalHashMap.containsKey(value)) return globalHashMap.get(value);
//
//        int tmp_val = value;
//        Map<Integer, Integer> factMap = new HashMap<>();
//        int sqrt = (int) Math.sqrt(tmp_val) + 1;
//
//        int exp2 = 0;
//        while(tmp_val % 2 == 0) {
//            exp2++; tmp_val >>= 1;
//        }
//        if(exp2 > 0) factMap.put(2, exp2);
//
//        for(int i = 3; i <= sqrt; i+=2) {
//            int exp = 0;
//            while (tmp_val % i == 0) {
//                exp++; tmp_val /= i;
//            }
//            if(exp > 0) factMap.put(i, exp);
//        }
//
//        if(tmp_val > 1) factMap.put(tmp_val, 1);
//
//        globalHashMap.put(value, factMap);
//
//        return factMap;
//    }
//
//    private  void HLD(int currentNode, int prevNode) {
//        if(chainHead[chainNumber] == -1) chainHead[chainNumber] = currentNode;
//        chainInd[currentNode] = chainNumber;
//        posInBase[currentNode] = pointer;
//        baseArray[pointer++] = factorize(costs[currentNode]);
//
//        int specialChild = -1;
//        for(int i = 0; i < adj[currentNode].size(); i++) {
//            int currentNeighbour = (int) adj[currentNode].get(i);
//            if (currentNeighbour != prevNode)
//                if (specialChild == -1 || subSize[specialChild] < subSize[currentNeighbour])
//                    specialChild = currentNeighbour;
//        }
//
//        if(specialChild != -1) HLD(specialChild, currentNode);
//
//        for(int i = 0; i < adj[currentNode].size(); i++) {
//            int currentNeighbour = (int) adj[currentNode].get(i);
//            if (currentNeighbour != prevNode && currentNeighbour != specialChild) {
//                chainNumber++; HLD(currentNeighbour, currentNode);
//            }
//        }
//    }
//
//    private  void dfs(int currentNode, int prevNode, int d) {
//        parent[0][currentNode] = prevNode;
//        depth[currentNode] = d;
//        subSize[currentNode] = 1;
//        for(int i = 0; i < adj[currentNode].size(); i++) {
//            if((int) adj[currentNode].get(i) != prevNode) {
//                dfs((int) adj[currentNode].get(i), currentNode, d + 1);
//                subSize[currentNode] += subSize[(int) adj[currentNode].get(i)];
//            }
//        }
//    }
//
//    private  Map<Integer, Integer> queryUp(int u, int v) {
//        if(u == v) return null;
//
//        Map<Integer, Integer> result = null;
//        int uchain, vchain = chainInd[v];
//        while (true) {
//            uchain = chainInd[u];
//            if(uchain == vchain) {
//                if(u == v) break;
//                result = mergeMaps(result,
//                        querySegTree(1, 0, pointer, posInBase[v] + 1, posInBase[u] + 1));
//                break;
//            }
//            result = mergeMaps(result,
//                    querySegTree(1, 0, pointer, posInBase[chainHead[uchain]], posInBase[u] + 1));
//            u = chainHead[uchain];
//            u = parent[0][u];
//        }
//        return result;
//    }
//
//    private  int LCA(int u, int v) {
//        if(depth[u] < depth[v]) {
//            int temp = u; u = v; v = temp;
//        }
//        int difference = depth[u] - depth[v];
//        for(int i = 0; i < LN; i++) if(((difference >> i) & 1) > 0) u = parent[i][u];
//        if(u == v) return u;
//        for(int i = LN - 1; i >= 0; i--)
//            if(parent[i][u] != parent[i][v]) {
//                u = parent[i][u];
//                v = parent[i][v];
//            }
//        return parent[0][u];
//    }
//
//    private  long computeResult(Map<Integer, Integer> factMap) {
//        long factors = 1l;
//        for(Map.Entry<Integer, Integer> entry: factMap.entrySet()) {
//            factors *= (entry.getValue() + 1);
//            factors %= mod;
//        }
//        return factors;
//    }
//
//    private  long query(int u, int v) {
//        if(u == v) return computeResult(globalHashMap.get(costs[u]));
//
//        int lca = LCA(u, v);
//        Map<Integer, Integer> factMap1 = queryUp(u, lca);
//        Map<Integer, Integer> factMap2 = queryUp(v, lca);
//        return computeResult(mergeMaps(factMap1, mergeMaps(factMap2, globalHashMap.get(costs[lca]))));
//    }
//
//    public  void main(String args[]) {
//        try {
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(System.out);
//            String eol = System.getProperty("line.separator");
//            byte[] eolb = eol.getBytes();
//            String str[] = bufferedReader.readLine().split(" ");
//            int t = Integer.parseInt(str[0]);
//            while (t-- > 0) {
//                str = bufferedReader.readLine().split(" ");
//                int n = Integer.parseInt(str[0]);
//
//                cleanup(n);
//
//                for(int i = 1; i < n; i++) {
//                    str = bufferedReader.readLine().split(" ");
//                    int u = Integer.parseInt(str[0]) - 1;
//                    int v = Integer.parseInt(str[1]) - 1;
//                    adj[u].add(v); adj[v].add(u);
//                }
//
//                str = bufferedReader.readLine().split(" ");
//                for(int i = 0; i < n; i++) costs[i] = Integer.parseInt(str[i]);
//
//                chainNumber = pointer = 0;
//                dfs(root, -1, 0);
//                HLD(root, -1);
//                constructTree(1, 0, pointer);
//
//                int ln = (int) (Math.log(n) / Math.log(2)) + 1;
//                for(int i = 1; i < ln; i++)
//                    for(int j = 0; j < n; j++)
//                        if(parent[i-1][j] > -1) { parent[i][j] = parent[i-1][parent[i-1][j]]; }
//
//                str = bufferedReader.readLine().split(" ");
//                int q = Integer.parseInt(str[0]);
//                while (q-- > 0) {
//                    str = bufferedReader.readLine().split(" ");
//                    int u = Integer.parseInt(str[0]) - 1;
//                    int v = Integer.parseInt(str[1]) - 1;
//                    bufferedOutputStream.write(Long.toString(query(u, v)).getBytes());
//                    bufferedOutputStream.write(eolb);
//                }
//            }
//            bufferedOutputStream.flush();
//            bufferedOutputStream.close();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//    }
//}

public class FCTRE {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        QWQE solver = new QWQE();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }
}

class QWQE {

    private  final long mod = (long) 1e9 + 7;
    private int n, ln;
    private int chainNumber = -1, pointer = -1;
    private List[] adj;
    private int[] costs;
    private  Map[] baseArray;
    private  int[] chainInd;
    private  int[] chainHead;
    private  int[] posInBase;
    private  int[] depth;
    private  int[] subSize;
    private  Map[] segTree;
    private  int[][] parent;

    private final Map<Integer, Map<Integer, Integer>> globalHashMap = new HashMap<>();

    private  void cleanup(int n) {
        for(int i = 0; i < n; i++) {
            chainHead[i] = -1; adj[i] = new ArrayList();
            for(int j = 0; j < ln; j++) parent[j][i] = -1;
        }
    }

    private  Map<Integer, Integer> mergeMaps(Map<Integer, Integer> m1, Map<Integer, Integer> m2) {
        if(m1 != null && m2 != null) {
            Map<Integer, Integer> result = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry : m1.entrySet()) {
                int val = entry.getValue();
                if (m2.containsKey(entry.getKey())) val += m2.get(entry.getKey());
                result.put(entry.getKey(), val);
            }

            for (Map.Entry<Integer, Integer> entry : m2.entrySet()) {
                if (!result.containsKey(entry.getKey())) result.put(entry.getKey(), entry.getValue());
            }
            return result;
        } else if(m1 == null) return m2;
        else return m1;
    }

    private  void constructTree(int currentNode, int start, int end) {
        if(start == end - 1) {
            segTree[currentNode] = baseArray[start];
            return;
        }
        int left = currentNode << 1, right = left | 1, middle = (start + end) >> 1;
        constructTree(left, start, middle);
        constructTree(right, middle, end);
        segTree[currentNode] = mergeMaps(segTree[left], segTree[right]);
    }

    private  Map<Integer, Integer> querySegTree(int currentNode, int start, int end, int segSt, int segEnd) {
        if(start >= segEnd || end <= segSt) {
            return null;
        } else if (start >= segSt && end <= segEnd) {
            return segTree[currentNode];
        }
        int left = currentNode << 1, right = left | 1, middle = (start + end) >> 1;
        return mergeMaps(querySegTree(left, start, middle, segSt, segEnd),
                querySegTree(right, middle, end, segSt, segEnd));
    }

    private  Map<Integer, Integer> factorize(int value) {

        if(globalHashMap.containsKey(value)) return globalHashMap.get(value);

        int tmp_val = value;
        Map<Integer, Integer> factMap = new HashMap<>();
        int sqrt = (int) Math.sqrt(tmp_val) + 1;

        int exp2 = 0;
        while(tmp_val % 2 == 0) {
            exp2++; tmp_val >>= 1;
        }
        if(exp2 > 0) factMap.put(2, exp2);

        for(int i = 3; i <= sqrt; i+=2) {
            int exp = 0;
            while (tmp_val % i == 0) {
                exp++; tmp_val /= i;
            }
            if(exp > 0) factMap.put(i, exp);
        }

        if(tmp_val > 1) factMap.put(tmp_val, 1);

        globalHashMap.put(value, factMap);

        return factMap;
    }

    private  void HLD(int currentNode, int prevNode) {
        if(chainHead[chainNumber] == -1) chainHead[chainNumber] = currentNode;
        chainInd[currentNode] = chainNumber;
        posInBase[currentNode] = pointer;
        baseArray[pointer++] = factorize(costs[currentNode]);

        int specialChild = -1;
        for(int i = 0; i < adj[currentNode].size(); i++) {
            int currentNeighbour = (int) adj[currentNode].get(i);
            if (currentNeighbour != prevNode)
                if (specialChild == -1 || subSize[specialChild] < subSize[currentNeighbour])
                    specialChild = currentNeighbour;
        }

        if(specialChild != -1) HLD(specialChild, currentNode);

        for(int i = 0; i < adj[currentNode].size(); i++) {
            int currentNeighbour = (int) adj[currentNode].get(i);
            if (currentNeighbour != prevNode && currentNeighbour != specialChild) {
                chainNumber++; HLD(currentNeighbour, currentNode);
            }
        }
    }

    private  void dfs(int currentNode, int prevNode, int d) {
        parent[0][currentNode] = prevNode;
        depth[currentNode] = d;
        subSize[currentNode] = 1;
        for(int i = 0; i < adj[currentNode].size(); i++) {
            if((int) adj[currentNode].get(i) != prevNode) {
                dfs((int) adj[currentNode].get(i), currentNode, d + 1);
                subSize[currentNode] += subSize[(int) adj[currentNode].get(i)];
            }
        }
    }

    private  Map<Integer, Integer> queryUp(int u, int v) {
        if(u == v) return null;

        Map<Integer, Integer> result = null;
        int uchain, vchain = chainInd[v];
        while (true) {
            uchain = chainInd[u];
            if(uchain == vchain) {
                if(u == v) break;
                result = mergeMaps(result,
                        querySegTree(1, 0, pointer, posInBase[v] + 1, posInBase[u] + 1));
                break;
            }
            result = mergeMaps(result,
                    querySegTree(1, 0, pointer, posInBase[chainHead[uchain]], posInBase[u] + 1));
            u = chainHead[uchain];
            u = parent[0][u];
        }
        return result;
    }

    private int LCA(int u, int v) {
        if(depth[u] < depth[v]) {
            int temp = u; u = v; v = temp;
        }
        int difference = depth[u] - depth[v];
        for(int i = 0; i < ln; i++) if(((difference >> i) & 1) > 0) u = parent[i][u];
        if(u == v) return u;
        for(int i = ln - 1; i >= 0; i--)
            if(parent[i][u] != parent[i][v]) {
                u = parent[i][u];
                v = parent[i][v];
            }
        return parent[0][u];
    }

    private  long computeResult(Map<Integer, Integer> factMap) {
        long factors = 1l;
        for(Map.Entry<Integer, Integer> entry: factMap.entrySet()) {
            factors *= (entry.getValue() + 1);
            factors %= mod;
        }
        return factors;
    }

    private  long query(int u, int v) {
        if(u == v) return computeResult(globalHashMap.get(costs[u]));

        int lca = LCA(u, v);
        Map<Integer, Integer> factMap1 = queryUp(u, lca);
        Map<Integer, Integer> factMap2 = queryUp(v, lca);
        return computeResult(mergeMaps(factMap1, mergeMaps(factMap2, globalHashMap.get(costs[lca]))));
    }
    
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.nextInt();
        adj = new ArrayList[n];
        costs = new int[n];
        baseArray = new Map[n+100];
        chainInd = new int[n+100];
        chainHead = new int[n+100];
        posInBase = new int[n+100];
        depth = new int[n+100];
        subSize = new int[n+100];
        segTree = new Map[n+200];
        ln = (int)(Math.log(n) /Math.log(2)) + 2;
        parent = new int[ln][n];
        chainNumber = pointer = 0;
        cleanup(n);

        for(int i = 1; i < n; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            adj[u].add(v); adj[v].add(u);
        }

        for(int i = 0; i < n; i++) costs[i] = in.nextInt();

        dfs(0, -1, 0);
        HLD(0, -1);
        constructTree(1, 0, pointer);

        for(int i = 1; i < ln; i++)
            for(int j = 0; j < n; j++)
                if(parent[i-1][j] > -1) { parent[i][j] = parent[i-1][parent[i-1][j]]; }

        int q = in.nextInt();
        while (q-- > 0) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            out.printLine(query(u, v));
        }

    }
}


class InputReader {
    private InputStream stream;
    private byte[] buffer = new byte[10000];
    private int cur;
    private int count;

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    public static boolean isSpace(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public int read() {
        if (count == -1) {
            throw new InputMismatchException();
        }
        try {
            if (cur >= count) {
                cur = 0;
                count = stream.read(buffer);
                if (count <= 0)
                    return -1;
            }
        } catch (IOException e) {
            throw new InputMismatchException();
        }
        return buffer[cur++];
    }

    public int readSkipSpace() {
        int c;
        do {
            c = read();
        } while (isSpace(c));
        return c;
    }

    public String nextToken() {
        int c = readSkipSpace();
        StringBuilder sb = new StringBuilder();
        while (!isSpace(c)) {
            sb.append((char) c);
            c = read();
        }
        return sb.toString();
    }

    public String next() {
        return nextToken();
    }

    public int nextInt() {
        int sgn = 1;
        int c = readSkipSpace();
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9') {
                throw new InputMismatchException();
            }
            res = res * 10 + c - '0';
            c = read();
        } while (!isSpace(c));
        res *= sgn;
        return res;
    }

}

class OutputWriter {
    private final PrintWriter writer;

    public OutputWriter(OutputStream outputStream) {
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
    }

    public void print(Object... objects) {
        for (int i = 0; i < objects.length; i++) {
            if (i != 0) {
                writer.print(' ');
            }
            writer.print(objects[i]);
        }
    }

    public void printLine(Object... objects) {
        print(objects);
        writer.println();
    }

    public void close() {
        writer.close();
    }
}





