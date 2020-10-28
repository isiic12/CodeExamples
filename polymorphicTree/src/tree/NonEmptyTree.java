package tree;

import java.util.Collection;

/**
 * This class represents a non-empty search tree. An instance of this class
 * should contain:
 * <ul>
 * <li>A key
 * <li>A value (that the key maps to)
 * <li>A reference to a left Tree that contains key:value pairs such that the
 * keys in the left Tree are less than the key stored in this tree node.
 * <li>A reference to a right Tree that contains key:value pairs such that the
 * keys in the right Tree are greater than the key stored in this tree node.
 * </ul>
 *  
 */
 public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	/* Provide whatever instance variables you need */
	private Tree<K,V> left, right;
	private K key;
	private V value;

	/**
	 * Only constructor we need.
	 * @param key
	 * @param value
	 * @param left
	 * @param right
 */
	public NonEmptyTree(K key, V value, Tree<K,V> left, Tree<K,V> right) { 
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}

	/*
	 * Searches the tree for a node with the correct key and then returns
	 * the corresponding value
	 */
	public V search(K key) {
		
		int cmp = key.compareTo(this.key);
		
		if (cmp == 0)
			return this.value;
		else if (cmp < 0)
			return this.left.search(key);
		else
			return this.right.search(key);
	}
	
	/*
	 * Finds the proper place for the node based on key order
	 * (uses the key's compareTo method to determine precedence)
	 */
	public NonEmptyTree<K, V> insert(K key, V value) {
		int cmp = key.compareTo(this.key);
		
		//Belongs to the left of this node
		if (cmp < 0) 
			this.left = left.insert(key, value);
		//This node has the same key (update value)
		else if (cmp == 0)
			this.value = value;
		//Belongs to the right of this node
		else 
			this.right = right.insert(key, value);
		
		return this;
	}
	
	/*
	 * Deletes node from tree by first finding the node with the correct key
	 * Then tries replacing it with the minimum node of the right subtree
	 * before deleting that minimum node.
	 * If no right subtree exists, tries replacing it with the maximum node
	 * of the left subtree before deleting that maximum node.
	 * If the node has neither right nor left subtrees, returns the SINGLETON
	 * instance of the EmptyTree
	 */
	public Tree<K, V> delete(K key) {
		
		int cmp = key.compareTo(this.key);
		
		//Work down the tree to the node with the correct key
		if (cmp < 0)
			left = this.left.delete(key);
		else if (cmp > 0)
			right = this.right.delete(key);
		else {
			//First try looking for minimum of right subtree
			try {
				//Set this key/value to min right key/value, then delete min right
				this.key = this.right.min();
				this.value = this.right.search(this.key);
				this.right = this.right.delete(this.key);
			}
			//If there is no right subtree
			catch(TreeIsEmptyException noRightMin) {
				//Try looking for maximum of left subtree
				try {
					//Set this key/value to max left key/value, then delete max left
					this.key = this.left.max();
					this.value = this.left.search(this.key);
					this.left = this.left.delete(this.key);
				} 
				//If there is no left subtree as well, return an empty tree
				catch(TreeIsEmptyException noLeftMax) {
					return EmptyTree.getInstance();
				}
			}
		}
		return this;
	}

	/* Works down the right side of the tree element by element
	 * If the element throws an exception, it is an empty tree (last node)
	 * If the next node threw an exception, return this node's key
	 */
	public K max() {
		try {
			return right.max();
		} catch (TreeIsEmptyException e) {
			return key;
		}
	}

	/*
	 * Works down the left side of the tree element by element
	 * If the element throws an exception, it is an empty tree (last node)
	 * If the next node threw an exception, return this node's key
	 */
	public K min() {
		try {
			return left.min();
		} catch (TreeIsEmptyException e) {
			return key;
		}
	}

	/*
	 * Count this node (1) and add the size of left and right subtrees
	 */
	public int size() {
		return (1 + left.size() + right.size());
	}

	public void addKeysToCollection(Collection<K> c) {
		left.addKeysToCollection(c);
		c.add(key);
		right.addKeysToCollection(c);
	}
	
	public Tree<K,V> subTree(K fromKey, K toKey) {
		Tree<K, V> sT = EmptyTree.getInstance();
		
		int compare1 = this.key.compareTo(fromKey);			//Is >= 0 if it is less than this key
		int compare2 = this.key.compareTo(toKey);			//Is <= 0 if it is greater than this key
		
		//If this node is within the given range (inclusive)
		if ((compare1 >= 0) && (compare2 <= 0)) {
			sT = new NonEmptyTree<K, V>(key, value, this.left.subTree(fromKey, toKey),
											  		this.right.subTree(fromKey, toKey));
		}
		
		//If this node's key is below the range, head right
		else if (compare1 < 0) {
			return this.right.subTree(fromKey, toKey);
		}
		
		//If this node's key is above the range, head left
		else if (compare2 > 0) {
			return this.left.subTree(fromKey, toKey);
		}
		
		return sT;
	}

	
	/*
	 * Return 1 (the edge leading from this node to 
	 * the next) + the larger height (left or 
	 * right). If the next node is an EmptyTree,
	 * it will return -1 so as not to count the edge
	 * leading to it
	 */
	public int height() {
		return 1 + Math.max(this.left.height(), this.right.height());
	}
	
	/*
	 * The performTask method adds a key-value pair to separate 
	 * arrayLists. This calls that method to add them in an 
	 * inOrder traversal order, going left-this-right
	 */
	public void inorderTraversal(TraversalTask<K,V> p) {
		this.left.inorderTraversal(p);
		p.performTask(key, value);
		this.right.inorderTraversal(p);
	}
	
	/*
	 * The performTask method adds a key-value pair to separate
	 * arrayLists. This calls that method to add them in a
	 * REVERSE inOrder traversal order (right-this-left)
	 */
	public void rightRootLeftTraversal(TraversalTask<K,V> p) {
		this.right.rightRootLeftTraversal(p);
		p.performTask(key, value);
		this.left.rightRootLeftTraversal(p);
	}
	
	
	public String toString() {
		return "Key: " + key + "\nValue: " + value;
	}
}
