package binaryheaps;

public interface Heap<V extends Comparable<V>> {
	
	/**
	 * Adds the given value to the heap
	 * @param val Value to be added
	 */
	public void offer(V val);
	
	/**
	 * Clears the entire heap
	 */
	public void clear();
	
	/**
	 * Checks if heap contains the given value
	 * @param val Value to search for in the heap
	 * @return True if heap contains the value, false otherwise
	 */
	public boolean contains(V val);
	
	/**
	 * Gets the value at the top of the heap without removing it
	 * @return Value stored at the top of the heap
	 */
	public V peek();
	
	/**
	 * Gets the value at the top of the heap and removes it
	 * @return Value stored at the top of the heap
	 */
	public V poll();
	
	/**
	 * Removes the given value from the heap
	 * @param val Value to be removed from heap
	 * @return True if value was in the heap and removed, false otherwise
	 */
	public boolean removeValue(V val);
	
	/**
	 * Checks if the heap is empty or not
	 * @return True if the heap is empty, false otherwise
	 */
	public boolean isEmpty();
	
	/**
	 * Gets the number of elements in the heap
	 * @return Size of the heap
	 */
	public int size();
	
	/**
	 * Creates a string representation of the heap
	 * @return String representation of the heap
	 */
	public String toString();
}
