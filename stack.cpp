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

// Using the linked list implementation
class Stack
{
    int itemCount = 0;
    Node *top = nullptr;
    Node *tail = nullptr;

public:
    int push(int data)
    {
        if (itemCount == 0)
        {
            top = new Node(data);
        }
        else
        {
            top = new Node(data, top);
        }
        itemCount++;
    }

    int pop()
    {
        if (isEmpty())
        {
            throw -1;
        }
        else
        {
            Node *tmp = top;
            int data = tmp->getData();
            top = top->getNext();
            itemCount--;
            free(tmp);
            return data;
        }
    }

    bool isEmpty()
    {
        return itemCount == 0;
    }

    int peek()
    {
        return top->getData();
    }
};

int main()
{

    cout << "Testing stack implementation" << endl;
    Stack stack;
    stack.push(10);
    stack.push(20);
    stack.push(30);
    stack.push(40);

    cout << stack.pop() << endl;
    cout << stack.pop() << endl;
    cout << stack.pop() << endl;
    cout << stack.pop() << endl;
    

    stack.push(50);
    cout << stack.pop() << endl;

    cout << "Test completed" << endl;
    return 0;
}