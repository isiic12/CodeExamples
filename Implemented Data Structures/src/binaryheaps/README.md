# Binary Heap

### About this data structure
A form of a binary tree, a binary heap is useful for proper ordering of elements by least->greatest or greatest->least. Unlike many trees, the heap is typically stored as
an array (or sometimes an ArrayList, as my implementation does). It has several important
properties:

1) It is a complete binary tree (all levels of the tree are entirely filled, except for possibly the lowest level, where elements are stored as leftwards as possible).
2) In a Min Binary Heap, all elements are the smallest element of their subtree - that is, the root is the smallest element in the entire tree, its left child is the smallest element in the entire left subtree, its right child is the smallest element in the entire right subtree, and so on down the entire tree. In a Max Binary Heap this order is reversed (the root element is the greatest in the entire tree, etc).

This implementation of a heap is designed to hold any type of elements, so long as the element's class uses Java's **Comparable** interface. This is neccessary to sort the elements of the heap
to ensure proper heap order. This implementation also includes a number of different constructors that allow a heap to be instantiated in a number of different ways. These include creating an empty heap and creating a heap and populating it using values from an array, an ArrayList, a Set, a Stack, or a Queue.

<sub> **Important Note:** This implementation begins storing elements at index _0_ of the ArrayList, not _1_, as is sometimes seen.

### <br> 1. Child/Parent elements
Because the heap is a complete binary tree and is stored in an ArrayList with the first element
at index _0_, accessing the child (left or right elements below) or parent (element above) of any given element is very simple. The parent of the element at index _i_ is always stored at index _(i-1)/2_. The left child of the element is stored at index _2i+1_, and the right child at
_2i+2_.

### <br> 2. Constructors
This heap has a variety of constructors that allow for multiple methods of instantiation. 
These include:
* Basic [creates a new heap with an empty ArrayList]
* Array based [creates a new heap and populates it with values stored in an array]
* ArrayList based [creates a new heap and populates it with values stored in an ArrayList]
* Set based [creates a new heap and populates it with values stored in a Set]
* Stack based [creates a new heap and populates it with values stored in a stack]
* Queue based [creates a new heap and populates it with values stored in a queue]

### <br> 3. The _Heap.java_ Interface
</p> While this repository only contains a MinHeap, this class implements the *Heap.java*  interface. This was done to demonstrate how Java classes interact with interfaces. If I were also to create a MaxHeap class, it would be useful to have an interface that both classes implemented, as a list of methods needed by each class.

### <br> 4. Methods
</p> My implementation contains most of the methods Java's included **PriorityQueue** class uses. The full list of these methods can be found [here](https://docs.oracle.com/javase/7/docs/api/java/util/PriorityQueue.html "Java PriorityQueue") . The list of methods implemented here includes:

* _offer_ - Adds the passed new element to the heap, then moves it to its correct position based on proper heap ordering.
* _clear_ - Resets the heap
* _contains_ - Boolean method that returns true if the given value exists in the heap, false otherwise
* _peek_ - Returns the value stored at the top of the heap without removing it (null if heap is empty).
* _poll_ - Removes and returns the value stored at the top of the heap (null if heap is empty). 
* _removeValue_ - Removes the specified value if it exists anywhere in the heap (returns false if the value did not exist in the heap).
* _isEmpty_ - Boolean method that returns true if nothing is in the heap, false otherwise.
* _size_ - Returns the number of elements in the heap.
* _getHeap_ - Returns the ArrayList which the heap is based on.
* _toString_ - Returns a string representation of the heap based on the order the elements are stored in the heap's ArrayList.
* _leftChild_ - Private method that returns the index of an element's left child.
* _rightChild_ - Private method that returns the index of an element's right child.
* _parent_ - Private method that returns the index of an element's parent.
* _heapify_ - Private method that sifts an element down the heap to its proper location.
* _swap_ - Private method that swaps two elements in the heap.

### <br> 5. Tests
</p> The _tests.java_ file contains all the JUnit tests I wrote to test the data structure's methods. With one minor exception for convinience, the tests each check one method for proper performance. Included in this file is the non-test method _createMinHeap_, which returns a MinHeap already populated with values. This method is used by many of the tests purely for convinience, as it reduced the repetative lines of code needed.