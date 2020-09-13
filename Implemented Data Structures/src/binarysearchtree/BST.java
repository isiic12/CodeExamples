package binarysearchtree;

public class BST<K extends Comparable<K>, V> {
	
	public Node root;

	public class Node{
		public K key;
		public V val;
		public Node left, right;
		
		Node(K key, V value) {
			this.key = key;
			this.val = value;
		}
		
		public String toString() {
			return this.key + ":" + this.val;
		}
	}
	
	public BST() {
		this.root = null;
	}
	
	public BST(K key, V value) {
		this.root = new Node(key, value);
	}
	
	public Node getRoot() {
		return this.root;
	}
	
	/**
	 * Public method for inserting a node with the given key-value pair into the tree
	 * @param key Key
	 * @param value Value
	 */
	public void insert(K key, V value) {	
		root = insert(this.root, key, value);
	}
	
	/* If the node does not exist, create it.
	 * Otherwise, go left/right depending on whether the key is less/greater than the 
	 * current node's key. If they are equal, update the associated value.
	 * Finally, return this node
	 */
	private Node insert(Node n, K key, V value) {
		if (n == null)
			n = new Node(key, value);
		else if (key.compareTo(n.key) < 0)
			n.left = insert(n.left, key, value);
		else if (key.compareTo(n.key) > 0)
			n.right = insert(n.right, key, value);
		else
			n.val = value;
		return n;
	}
	
	public boolean containsKey(K key) {
		return containsKey(root, key);
	}
	
	private boolean containsKey(Node n, K key) {
		if (n == null) return false;
		if (key.compareTo(n.key) < 0)
			return containsKey(n.left, key);
		else if (key.compareTo(n.key) > 0)
			return containsKey(n.right, key);
		else
			return true;
	}
	
	public boolean containsValue(V value) {
		return containsValue(root, value);
	}
	
	private boolean containsValue(Node n, V value) {
		if (n == null) return false;
		if (n.val == value) return true;
		
		return containsValue(n.left, value) || containsValue(n.right, value);
	}
	
	/**
	 * Method for getting the value associated with the specified key.
	 * @param key Key whose related value is requested.
	 * @return Value associated with provided key.
	 */
	public V get(K key) {
		return get(root, key);
	}
	
	/* If a null node is reached, no node with the provided key exists. Returns null.
	 * Otherwise, go left/right depending on whether the key is less/greater than the current
	 * node's key. If they are equal, the node has been found, so return the associated value.
	 */
	private V get(Node n, K key) {
		if (n == null) return null;
		if (key.compareTo(n.key) < 0)
			return get(n.left, key);
		else if (key.compareTo(n.key) > 0)
			return get(n.right, key);
		return n.val;
	}
	
	/**
	 * Deletes the node with the provided key.
	 * @param key Key of node to delete.
	 */
	public void delete(K key) {
		root = delete(root, key);
	}
	
	private Node delete(Node n, K key) {
		if (n == null) return null;
		
		if (key.compareTo(n.key) < 0)
			n.left = delete(n.left, key);
		else if (key.compareTo(n.key) > 0)
			n.right = delete(n.right, key);
		else {
			if (n.left == null && n.right == null) {
				n = null;
				return n;
			}
			Node temp;
			if (n.right != null)
				temp = min(n.right);
			else
				temp = max(n.left);
			
			delete(root, temp.key);
			n.key = temp.key;
			n.val = temp.val;
		}
		return n;
	}
	
	/**
	 * Method for getting the maximum key in the tree
	 * @return Maximum key in the tree
	 */
	public K maxKey() {
		return maxKey(root);
	}
	
	/*Helper function returns the maximum key from a given node*/
	private K maxKey(Node n) {
		Node temp = max(root);
		if (temp != null) 	return temp.key;
		return null;
	}
	
	/**
	 * Method for getting the value associated with the maximum key in the tree.
	 * @return Value associated to maximum key in the tree.
	 */
	public V maxVal() {
		Node n = max(root);
		if (n != null)
			return n.val;
		return null;
	}
	
	/* Finds the maximum node in the tree by key by going as far right as possible in 
	 * the tree. If the tree's root is null (tree is empty) returns null.*/
	private Node max(Node n) {
		if (n == null)			return null;
		if (n.right == null) 	return n;
		
		return max(n.right);
	}
	
	/**
	 * Public method for getting the minimum key in the tree
	 * @return Minimum key in the tree
	 */
	public K minKey() {
		return minKey(root);
	}
	
	/*Helper function returns the minimum key from a given node*/
	private K minKey(Node n) {
		Node temp = min(root);
		if (temp != null)  return temp.key;
		return null;
	}
	
	/**
	 * Method for getting the value associated with the minimum key in the tree.
	 * @return Value associated to minimum key in the tree.
	 */
	public V minVal() {
		Node n = min(root);
		if (n != null)  return n.val;
		return null;
	}
	
	/* Find the minimum node in the tree by key by going as far left as possible in
	 * the tree. IF the tree's root is null (tree is empty) returns null.*/
	private Node min(Node n) {
		if (n == null) 			return null;
		if (n.left == null) 	return n;
		
		return min(n.left);
	}
	
	/**
	 * Verifies that tree is a valid BST.
	 * @return True if tree is a valid BST, false otherwise.
	 */
	public boolean isBST() {
		K min = minKey(root);
		K max = maxKey(root);
		return isBST(root, min, max);
	}
	
	private boolean isBST(Node n, K min, K max) {
		if (n == null) return true;
		if (min != null && n.key.compareTo(min) < 0) return false;
		if (max != null && n.key.compareTo(max) > 0) return false;
		return isBST(n.left, min, max) && isBST(n.right, min, max);
	}
	
	/**
	 * Checks if the BST is a full tree.
	 * @return True if all nodes have 0 or 2 children, false if any node has only 1 child.
	 */
	public boolean isFull() {
		return isFull(root);
	}
	
	private boolean isFull(Node n) {
		if (n == null) return true;
		if (n.left == null && n.right == null) return true;
		if (n.left != null && n.right != null)
			return isFull(n.left) && isFull(n.right);
		
		return false;
	}
	
	public boolean isComplete() {
		int size = size(root);
		return isComplete(root, 0, size);
	}
	
	private boolean isComplete(Node n, int index, int size) {
		if (n == null) 		return true;
		if (index >= size)  return false;
		
		return isComplete(n.left, ((index*2)+1), size) && isComplete(n.right, ((index*2)+2), size);
	}
	
	/**
	 * Prints the preOrder traversal of the tree
	 */
	public void preOrder() {
		if (root == null) {
			System.out.println("Root is null");
			return;
		}
		preOrder(root);
		System.out.println();
	}
	
	private void preOrder(Node n) {
		if (n == null) return;
		System.out.print(n + " ");
		preOrder(n.left);
		preOrder(n.right);
	}
	
	/**
	 * Prints the inOrder traversal of the tree.
	 */
	public void inOrder() {
		if (root == null) {
			System.out.println("Root is null");
			return;
		}
		inOrder(root);
		System.out.println();
	}
	
	private void inOrder(Node n) {
		if (n == null) return;
		inOrder(n.left);
		System.out.print(n + " ");
		inOrder(n.right);
	}
	
	public void reverseOrder() {
		if (root == null) {
			System.out.println("Root is null");
			return;
		}
		reverseOrder(root);
		System.out.println();
	}
	
	private void reverseOrder(Node n) {
		if (n == null) return;
		reverseOrder(n.right);
		System.out.print(n + " ");
		reverseOrder(n.left);
	}
	
	/**
	 * Prints the postOrder traversal of the tree.
	 */
	public void postOrder() {
		if (root == null) {
			System.out.println("Root is null");
			return;
		}
		postOrder(root);
		System.out.println();
	}
	
	private void postOrder(Node n) {
		if (n == null) return;
		postOrder(n.left);
		postOrder(n.right);
		System.out.print(n + " ");
	}
	
	/**
	 * Gets the height (number of levels) of the tree. A tree with only a
	 * root node has a height of 1. A tree with no nodes has a height of 0.
	 * @return Height of the tree
	 */
	public int height() {
		return height(root);
	}
	
	private int height(Node n) {
		if (n == null) return 0;
		return (1 + Math.max(height(n.left), height(n.right)));
	}
	
	/**
	 * Find the minimum height of the BST.
	 * @return Minimum height of tree.
	 */
	public int minHeight() {
		return minHeight(root);
	}
	
	private int minHeight(Node n) {
		if (n == null) return 0;
		return 1 + Math.min(minHeight(n.left), minHeight(n.right));
	}
	
	/**
	 * Gets the size of the tree.
	 * @return Number of nodes in the tree.
	 */
	public int size() {
		return size(root);
	}
	
	private int size(Node n) {
		if (n == null) return 0;
		return 1 + size(n.left) + size(n.right);
	}
	
	/**
	 * Gets the number of leaves in the tree.
	 * @return Number of nodes with no children in the tree.
	 */
	public int numLeaves() {
		return numLeaves(root);
	}
	
	private int numLeaves(Node n) {
		if (n == null) return 0;
		if (n.left == null && n.right == null) return 1;
		return numLeaves(n.left) + numLeaves(n.right);
	}
	
	/**
	 * Clears the entire tree.
	 */
	public void clear() {
		root = null;
	}
}
