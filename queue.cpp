#include <iostream>

using namespace std;

class Node
{
private:
    int data;
    Node *next;

    void initialize(int data, Node *next)
    {
        this->data = data;
        this->next = next;
    }

public:
    Node(int data)
    {
        initialize(data, nullptr);
    }

    Node(int data, Node *next)
    {
        initialize(data, next);
    }

    void setData(int data)
    {
        this->data = data;
    }

    int getData()
    {
        return data;
    }

    void setNext(Node *next)
    {
        this->next = next;
    }

    Node *getNext()
    {
        return this->next;
    }
};

class Queue
{
private:

    Node *front;
    Node *back;

    int itemCount = 0;

public:

    // same as enqueue
    int add(int data) {
        if(isEmpty()) {
            front = new Node(data);
            back = front;
        } else {
            Node* tmp = new Node(data);
            back->setNext(tmp);
            back = tmp;
        }
        itemCount++;
    }

    // same as dequeue
    int remove() {
        if (isEmpty()) {
            throw -1;
        } else {
            Node* tmp = front;
            front = front->getNext();

            int value = tmp->getData();
            free(tmp);
            return value;
        }
    }

    // this is same as remove but will not throw exception if the queue is empty and will return null
    // for the exercise sake, we will return INT_MIN value instead of null,
    int poll() {
        if (isEmpty()) {
            return INT32_MIN;
        } else {
            return remove();
        }
    }

    int size() {
        return itemCount;
    }

    bool isEmpty() {
        return itemCount == 0;
    }

    // peek is similar to poll but will not remove the element
    int peek() {
        if(isEmpty()) {
            return INT32_MIN;
        } else {
            return front->getData();
        }
    }
};

int main()
{
    cout << "Testing the Queue implementation" << endl;
    Queue q;
    q.add(10);
    q.add(20);
    q.add(30);
    q.add(40);
    cout << q.peek() << endl;
    cout << q.remove() << endl;
    cout << q.poll() << endl;
    cout << "Testing completed" << endl;
}
