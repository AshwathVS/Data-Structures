public class QuickFind implements UnionFind {

    /**
     * The indices are made to point to the representative of the target node. Hence a loop has to be run so that we 
     * can initialize all the nodes in the source set to the target set.
     */

    private int[] arr;

    public QuickFind(int size) {
        this.arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
    }

    public void union(int source, int target) {
        int currentValue = arr[source];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == currentValue) {
                arr[i] = arr[target];
            }
        }
    }

    public boolean find(int a, int b) {
        return arr[a] == arr[b];
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
        QuickFind quickFind = new QuickFind(10);
        quickFind.union(5, 6);
        quickFind.union(2, 3);
        quickFind.union(3, 4);
        quickFind.union(2, 6);
        quickFind.union(1, 9);
        quickFind.union(1, 2);
        System.out.println(quickFind.find(1, 5));
        quickFind.print();
        System.out.println();
    }
}