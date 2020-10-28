package tree;

import java.util.Collection;

/**
 * This class is used to represent the empty search tree: a search tree that
 * contains no entries.
 * 
 * This class is a singleton class: since all empty search trees are the same,
 * there is no need for multiple instances of this class. Instead, a single
 * instance of the class is created and made available through the static field
 * SINGLETON.
 * 
 * The constructor is private, preventing other code from mistakenly creating
 * additional instances of the class.
 *  
 */
 public class EmptyTree<K extends Comparable<K>,V> implements Tree<K,V> {
	/**
	 * This static field references the one and only instance of this class.
	 * We won't declare generic types for this one, so the same singleton
	 * can be used for any kind of EmptyTree.
	 */
	private static EmptyTree SINGLETON = new EmptyTree();


	public static  <K extends Comparable<K>, V> EmptyTree<K,V> getInstance() {
		return SINGLETON;
	}

	/**
	 * Constructor is private to enforce it being a singleton
	 *  
	 */
	private EmptyTree() {
		// Nothing to do
	}
	
	/*
	 * Reaching a EmptyTreeNode while searching means no such
	 * node with the requested key exists in the tree. Returns null.
	 */
	public V search(K key) {
		return null;
	}
	
	/*
	 * Inserting at a EmptyTree node will create a new NonEmptyTree
	 * node with the provided values and SINGLETON as both children
	 */
	public NonEmptyTree<K, V> insert(K key, V value) {
		return new NonEmptyTree(key, value, EmptyTree.getInstance(), EmptyTree.getInstance());
	}

	public Tree<K, V> delete(K key) {
		return SINGLETON;
	}
	
    /*
     * Throws an exception to signal to the previous node
     * this node is empty. Previous node must be the 
     * maximum (see max() method in NonEmptyTree.java) and should
     * return its key
     */
	public K max() throws TreeIsEmptyException {
		throw new TreeIsEmptyException();
	}

	/*
	 * Throws an exception to signal to previous node this node
	 * is empty. Previous node must therefore be the 
	 * minimum node (see min() method in NonEmptyTree.java) and
	 * should return its key
	 */
	public K min() throws TreeIsEmptyException {
		throw new TreeIsEmptyException();
	}

    //--STUDENT--
	/*
	 * EmptyTree is equivalent of null node (does not add to size)
	 * Therefore only returns 0
	 */
	public int size() {
		return 0;
	}

	public void addKeysToCollection(Collection<K> c) {
		return;
	}

	public Tree<K,V> subTree(K fromKey, K toKey) {
		return SINGLETON;
	}
	
	/*
	 * Returns 0 to avoid counting this node
	 */
	public int height() {
		return 0;
	}
	
	/*
	 * Do nothing (no key-value pair needs to be added to arrayLists
	 * using the performTask method).
	 */
	public void inorderTraversal(TraversalTask<K,V> p) {
		return;
	}
	
	/*
	 * Do nothing (no key-value pair needs to be added to arrayLists
	 * using the performTask method).
	 */
	public void rightRootLeftTraversal(TraversalTask<K,V> p) {
		return;
	}
	
	public String toString() {
		return "Empty Tree\n";
	}
}
