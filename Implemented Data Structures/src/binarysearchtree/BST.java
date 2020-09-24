package binarysearchtree;

import java.util.Map;
import java.util.Set;

public class BST<K extends Comparable<K>, V> {
	
	private Node root;

	protected class Node{
		protected K key;
		protected V val;
		protected Node left, right;
		
		Node(K key, V value) {
			this.key = key;
			this.val = value;
		}
		
		@Override
		public String toString() {
			return this.key + ":" + this.val + " ";
		}
	}
	
	/**
	 * Default constructor; initializes an empty tree with a null root
	 */
	public BST() {
		this.root = null;
	}
	
	/**
	 * Constructor for initializing tree with given key-value pair
	 * @param key Key of root node
	 * @param value Value of root node
	 */
	public BST(K key, V value) {
		this.root = new Node(key, value);
	}
	
	/**
	 * Constructor that allows user to create a new tree and populate it
	 * using key-value pairs stored in a map. Due to the nature of maps,
	 * this will not guarantee any particular ordering of elements within the
	 * tree, outside of normal tree orderings - i.e. multiple instantiations 
	 * of trees based on the same map may have different roots and shapes.
	 * 
	 * @param map Map whose key-value pairs will be used to populate a new tree
	 */
	public BST(Map<K, V> map) {
		this();
		if (!map.isEmpty()) {
			Set<K> keys = map.keySet();
			for (K key : keys) {
				this.insert(key, map.get(key));
			}
		}
	}
	
	/**
	 * Getter for tree's root
	 * @return Root node of tree
	 */
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
		
		int comparison = key.compareTo(n.key);
		if (comparison < 0)
			n.left = insert(n.left, key, value);
		else if (comparison > 0)
			n.right = insert(n.right, key, value);
		else
			n.val = value;
		return n;
	}
	
	/**
	 * Checks if a node with the given key exists in the tree
	 * @param key Key to search for in the tree
	 * @return True if node with the given key exists in the tree, false otherwise
	 */
	public boolean containsKey(K key) {
		return containsKey(root, key);
	}
	
	private boolean containsKey(Node n, K key) {
		if (n == null) return false;
		
		int comparison = key.compareTo(n.key);
		if (comparison < 0)
			return containsKey(n.left, key);
		else if (comparison > 0)
			return containsKey(n.right, key);
		else
			return true;
	}
	
	/**
	 * Checks if a node with a given value exists in the tree
	 * @param value Value to search for in the tree
	 * @return True if node with the given value exists in the tree, false otherwise
	 */
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
		
		int comparison = key.compareTo(n.key);
		if (comparison < 0)
			return get(n.left, key);
		else if (comparison > 0)
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
		
		int comparison = key.compareTo(n.key);
		if (comparison < 0)
			n.left = delete(n.left, key);
		else if (comparison > 0)
			n.right = delete(n.right, key);
		else {
			if (n.right == null) return n.left;
			if (n.left == null)  return n.right;
			
			Node temp = min(n.right);
			delete(root, temp.key);
			n.key = temp.key;
			n.val = temp.val;
		}
		return n;
	}
	
	/* Finds the maximum node in the tree by key by going as far right as possible in 
	 * the tree. If the tree's root is null (tree is empty) returns null.*/
	private Node max(Node n) {
		if (n == null)			return null;
		if (n.right == null) 	return n;
		
		return max(n.right);
	}
	
	/**
	 * Method for getting the maximum key in the tree
	 * @return Maximum key in the tree
	 */
	public K maxKey() {
		Node temp = max(root);
		if (temp == null) return null;
		return temp.key;
	}
	
	/**
	 * Method for getting the value associated with the maximum key in the tree.
	 * @return Value associated to maximum key in the tree.
	 */
	public V maxVal() {
		Node temp = max(root);
		if (temp == null) return null;
		return temp.val;
	}
	
	
	/* Find the minimum node in the tree by key by going as far left as possible in
	 * the tree. IF the tree's root is null (tree is empty) returns null.*/
	private Node min(Node n) {
		if (n == null) 			return null;
		if (n.left == null) 	return n;
		
		return min(n.left);
	}
	
	/**
	 * Public method for getting the minimum key in the tree
	 * @return Minimum key in the tree
	 */
	public K minKey() {
		Node temp = min(root);
		if (temp == null) return null;
		return temp.key;
	}
	
	/**
	 * Method for getting the value associated with the minimum key in the tree.
	 * @return Value associated to minimum key in the tree.
	 */
	public V minVal() {
		Node temp = min(root);
		if (temp == null) return null;
		return temp.val;
	}
	
	/**
	 * Method inverts the tree (turns the tree into a mirror image of itself).
	 * Result is no longer valid as a binary search tree due to violation of node ordering.
	 */
	public void invert() {
		invert(root);
	}
	
	private Node invert(Node n) {
		if (n == null) return null;
		
		Node left = n.left;
		Node right = n.right;
		
		invert(n.left);
		invert(n.right);
		
		n.left = right;
		n.right = left;
		
		return n;
	}
	
	/**
	 * Verifies that tree is a valid BST.
	 * @return True if tree is a valid BST, false otherwise.
	 */
	public boolean isBST() {
		K min = minKey();
		K max = maxKey();
		return isBST(root, min, max);
	}
	
	private boolean isBST(Node n, K min, K max) {
		if (n == null) return true;
		
		int cmpMin = n.key.compareTo(min);
		int cmpMax = n.key.compareTo(max);
		if (cmpMin < 0 || cmpMax > 0) return false;
		
		return isBST(n.left, min, n.key) && isBST(n.right, n.key, max);
	}
	
	/**
	 * Checks if the bst is a full tree (all nodes have either 0 or 2 children)
	 * @return True if the tree is full, false otherwise
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
	
	/**
	 * Checks if the bst is complete (all levels but the last are entirely 
	 * filled, and the nodes in the last level are as far left as possible)
	 * @return True if tree is complete, false otherwise
	 */
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
	 * Performs a pre-order (root-left-right) traversal of the tree
	 * @return String of tree in pre-order
	 */
	public String preOrder() {
		if (this.root == null) return "";
		String output = preOrder(root);
		return output.substring(0, output.length() - 1);
	}
	
	private String preOrder(Node n) {
		if (n == null) return "";
		
		String output = "";
		output += n.toString();
		output += preOrder(n.left);
		output += preOrder(n.right);
		
		return output;
	}
	
	/**
	 * Performs an in-order (left-root-right) traversal of the tree
	 * @return String of tree in in-order
	 */
	public String inOrder() {
		if (this.root == null) return "";
		String output = inOrder(root);
		return output.substring(0, output.length() - 1);
	}
	
	private String inOrder(Node n) {
		if (n == null) return "";
		
		String output = "";
		output += inOrder(n.left);
		output += n.toString();
		output += inOrder(n.right);
		
		return output;
	}
	
	/**
	 * Performs a post-order (left-right-root) traversal of the tree
	 * @return String of tree in post-order
	 */
	public String postOrder() {
		if (this.root == null) return "";
		String output = postOrder(root);
		return output.substring(0, output.length() - 1);
	}
	
	private String postOrder(Node n) {
		if (n == null) return "";
		
		String output = "";
		output += postOrder(n.left);
		output += postOrder(n.right);
		output += n.toString();
		
		return output;
	}
	
	/**
	 * Performs a reverse-order (right-root-left) traversal of the tree
	 * @return String of tree in reverse-order
	 */
	public String reverseOrder() {
		if (this.root == null) return "";
		String output = reverseOrder(root);
		return output.substring(0, output.length() - 1);
	}
	
	private String reverseOrder(Node n) {
		if (n == null) return ""; 
		
		String output = "";
		output += reverseOrder(n.right);
		output += n.toString();
		output += reverseOrder(n.left);
		
		return output;
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
		return (1 + Math.min(minHeight(n.left), minHeight(n.right)));
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
	 * Gets the number of internal nodes in the tree
	 * @return Number of nodes in the tree having at least one child
	 */
	public int numInternal() {
		return numInternal(root);
	}
	
	private int numInternal(Node n) {
		if (n == null) 						   return 0;
		if (n.left == null && n.right == null) return 0;
		return 1 + (numInternal(n.left) + numInternal(n.right));
	}
	
	/**
	 * Clears the entire tree.
	 */
	public void clear() {
		root = null;
	}
}
