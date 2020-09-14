# (Singly) Linked List

### About this structure 
As one of the simplest data structures, I felt this was a good place to start. The list is designed to hold
any type of data or object, so long as the object implements Java's comparable interface, as the  _compareTo_ 
method is used to determine element order when sorting the list. I've also created a variety of constructors 
that allow a user to create a new linked list in a variety of ways, most particularly using values stored in 
other data structures (such as sets or arrays). If there's any method that you believe would be useful but is 
currently missing, feel free to let me know and I'll add it as soon as I have time.

### 1. Nodes
The linked list is made up of node, which each have two data fields:  _value_  and  _next_.
The  _value_  field is of generic type  _V_  (the same data type/object that the list was
initially declared to contain). The  _next_  field is the pointer to the next node in the list.
Nodes may be initialized using one of two contrustors: **basic** and **pointed**, both requiring a
value to be assigned to the new node to be passed. The **basic** constructor only needs the initial
value, and automatically sets the  _next_  field to be **null**, while the **pointed** constructor also
requires another node to be passed, and will set the  _next_  field to store that node.

### 2. Constructors 
I wrote a variety of constructors for this structure that allow you to initialize it in various ways. These include: 
* Basic [creates a new linked list with 0 elements] 
* One node [creates a new linked list with 1 element given an initial value] 
* From array [creates a new linked list using elements of an array] 
* From Set [creates a new linked list using elements contained in a set] 

<sub><sup> **Important note:** due to the nature of sets, creating a new linked list from values stored in an 
      existing set does **NOT** gurantee a particular order</sup></sub>
      

### 3. Methods Implemented by this structure 
The following is a list of methods I wrote for this structure, based on what I felt would be handy: 
* _length_
      - returns the number of nodes in the list
* _add_
      - for adding a new item to the beginning of the list
* _append_ 
      - for adding a new item to the end of the list
* _insertAt_ 
      - for adding a new item at a given index in the list
* _delete_ 
      - removes node with associated value
* _contains_
      - checks if a node with a given value exists in the list
* _get_
      - gets the value of the node at a given index
* _sort_ 
      - in ascending order 
* _reverse_ 
      - reverses list
* _clear_ 
      - resets the list to be empty
* _toString_
      - prints out a string representation of the list


### 4. Tests
The Tests.java file contains all the JUnit tests I wrote to test the structure and its methods.
The file is particularly long (roughly 600 lines) for such a simple structure, and many tests
seem repetitive. This was intentional on my part, as I wanted them to be as easily understandable
to the casual browser as possible. As such, almost every test checks exactly one method in exactly one
way (for example, there are different tests for deleting the first, last, or a middle element in the list,
respectively).


**IMPORTANT**

It should be noted that of the mentioned implemented methods, not all would be
considered *efficient*. However, I chose to include them partly as a challenge 
to myself, as they perhaps required some extra thought on my part, or because 
I found them interesting. Some examples of these include the  _sort_  and  _reverse_  methods. 
In reality, sorting a linked list is highly inefficient, however, I felt like it was 
an interesting utility to implement.
