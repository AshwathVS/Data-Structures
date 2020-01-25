class BinarySearchTree<T extends Comparable> {

    private Node<T> root;

    private int size;

    public BinarySearchTree(T data) {
        this.root = new Node<T>(data);
        if (root != null) {
            this.size = 1;
        } else {
            this.size = 0;
        }
    }

    public BinarySearchTree() {
        this(null);
    }

    public void insert(T data) {
        if (data == null) {
            throw new NullPointerException("Data to be entered cannot be null");
        }
        insert(root, data);
    }

    private Node<T> insert(Node<T> node, T data) {
        // If it is the first element, that is root is null
        if (root == null) {
            root = new Node<T>(data);
            return root;
        }

        // Normal insertion steps..
        else {
            if (node == null) {
                return new Node<T>(data);
            }
            int compareResult = data.compareTo(node.getData());
            if (compareResult == 0) {
                System.out.println("Element already exists...");
                return null;
            } else if (compareResult < 0) {
                node.setLeft(insert(node.getLeft(), data));
            } else {
                node.setRight(insert(node.getRight(), data));
            }
            return node;
        }
    }

    public void delete(T data) {
        if (data == null) {
            throw new NullPointerException("Data to be entered cannot be null");
        }
        if (root == null) {
            System.out.println("Binary search tree is empty");
        }
        delete(root, data);
    }

    // Case 1: node is a leaf node
    // Case 2: node is having only one child
    // Case 3: node is having 2 children (We can either find max in the left or find the min in the right)
    // Taking the findMax approach
    private Node<T> delete(Node<T> node, T data) {
        int compareResult = data.compareTo(node.getData());
        if (node == null) {
            return null;
        } else if (compareResult > 0) {
            node.setRight(delete(node.getRight(), data));
        } else if (compareResult < 0) {
            node.setLeft(delete(node.getLeft(), data));
        } else {
            // Case 1:
            if (node.getRight() == null && node.getLeft() == null) {
                node = null;
            }

            // Case 2: 
            else if (node.getLeft() != null || node.getRight() != null) {
                if (node.getLeft() == null) {
                    node = node.getRight();
                } else {
                    node = node.getLeft();
                }
            }

            // Case 3:
            else {
                Node<T> max = findMax(node.getLeft());
                node.setData(max.getData());
                delete(node.getLeft(), max.getData());
            }
        }
        return node;
    }

    private Node<T> findMax(Node<T> node) {
        if (node != null && node.getRight() != null) {
            return findMax(node.getRight());
        } else {
            return node;
        }
    }

    private void findWithParent(T data, Node<T> parent, Node<T> node) {
        int compareResult = data.compareTo(node.getData());
        if (compareResult == 0) {
            return;
        } else if (compareResult > 0) {
            parent = node;
            node = node.getRight();
            findWithParent(data, parent, node);
        } else {
            parent = node;
            node = node.getLeft();
            findWithParent(data, parent, node);
        }
    }

    public Node<T> find(T data) {
        return find(root, data);
    }

    private Node<T> find(Node<T> node, T data) {
        if (node == null || data == null) {
            return node;
        } else {
            int compareResult = data.compareTo(node.getData());
            if (compareResult == 0) {
                return node;
            } else if (compareResult > 0) {
                return find(node.getRight(), data);
            } else {
                return find(node.getLeft(), data);
            }
        }
    }

    
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    public void PreOrderTraversal() {
        PreOrderTraversal(root);
    }

    private void inOrderTraversal(Node<T> node) {
        if (node == null) {
            return;
        } else {
            inOrderTraversal(node.getLeft());
            System.out.print(node.getData() + " ");
            inOrderTraversal(node.getRight());
        }
    }

    private void postOrderTraversal(Node<T> node) {
        if (node == null) {
            return;
        } else {
            postOrderTraversal(node.getLeft());
            postOrderTraversal(node.getRight());
            System.out.print(node.getData()+ " ");
        }
    }

    private void PreOrderTraversal(Node<T> node) {
        if (node == null) {
            return;
        } else {
            System.out.print(node.getData() + " ");
            inOrderTraversal(node.getLeft());
            inOrderTraversal(node.getRight());
        }
    }

    /**
     * Node class definition
     * @param <T>
     */
    public static class Node<T> {
        private T data;
        private Node<T> left;
        private Node<T> right;

        public Node(T data, Node<T> left, Node<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public void setData(T data) {
            this.data = data;
        }
        
        public T getData() {
            return this.data;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }

        public Node<T> getRight() {
            return this.right;
        }
        
        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public Node<T> getLeft() {
            return this.left;
        }
    }
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(10);
        bst.insert(12);
        bst.insert(15);
        bst.insert(8);
        bst.insert(9);
        bst.insert(7);
        bst.insert(11);
        bst.insert(20);
        bst.inOrderTraversal();
        System.out.println();
    }
}