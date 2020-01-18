class UnrolledLinkedList<T> {

    private int nodeCount;

    private int numberOfElements;

    private int numberOfElementsPerNode;

    private Node<T> front;

    private Node<T> back;

    public UnrolledLinkedList(int numberOfElementsPerNode) {
        this.numberOfElementsPerNode = numberOfElementsPerNode;
    }

    public UnrolledLinkedList() {
        this(8);
    }

    public void remove(int index) {

    }

    public void remove(T data) {

    }

    public void add(T data) {

    }

    public void add(T data, int index) {

    }

    public int size() {
        return this.numberOfElements;
    }

    public T find(T data) {

    }

    public static class Node<T> {
        private int countOfElements;
        private T[] arr;
        private Node<T> next;

        public Node(int arrSize, Node<T> next) {
            this.arr = (T[]) new Object[arrSize];
            this.next = next;
            this.countOfElements = 0;
        }

        public int getCountOfElements() {
            return countOfElements;
        }

        public void setCountOfElements(int countOfElements) {
            this.countOfElements = countOfElements;
        }

        public T[] getArr() {
            return arr;
        }

        public void setArr(T[] arr) {
            this.arr = arr;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello..");
    }
}