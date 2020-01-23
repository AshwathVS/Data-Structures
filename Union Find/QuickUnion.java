public class QuickUnion implements UnionFind {

    /**
     * Here each node is made to point to the parent node, hence the loop for setting the values in the source group 
     * is removed. But for checking find, we have to keep navigating through the tree.
     * 
     * This tree can grow long in one direction which will make the tree a list and traversing will take time.
     */

    private int[] arr;

    public QuickUnion(int size) {
        this.arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
    }

    public void union(int source, int target) {
        arr[root(source)] = root(target);
    }

    public boolean find(int a, int b) {
        return root(a) == root(b);
    }

    private int root(int a) {
        while (arr[a] != a) {
            a = arr[a];
        }
        return a;
    }

    public void print() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        QuickUnion quickUnion = new QuickUnion(10);
        quickUnion.union(4, 3);
        quickUnion.union(9, 4);
        quickUnion.union(0, 8);
        quickUnion.union(3, 2);
        quickUnion.union(6, 5);
        quickUnion.union(9, 5);
        quickUnion.union(3, 7);
        quickUnion.union(8, 4);
        quickUnion.union(1, 6);

        System.out.println(quickUnion.find(1, 5));
        quickUnion.print();
        System.out.println();
    }
}