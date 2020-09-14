package linkedlist;

import java.util.Set;
import java.util.NoSuchElementException;

/**
 * Linked List class written by Isaac C. Dubrawsky as a personal exercise and to demonstrate mastered skill.
 * @author Isaac Dubrawsky
 * @since 30/08/2020
 * @param <V> The type of values to be stored in the list. These
 * 			  values must implement Comparable, as the compareTo() method
 * 			  is used to determine ordering in certain operations, such as sorting.
 */

public class LinkedList<V extends Comparable<V>> {

	public Node head;
	
	private int numElements;
	
	/**
	 * Node class for linked list. Every node stores a piece
	 * of data and a reference to the next node in the list.
	 */
	public class Node {
		public V val;
		public Node next;
		
		/**
		 * Creates a new node with the provided value.
		 * @param val Value new node will store.
		 */
		public Node(V val) {
			this.val = val;
			this.next = null;
		}
		
		/**
		 * Creates a new node with the provided value
		 * and points to the provided node.
		 * @param val Value new node will store.
		 * @param next Node this new node will point to.
		 */
		public Node(V val, Node next) {
			this.val = val;
			this.next = next;
		}
		
		@Override
		public String toString() {
			return "(" + this.val + ")->";
		}
	}
	
	/**
	 * Creates a new linked list with no nodes. Basic constructor.
	 */
	public LinkedList() {
		this.head = null;
		this.numElements = 0;
	}
	
	/**
	 * Creates a linked list with an initial node.
	 * @param val Value to use as the first node in a new list.
	 */
	public LinkedList(V val) {
		this.head = new Node(val);
		this.numElements = 1;
	}
	
	/**
	 * Creates a new linked list using values stored in an array.
	 * @param values Array from whose stored values a list will be created.
	 */
	public LinkedList(V[] values) {
		this.numElements = 0;
		for (V val : values) {
			this.add(val);
		}
	}
	
	/**
	 * Creates a new linked list using values stored in a set.
	 * @param set Set from whose stored values a list will be created.
	 */
	public LinkedList(Set<V> set) {
		this.numElements = 0;
		for (V val : set) {
			this.add(val);
		}
	}
	
	/**
	 * Returns the length of the list. Like arrays, a list with
	 * 10 elements has a max index of 9.
	 * @return Length of list.
	 */
	public int length() {
		return numElements;
	}
	
	/**
	 * Creates new node at the head of the list with the provided value.
	 * This value will now be the list new head.
	 * @param val Value to be added to the head of the list
	 */
	public void add(V val) {
		//If the list is empty, create a new node to be the head
		if (this.head == null)
			this.head = new Node(val);
		//Otherwise store the head temporarily, then create a new head with the temp as it's next
		else {
			Node temp = head;
			this.head = new Node(val);
			this.head.next = temp;
		}
		
		//Increment size
		this.numElements++;
	}
	
	/**
	 * Creates a new node at the end of the list with the provided value
	 * @param val Value to be added to end of list
	 */
	public void append(V val) {
		//If the list is empty, add normally
		if (head == null)
			head = new Node(val);
		//Otherwise
		else {
			//Move along list until the end is reached, then add a new node
			Node current = head; 
			while (current.next != null) {
				current = current.next;
			}

			current.next = new Node(val);
		}
		
		//Increment size
		numElements++;
	}
	
	/**
	 * Inserts the provided value into the list at the provided index.
	 * Pushes the index of every node on [index, end of list] back by one.
	 * @param val Value to insert to the list
	 * @param index Index in list value will be inserted to
	 */
	public void insertAt(V val, int index) throws IndexOutOfBoundsException {
		
		//May only insert at valid indices
		if (index < 0 || index > numElements)
			throw new IndexOutOfBoundsException();
		
		//If the head is null
		if (head == null || index == 0)
			add(val);
		//Otherwise
		else {
			int N = 0;							//Counter variable for current's index
			Node current = head;				//Temporary variable to move along list

			//Move along list; break if the end of the list is reached or the proper index is the next element
			while (current.next != null && N != index - 1) {
				current = current.next;
				N++;
			}

			//Insert to correct index
			if (current.next != null) {
				Node temp = current.next;
				current.next = new Node(val);
				current.next.next = temp;
			}
			
			else {
				current.next = new Node(val);
			}
			
			//Increment the size
			numElements++;
		}
	}
	
	/**
	 * Deletes the provided value from the list
	 * @param val Value to delete from the list
	 */
	public void delete(V val) throws NoSuchElementException {
		
		//If the list is empty
		if (head == null) throw new NoSuchElementException();
		
		//If the item to delete is the first node
		if (head.val == val) {
			head = head.next;
			numElements--;
			return;
		}
		
		//Otherwise continue down the list
		Node current = head.next;
		Node parent = head;
		
		while (current != null) {
			//Delete if it is found
			if (current.val == val) {
				parent.next = current.next;
				numElements--;
				return;
			}
			
			//Move to next item in list;
			current = current.next;
			parent = parent.next;
		} 
		
		//End of list has been reached without finding the item
		return;
	}
	
	/**
	 * Checks if the list contains a given value
	 * @param val Value being checked for in the list
	 * @return True if value is stored in the list, false otherwise
	 */
	public boolean contains(V val) {
		Node current = head;
		
		while (current != null && current.val != val) {
			current = current.next;
		}
		
		if (current == null)
			return false;
		if (current.val == val)
			return true;
		return false;
	}
	
	/**
	 * Retrieves the value stored at the provided index if it exits.
	 * Throws an exception if the index does not exist in the list
	 * @param index Index of value to retrieve
	 * @throws NoSuchElementException for indices less than 0 or greater than the list's length
	 * @return The value at the provided index
	 */
	public V get(int index) throws NoSuchElementException {
		//Throw exceptions if the index is invalid
		if (head == null)				 throw new NoSuchElementException();
		if (index < 0)					 throw new NoSuchElementException();
		if (index >= this.numElements)   throw new NoSuchElementException();
		
		int N = 0;
		Node current = head;
		
		while (current != null && N < index) {
			current = current.next;
			N++;
		}
		
		//Avoid null pointer exceptions
		if (current != null) 
			return current.val;
		
		//If for some strange unknowable current is null but the index was valid, throw exception
		throw new NoSuchElementException();
	}
	
	/**
	 * Sorts the entire list by creating a new node to serve as the head of a new list.
	 * It then proceeds down the original (unsorted) list, adding each element to
	 * the appropriate place in the new (sorted) list. Then sets this list's head
	 * to the head of the new list.
	 */
	public void sort() {
		if (numElements <= 1)
			return;
		
		Node newList = new Node(this.head.val);
		Node originalCur = this.head.next;
		
		while (originalCur != null) {
			if (originalCur.val.compareTo(newList.val) <= 0)
				newList = new Node(originalCur.val, newList);
			else {
				Node newCur = newList;
				while (newCur.next != null && originalCur.val.compareTo(newCur.next.val) > 0)
					newCur = newCur.next;
				if (newCur.next == null)
					newCur.next = new Node(originalCur.val);
				else {
					Node temp = newCur.next;
					newCur.next = new Node(originalCur.val, temp);
				}
			}
			originalCur = originalCur.next;
		}
		this.head = newList;
	}
	
	/**
	 * Reverses the current list by taking each node and 
	 * flipping the direction its 'next' data field points. 
	 */
	public void reverse() {
		if (numElements <= 1) return;
				
		Node prev = null;
		Node current = head;
		Node next = null;
		
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		
		head = prev;
	}
	
	/**
	 * Resets the entire list by setting the head to
	 * null and the current size to 0.
	 */
	public void clear() {
		this.head = null;
		this.numElements = 0;
	}
	
	
	/**
	 * The entire list is returned in the form (node)->(node)->(node).
	 * The final node will not have an arrow following it.
	 * If the list is empty, it will instead return a message stating so.
	 * @return String representation of the list in form (node)->(node)->(node)
	 */
	@Override
	public String toString() {
		if (head == null)
			return "List is empty";
		
		String output = "";
		Node current = head;
		
		while (current != null) {
			output += current.toString();
			current = current.next;
		}
		
		return output.substring(0, output.length() - 2);
	}
}
