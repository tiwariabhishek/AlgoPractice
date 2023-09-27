import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) {
    FastScanner fs = new FastScanner();
    PrintWriter pr = new PrintWriter(System.out);
    int T = fs.nextInt();

    for (int tt = 1; tt <= T; tt++) {
      int n = fs.nextInt();
      int[] a = fs.readArray(n);
      pr.println();
    }

    pr.flush();
    pr.close();
  }

  private static boolean allNeg(int[] a) {
    for (int i : a) {
      if (i > 0) {
        return false;
      }
    }
    return true;
  }

  static void sort(int[] a) {
    ArrayList<Integer> l = new ArrayList<>();
    for (int i : a) {
      l.add(i);
    }
    Collections.sort(l);
    for (int i = 0; i < a.length; i++) {
      a[i] = l.get(i);
    }
  }

  static long[] getPS(int[] a) {
    long[] ps = new long[a.length];
    long sum = 0;
    for (int i = 0; i < a.length; i++) {
      ps[i] = sum + a[i];
      sum = ps[i];
    }
    return ps;
  }


  static class FastScanner {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer("");

    String next() {
      while (!st.hasMoreTokens()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    int[] readArray(int n) {
      int[] a = new int[n];
      for (int i = 0; i < n; i++) {
        a[i] = nextInt();
      }
      return a;
    }

    long[] readLongArray(int n) {
      long[] a = new long[n];
      for (int i = 0; i < n; i++) {
        a[i] = nextLong();
      }
      return a;
    }

    long nextLong() {
      return Long.parseLong(next());
    }

    List<Long> readLongList(int n) {
      List<Long> list = new ArrayList<>();
      while (n-- > 0) {
        list.add(nextLong());
      }
      return list;
    }
  }
}
