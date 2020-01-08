#include <iostream>

using namespace std;

class BitArray {
    private:
        const int numberOfBitsPerInt = 32;
        int* _bitArray;
        int _size;
        int _arraySize;
        
        void initialize(int size) {
            // 32 bits per int, so calculating number of ints needed
            int requiredSize = size / numberOfBitsPerInt;
            if (size % numberOfBitsPerInt != 0) {
                requiredSize++;
            }

            _bitArray = new int[requiredSize];
            _arraySize = requiredSize;
            _size = size;
        }

        void sizeCheck(BitArray bitArray) {
            if(bitArray.getSize() != this->_size) {
                throw -1;
            }
        }

    public:

        BitArray(int size) {            
            initialize(size);
        }

        BitArray(int size, bool baseValue) {
            initialize(size);
            setAll(baseValue);
        }

        bool getBit(int position) {
            if (position > _size) {
                throw -1;
            } else{
                int divide = position / numberOfBitsPerInt;
                int modulo = position % numberOfBitsPerInt;

                int tmp = 1;
                tmp = tmp << modulo;

                if (_bitArray[divide] & tmp) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        void setBit(int position, bool value) {
            int divide = position / numberOfBitsPerInt;
            int modulo = position % numberOfBitsPerInt;

            int tmp = 1;
            if (value) {
                _bitArray[divide] |= (tmp << modulo);
            } else {
                int a = ~0;                
                _bitArray[divide] &= (a - (tmp << modulo));
            }

        }

        void setAll(bool value) {
            for(int i = 0; i < _arraySize; i++) {
                if(value) {
                    int comp = ~_bitArray[i];
                    _bitArray[i] |= comp;
                } else
                {
                    _bitArray[i] &= 0;
                }
                
            }
        }

        int getSize() {
            return _size;
        }

        void notTheArray() {
            for(int i = 0; i < _arraySize; i++) {
                _bitArray[i] = ~_bitArray[i];
            }
        }
};

int main() {
    BitArray bitArray(100, false);

    cout << "Testing the Bit Array" << endl;

    cout << bitArray.getBit(5) << endl;
    bitArray.setBit(5, 1);
    cout << bitArray.getBit(5) << endl;

    bitArray.setBit(5, 0);
    cout << bitArray.getBit(5) << endl;

    bitArray.notTheArray();
    cout << bitArray.getBit(5) << endl;

    cout << endl << "Bit Array testing completed" << endl;
    return 0;
}