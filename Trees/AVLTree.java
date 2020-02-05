/*  
    AVL Tree is a self balancing tree and maintains the height of the tree ~= log(n).

    There are four cases when we need to do a rotation. Generally we need to do a rotation if the difference of heights 
    between left subtree and the right subtree is greater than 1.

    Case 1: If the imbalance exists in the left child's left subtree, we do a right rotation.
            Left left case -> right rotation
    
    Case 2: If the imbalance exists in the right child's right subtree, we do a left rotation.
            Right right case -> left rotation
    
    Case 3: If the imbalance exists in the right child's left subtree, we do a right rotation on the parent and a left rotation on the granparent
            Right Left case -> right rotation and left rotation
    
    Case 4: If the imbalance exists in the left child's right subtree, we do a left rotation on the parent and a right rotation on the grandparent
            Left Right case -> left rotation and right rotation
*/
class AVLTree {
    public static void main (String[] args) {
        System.out.println("Hello");
    }
}