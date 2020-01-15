#include <bits/stdc++.h>

using namespace std;

class Node {
private:
    int data;
    Node* left;
    Node* right;
public:
    Node(int data, Node* left, Node* right) {
        this->data = data;
        this->left = left;
        this->right= right;
    }

    Node(int data) {
        this->data = data;
        this->left = nullptr;
        this->right= nullptr;
    }

    void setData(int data) {
        this->data = data;
    }

    int getData() {
        return this->data;
    }

    Node* getLeft() {
        return this->left;
    }

    void setLeft(Node* left) {
        this->left = left;
    }

    Node* getRight() {
        return this->right;
    }

    void setRight(Node* right) {
        this->right = right;
    }

};

class DoublyLinkedList
{
private:
    int size;
    Node* front;
    Node* back;
public:
    DoublyLinkedList() {
        front = nullptr;
        back = nullptr;
        size = 0;
    }

    int getSize() {
        return size;
    }

    void add(int data) {
        if (front == nullptr) {
            front = new Node(data);
            back = front;
        } else {
            Node* newNode = new Node(data, back, nullptr);
            back->setRight(newNode);
            back = newNode;
        }
        size++;
    }


    void remove(int data) {
        if (size != 0) {
            Node* tmp = front;
            while (tmp != nullptr) {
                if (tmp->getData() == data) {

                    // both left and right is not null
                    if (tmp->getRight() != nullptr && tmp->getLeft() != nullptr) {
                       tmp->getLeft()->setRight(tmp->getRight());
                       tmp->getRight()->setLeft(tmp->getLeft());
                    }

                    //left is null which means the element is back
                    else if (tmp->getLeft() != nullptr) {
                        tmp->getLeft()->setRight(nullptr);
                        back = tmp->getLeft();
                    }


                    //right is null which means the element is front
                    else if (tmp->getRight() != nullptr) {
                        tmp->getRight()->setLeft(nullptr);
                        front = tmp->getRight();
                    }

                    //both left and right are empty and there is only one element in the list
                    else {
                        front = nullptr;
                        back = nullptr;
                    }
                    free(tmp);
                    size--;
                }
                tmp = tmp->getRight();
            }
        }
    }


    Node* find(int data) {
        if (size == 0) {
            return nullptr;
        } else {
            Node* tmp = front;
            while (tmp != nullptr) {
                if (tmp->getData() == data) {
                    return tmp;
                }
                tmp = tmp->getRight();
            }
        }
    }

    Node* peekFront() {
        return front;
    }

    Node* peekBack() {
        return back;
    }

    void clear() {
        if (size != 0) {
            while(front != nullptr) {
                Node* current = front;
                free(current);
                front = front->getRight();
            }
            size = 0;
        }
    }

    void print() {
        if (size != 0) {
            Node* tmp = front;
            cout << "NULL<==>";
            while (tmp != nullptr) {
                cout << tmp->getData() << "<==>";
                tmp = tmp->getRight();
            }
            cout << "NULL" << endl;
        } else {
            cout << "List is empty" << endl;
        }
    }

};

int main()
{
    DoublyLinkedList dll;
    dll.add(10);
    dll.add(20);
    dll.add(30);
    dll.add(40);
    dll.print();

    dll.remove(20);
    dll.remove(10);
    dll.remove(30);
    dll.print();
    dll.remove(40);
    dll.print();
    
    return 0;
}