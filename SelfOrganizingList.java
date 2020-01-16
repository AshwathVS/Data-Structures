/**
 * For reusing the code, counter variable is by default present in the node class.
 * It will be used only for the COUNTING type
 * @param <T>
 */
class SelfOrganizingList<T> {

    private Type type;

    int size;

    Node<T> head;

    Node<T> tail;

    public SelfOrganizingList(Type type) {
        this.type = type;
        head = null;
        tail = null;
        size = 0;
    }

    void add (T data) {
        if (head == null) {
            head = new Node<T>(data);
            tail = head;
            size++;
        } else {
            Node<T> tmp = new Node<T>(data);
            tail.setNext(tmp);
            tail = tmp;
        }
    }

    private void organize(Node<T> node) {
        switch (this.type) {
            case COUNTING: {
                node.incrementCounter();
                if (node == head) {
                    return;
                } else {
                    // find the previous pointer of the node
                    Node<T> prev = null;
                    for (prev = head; prev != null; prev = prev.getNext()) {
                        if (prev.getNext() == node) {
                            break;
                        }
                    }

                    if (node.getCounter() >= head.getCounter()) {
                        prev.setNext(node.getNext());
                        node.setNext(head);
                        head = node;
                    } else {
                        for (Node<T> tmp = head; tmp != null && tmp.getNext() != null; tmp = tmp.getNext()) {
                            if (node.getCounter() >= tmp.getNext().getCounter()) {
                                prev.setNext(node.getNext());
                                node.setNext(tmp.getNext());
                                tmp.setNext(node);
                                break;
                            }
                        }
                    }
                }

                break;
            }
            case TRANSPOSE: {
                if (head == node) {
                    return;
                } else {
                    Node<T> ptr1 = head;
                    Node<T> ptr2 = head.getNext();
                    if (ptr2 == node) {
                        node.setNext(head);
                        head.setNext(node.getNext());
                        head = node;
                    } else {
                        do {
                            if (ptr2.getNext() == node) {
                                ptr1.setNext(node);
                                ptr2.setNext(node.getNext());
                                node.setNext(ptr2);
                                break;
                            }
                            ptr1 = ptr1.getNext();
                            ptr2 = ptr2.getNext();
                        } while (ptr2 != null);
                    }
                }
                break;
            }
            case MOVE_TO_FRONT: {
                if (head == node) {
                    return;
                } else {
                    for (Node<T> tmp = head; tmp != null; tmp = tmp.getNext()) {
                        if (tmp.getNext() == node) {
                            tmp.setNext(node.getNext());
                            node.setNext(head);
                            head = node;
                        }
                    }
                }
                break;
            }
        }
    }

    public Node<T> find(T data) {
        Node<T> returnValue = null;
        if (size != 0) {
            Node<T> tmp = head;
            while (tmp != null) {
                if (equals(tmp.getData(), data)) {
                    returnValue = tmp;
                    break;
                }
                tmp = tmp.getNext();
            }
        }
        if (returnValue != null) {
            organize(returnValue);
        }
        return returnValue;
    }

    public void remove(T data) {
        if (size != 0) {
            if (equals(head.getData(), data)) {
                head = head.getNext();
                size--;
            }
            else {
                Node<T> tmp = head;
                while (tmp != null) {
                    if (tmp.getNext() != null && equals(tmp.getNext().getData(), data)) {
                        tmp.setNext(tmp.getNext().getNext());
                        size--;
                        break;
                    }
                    tmp = tmp.getNext();
                }
            }
        }
    }

    public int getSize() {
        return size;
    }

    public boolean equals(T source, T target) {
        if (source != null && target != null) {
            return source.equals(target);
        } else {
            return false;
        }
    }

    public void print() {
        for(Node<T> tmp = head; tmp != null; tmp = tmp.getNext()) {
            System.out.print(tmp.getData() + "==>");
        }
        System.out.println("NULL");
    }

    /*
    Static section
     */
    public static class Node<T> {

        private T data;
        private int counter;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.counter = 0;
            this.next = null;
        }

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
            this.counter = 0;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public int getCounter() {
            return counter;
        }

        public void incrementCounter() {
            this.counter++;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    public static enum Type {
        MOVE_TO_FRONT,
        COUNTING,
        TRANSPOSE,;
    }

    public static void main(String[] args) {
        SelfOrganizingList<Integer> list = new SelfOrganizingList<Integer>(Type.COUNTING);
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        list.print();
        list.find(30);
        list.find(20);
        list.find(30);
        list.find(30);
        list.find(10);
        list.find(10);
        list.print();
    }
}