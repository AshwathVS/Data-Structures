public class QuickUnionWithWeightsAndPathCompression implements UnionFind {

    /**
     * Here each node is made to point to the parent node, hence the loop for setting the values in the source group 
     * is removed. But for checking find, we have to keep navigating through the tree.
     * 
     * Now since we are going to join the smaller set to the larger set, the height of the tree can be maintained 
     * and made sure the height is not almost linear.
     * 
     * Path compression will make all the children instead of pointing to the parent, will point to the root.
     * OR
     * Point to the grand parent
     * 
     */

    private int[] arr;

    private int[] weights;

    public QuickUnionWithWeightsAndPathCompression(int size) {
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
            // Pointing to grandparent
            arr[a] = arr[arr[a]];

            // Or we can run another loop and make 'a' point to its root (not implemented)
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
        QuickUnionWithWeightsAndPathCompression qAndPathCompression = new QuickUnionWithWeightsAndPathCompression(10);
        qAndPathCompression.union(4, 3);
        qAndPathCompression.union(9, 4);
        qAndPathCompression.union(0, 8);
        qAndPathCompression.union(3, 2);
        qAndPathCompression.union(6, 5);
        qAndPathCompression.union(9, 5);
        qAndPathCompression.union(3, 7);
        qAndPathCompression.union(8, 4);
        qAndPathCompression.union(1, 6);

        System.out.println(qAndPathCompression.find(1, 5));
        qAndPathCompression.print();
        System.out.println();
    }
}