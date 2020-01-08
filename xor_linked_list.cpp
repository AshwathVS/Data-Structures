#include <iostream>

using namespace std;

class node {
private:
    int _data;
    node* _addr;
public:
    node(int data) {
        _data = data;
    }
    void setData(int data) {
        _data = data;
    }
    
    int getData() {
        return _data;
    }

    node* getAddress() {
        return _addr;
    }
    
    void setAddress(node* addr) {
        _addr = addr;
    }
    
    void setAddress(node* prev, node* next) {
        _addr = (node*) ((uintptr_t) prev ^ (uintptr_t) next);
    }
};

class doublylinkedlist {
private:
    node *head = NULL, *tail = NULL, *itr = NULL;
    int _size;
public:
    void add(int value) {
        if(head == NULL) {
            head = new node(value);
            head->setAddress(NULL, NULL);
            tail = head;
        }
        else {
            itr = new node(value);
            itr->setAddress(tail, NULL);
            tail->setAddress(tail->getAddress(), itr);
            tail = itr;
        }
        _size++;
    }
    
    node* getXOR(node* prev, node* next) {
        return (node*) ((uintptr_t) prev ^ (uintptr_t) next);
    }

    void print() {
        itr = head;
        node *prev = NULL, *next = NULL;
        cout << "Values: " << endl;
        while (itr != NULL) {
            cout << itr->getData() << " " ;
            next = getXOR(itr->getAddress(), prev);
            prev = itr;
            itr = next;
        }
        cout << endl;
    }
    
    int getSize() {
        return _size;
    }
};


int main() {
    doublylinkedlist *list = new doublylinkedlist;
    list->add(10);
    list->add(20);
    list->add(30);
    list->add(40);
    list->print();
}