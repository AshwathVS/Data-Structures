#include <bits/stdc++.h>

using namespace std;

/*
A circular buffer or a circular array or ring buffer is a data structure where data is written on the head pointer 
and data is read from the tail pointer. There is no need to erase the tail data when we read the data from the tail 
as it will be erased as the head moves.
*/

class CircularBuffer
{
private:
    int size;
    int *buffer;
    int *head;
    int *tail;
    int numberOfElements;

    void initialize(const int size)
    {
        this->size = size;
        buffer = new int[size];
        head = buffer;
        tail = head;
        this->numberOfElements = 0;
    }

    void checkOutOfBound()
    {
        if (head == buffer + size)
        {
            head = buffer;
        }
        if (tail == buffer + size)
        {
            tail = buffer;
        }
    }

public:
    CircularBuffer(int initialSize)
    {
        initialize(initialSize);
    }

    void enqueue(int element)
    {
        *head = element;

        // if the buffer is full, then the head and the tail are at the same place and hence we need
        // to overwrite the existing value and move head and tail together.
        if (isBufferFull())
        {
            *head = element;
            head++;
            tail++;
        }
        else
        {
            *head = element;
            numberOfElements++;
            head++;
        }

        // make sure head and tail do not run out the specified memory
        checkOutOfBound();
    }

    int dequeue()
    {
        if (!isBufferEmpty())
        {
            int value = *tail;
            tail++;
            numberOfElements--;
            checkOutOfBound();
            return value;
        } else {
            throw -1;
        }
    }

    bool isBufferFull()
    {
        return numberOfElements == size;
    }

    bool isBufferEmpty()
    {
        return numberOfElements == 0;
    }

    void clearBuffer()
    {
        numberOfElements = 0;
        head = buffer;
        tail = buffer;
    }

    // Method useful for testing
    bool isTailEqualToHead()
    {
        return head == tail;
    }

    // Method useful for testing
    void printHeadAndTailLocations()
    {
        cout << "HEAD: " << head - buffer << endl;
        cout << "TAIL: " << tail - buffer << endl;
    }

    void print()
    {
        cout << "Printing elements of the buffer: " << endl;
        for (int i = 0; i < size; i++)
        {
            cout << "buffer[" << i << "] = " << buffer[i] << endl;
        }
    }
};

int main()
{
    CircularBuffer c(5);

    cout << "Testing the circular buffer" << endl;
    
    
    cout << "Is Buffer empty: " << c.isBufferEmpty() << endl;
    c.enqueue(5);
    c.enqueue(4);
    c.enqueue(3);
    c.enqueue(2);
    c.enqueue(1);
    cout << "Is Buffer Full: " << c.isBufferFull() << endl;
    c.print();
    c.printHeadAndTailLocations();
    c.enqueue(6);
    c.printHeadAndTailLocations();
    c.print();
    cout << "Is tail equal to head: " << c.isTailEqualToHead() << endl;
    cout << "Dequeuing element: " << c.dequeue() << endl;
    c.printHeadAndTailLocations();
    c.enqueue(10);
    c.printHeadAndTailLocations();


    cout << endl
         << "Testing complete" << endl;
    
    return 0;
}