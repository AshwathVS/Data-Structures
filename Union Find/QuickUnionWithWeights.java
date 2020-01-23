public class QuickUnionWithWeights implements UnionFind {

    /**
     * Here each node is made to point to the parent node, hence the loop for setting the values in the source group 
     * is removed. But for checking find, we have to keep navigating through the tree.
     * 
     * Now since we are going to join the smaller set to the larger set, the height of the tree can be maintained 
     * and made sure the height is not almost linear.
     */

    private int[] arr;

    private int[] weights;

    public QuickUnionWithWeights(int size) {
        this.arr = new int[size];
        this.weights = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
            weights[i] = 1;
        }
    }

    public void union(int source, int target) {
        int sourceRoot = root(source);
        int targetRoot = root(target);
        if (weights[source] >= weights[target]) {
            arr[targetRoot] = sourceRoot;
            weights[sourceRoot] += weights[targetRoot];
        } else {
            arr[sourceRoot] = targetRoot;
            weights[targetRoot] += weights[sourceRoot];
        }
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
        System.out.print("INDEX:  ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.print("VALUE:  ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.print("WEIGHT: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(weights[i] + " ");
        }
    }

    public static void main(String[] args) {
        QuickUnionWithWeights quickUnionWithWeights = new QuickUnionWithWeights(10);
        quickUnionWithWeights.union(4, 3);
        quickUnionWithWeights.union(9, 4);
        quickUnionWithWeights.union(0, 8);
        quickUnionWithWeights.union(3, 2);
        quickUnionWithWeights.union(6, 5);
        quickUnionWithWeights.union(9, 5);
        quickUnionWithWeights.union(3, 7);
        quickUnionWithWeights.union(8, 4);
        quickUnionWithWeights.union(1, 6);

        System.out.println(quickUnionWithWeights.find(1, 5));
        quickUnionWithWeights.print();
        System.out.println();
    }
}