package algorithms.misc;

public class UnionFind {

  int[] parent;
  int[] size;
  int maxSize;

  public UnionFind(int n) {
    parent = new int[n];
    size = new int[n];
    maxSize = 1;
    for (int i = 0; i < n; i++) {
      parent[i] = i;
      size[i] = 1;
    }
  }

  public int find(int x) {
    // Finds the root of x
    if (x != parent[x]) {
      parent[x] = find(parent[x]);
    }
    return parent[x];
  }

  public boolean union(int x, int y) {
    // Connects x and y
    int rootX = find(x);
    int rootY = find(y);
    if (rootX == rootY) {
      return false;
    }
    if (size[rootX] < size[rootY]) {
      int temp = rootX;
      rootX = rootY;
      rootY = temp;
    }
    parent[rootY] = rootX;
    size[rootX] += size[rootY];
    maxSize = Math.max(maxSize, size[rootX]);
    return true;
  }
}

