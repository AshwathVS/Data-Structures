/**
 * Adding elements with index function to be added, this is a simple implementation of the unrolled list
 * @param <T>
 */
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

    private Node<T> getNewNode() {
        nodeCount++;
        return new Node<T>(this.numberOfElementsPerNode);
    }

    public void remove(T data) {
        Node<T> nodeWithData = find(data);
        if (nodeWithData != null) {
            for (int i = 0; i < nodeWithData.getCountOfElements(); i++) {
                if (equals(data, nodeWithData.get(i))) {
                    int j = 0;
                    for (j = i; j < nodeWithData.getCountOfElements() - 1; j++) {
                        nodeWithData.set(j, nodeWithData.get(j + 1));
                    }
                    nodeWithData.nullify(j);
                    break;
                }
            }
        }
    }

    public void add(T data) {
        if (front == null) {
            front = getNewNode();
            front.set(front.getCountOfElements(), data);
            back = front;
        } else {
            back.set(back.getCountOfElements(), data);
        }

        // create a new node and shift half of the contents to the new node
        if (back.getCountOfElements() == this.numberOfElementsPerNode) {
            Node<T> tmp = getNewNode();
            back.setNext(tmp);

            // copying half of the elements to the newly created node
            int half = this.numberOfElementsPerNode / 2;
            for (int i = 0; i < half; i++) {
                tmp.set(tmp.getCountOfElements(), back.get(i + half));
                back.nullify(i + half);
            }
            back = tmp;
        }
    }

    public int size() {
        return this.numberOfElements;
    }

    public boolean contains(T data) {
        return find(data) != null;
    }

    private Node<T> find(T data) {
        for(Node<T> tmp = front; tmp != null && tmp.getCountOfElements() > 0; tmp = tmp.getNext()) {
            for (int i = 0; i < tmp.getCountOfElements(); i++) {
                if(equals(data, tmp.get(i))) {
                    return tmp;
                }
            }
        }
        return null;
    }

    public void print() {
        for (Node<T> tmp = front; tmp != null; tmp = tmp.getNext()) {
            System.out.print(tmp.getCountOfElements() + ": ");
            for (int i = 0; i < tmp.getCountOfElements(); i++) {
                System.out.print(tmp.get(i) + " ");
            }
            System.out.println();
        }
    }

    public boolean equals(T value1, T value2) {
        if (value1 != null && value2 != null) {
            return value1.equals(value2);
        } else if (value1 == null && value2 == null) {
            return true;
        } else {
            return false;
        }
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

        public Node(int arrSize) {
            this(arrSize, null);
        }

        public int getCountOfElements() {
            return countOfElements;
        }

        public void setCountOfElements(int countOfElements) {
            this.countOfElements = countOfElements;
        }

        public void set(int index, T data) {
            validateIndex(index);
            // count is increased only for new element sets
            if (this.arr[index] == null) {
                countOfElements++;
            }
            this.arr[index] = data;
        }

        public void nullify(int index) {
            validateIndex(index);
            if (arr[index] != null) {
                countOfElements--;
            }
            this.arr[index] = null;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public T get(int index) {
            validateIndex(index);
            return arr[index];
        }

        public void validateIndex(int index) {
            if (index >= this.arr.length) {
                throw new IndexOutOfBoundsException("Index value out of bound");
            }
        }

    }

    public static void main(String[] args) {
        UnrolledLinkedList<Integer> unrolledLinkedList = new UnrolledLinkedList<Integer>(8);
        unrolledLinkedList.add(10);
        unrolledLinkedList.add(20);
        unrolledLinkedList.add(30);
        unrolledLinkedList.add(40);
        unrolledLinkedList.add(50);
        unrolledLinkedList.add(60);
        unrolledLinkedList.add(70);
        unrolledLinkedList.add(80);

        unrolledLinkedList.print();
        unrolledLinkedList.remove(20);
        unrolledLinkedList.print();

        unrolledLinkedList.add(51);
        unrolledLinkedList.add(62);
        unrolledLinkedList.add(73);
        unrolledLinkedList.add(84);
        unrolledLinkedList.print();

    }
}