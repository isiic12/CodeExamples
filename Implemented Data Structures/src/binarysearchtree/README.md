# Binary Search Tree (BST)

### About this data structure
Slightly more advanced than a linked list and one of my favorite data structures,
I chose this as the next one to implement. The tree is designed to hold
key-value pairs of any type the user chooses to specify, on the condition that the
key's type extends Java's **Comparable** interface so that the tree's nodes can be properly ordered
according to rules of a BST. I've added a number of constructors that allow the user to create
new binary search trees in a variety of ways. These include creating an empty tree, a tree with an initial root using a provided key-value pairing, and a tree from an existing map. The tree contains numerous methods that I felt would be useful to a user, but if there's anything that sticks out as missing, feel free to contact me and I'll add it as soon as I'm able. 

### <br> 1. Nodes
 The tree is made up of nodes, a class defined at the top of the BST.java file. Each node has 4 data fields: _key_ , _val_, _val_, and _right_. The _key_ field is of generic type _K_, and the only restriction on what can be passed on keys is they must be comparable using Java's
 **Comparable** interface - as long as the key implements it, any class can be used as a key. the _val_ field is of generic type _V_, and can be any data type you wish. The _left_ and 
 _right_ fields point to each node's left and right children, respectively. Nodes are always instantiated using a key-value pair. The Node class also contains a personalized **toString** method, which returns a string representation of the node of form: " _key_:_val_ ".

 ### <br> 2. Constructors
 I wrote a variety of constructors for the tree that allow for multiple methods of instantiation. These include:
 * Basic [creates a new and empty tree, with a null root]
 * Key-Value pair [creates a new tree with the root storing the provided key-value]
 * From map [creates a new tree having nodes with key-value pairs stored in a provided map]

<sub> Important Note regarding the "From Map" constructor: The constructor retrieves all the keys stored in the map using Java's *map.keySet()* method. It then uses those keys to look up the associated values in the map. While this is very efficient - retriving values from a HashMap, for example, typically has a time complexity of O(1), assuming minimal collisions with other keys in the hash bucket - the set is automatically ordered. As such, the mapping with the minimum key is inserted into the tree as its root, with each subsequent new node being the next sequential pair in the Set. This will generate a suboptimal BST that more closely resembles a linked list than a tree. Operations on such a worst-case tree will be O(n). The goal of writing a constructor that creates a tree from a map was not to create a balanced tree, but rather to show my familiarity with Maps.


### <br> 3. Methods
</p> I wrote many methods for this structure, and strove to make them as comprehensive as possible. The list of methods includes:

* _getRoot_ - Returns the root of the tree, as acess to the root is restricted under the principles of data encapsulation
* _insert_ - inserts the given key-value pairing into the tree
* _containsKey_ - boolean method that checks if the tree contains a node with the given key
* _containsVal_ - boolean method that checks if the tree contains a node with the given value
* _get_ - returns the value associated with the given key
* _delete_ - deletes the node with the given key from the tree
* _maxKey_ - returns the maximum key stored in the tree
* _maxVal_ - returns the value associated with the maximum key stored in the tree
* _minKey_ - returns the minimum key stored in the tree
* _minVal_ - returns the value associated with the minimum key stored in the tree
* _invert_ - inverts the tree (turns the tree into a mirror image of itself)
* _isBST_ - boolean method that checks if the tree is a valid BST based on rules of node ordering
* _isFull_ - boolean method that checks if the tree is full (all nodes have 0 or 2 children)
* _isComplete_ -boolean method that chekcs if the tree is complete (all levels except last filled, last is left justified)
* _preOrder_ - returns a String representing a pre-order traversal of the tree
* _inOrder_ - returns a String representing a in-order traversal of the tree
* _postOrder_ - returns a String representing a post-order traversal of the tree
* _reverseOrder_ - returns a String representing a reverse-order traversal of the tree
* _height_ - returns the maximum depth of the tree (number of levels; root to farthest leaf node)
* _minHeight_ - returns the minimum depth of the tree (number of levels; root to lowest leaf node)
* _size_ - returns the number of nodes in the tree
* _numLeaves_ - returns the number of leaf nodes (0 children) in the tree
* _numInternal_ - returns the number of internal nodes (1 or 2 children) in the tree
* _clear_ - resets the entire tree by setting the root to null

<sub> Important Note regarding methods: all methods are impelemented recursively; that is, each method begins at the root and calls itself on its children as it moves down the tree. This was done to showcase my understand of recursive processes, however, I also understand that for particularly large data sets some of these would be suboptimal, and a iterative approach would reduce stack space needed to execute the desired processes. Other methods are not particularly useful - for example, the invert method, which by definition breaks the proper ordering of nodes in a BST. I chose to implement some of these as a personal challenge to myself, or because I found it an interesting feature.

### <br> 4. Tests
</p> The tests.java file contains all of the JUnit tests I wrote to test the structure's methods. The file is relatively long for a fairly simple strucute, and many of the tests check very similar things, and could have been combined together. I chose to separate many of the similar ones into distinct tests for ease of comprehension for whoever chooses to view them. Each one comes accompanied with a short, 1 line comment explaining exactly what is being tested - though the naming convention should make that relatively apparent as well. As such, almost every test checks one method in one particular way (though sometimes multiple times). For example, there are separate tests for checking the deleting of a leaf node, an internal node, or the root of the tree, and for deleting a chain of nodes.