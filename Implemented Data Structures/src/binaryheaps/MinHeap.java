package binaryheaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.Stack;
import java.util.Queue;
import java.util.Collections;
import java.util.NoSuchElementException;

public class MinHeap<V extends Comparable<V>> implements Heap<V> {

	private ArrayList<V> heap;
	
	public MinHeap() {
		heap = new ArrayList<V>();
	}
	
	public MinHeap(V[] values) {
		this();		
		for (V val : values) {
			this.offer(val);
		}
	}
	
	public MinHeap(ArrayList<V> values) {
		this();
		for (V val : values) {
			this.offer(val);
		}
	}
	
	public MinHeap(Set<V> values) {
		this();
		for (V val : values) {
			this.offer(val);
		}
	}
	
	public MinHeap(Stack<V> values) {
		 this();
		 while (!values.isEmpty()) {
			 this.offer(values.pop());
		 }
	}
	
	public MinHeap(Queue<V> values) {
		this();
		while (!values.isEmpty()) {
			this.offer(values.poll());
		}
	}
	
	/**
	 * Adds the new value to the heap. Does so by first inserting it
	 * at the very last index of the arrayList, then swapping it up
	 * as long as it is considered less than its parent element.
	 */
	public void offer(V val) {
		//Add new value to end of the list
		heap.add(val);
		
		//Get new element's index
		int index = heap.size() - 1;
		
		//Store parent index and comparison values in variable to increase readability
		int parentIndex = this.parent(index);
		int cmp = heap.get(index).compareTo(heap.get(parentIndex));
		
		//Move new value to correct index by continually swapping with its parent until it is no longer greater
		while (index > 0 && cmp < 0) {
			this.swap(index, parentIndex);
			index = parentIndex;
			parentIndex = this.parent(index);
			cmp = heap.get(index).compareTo(heap.get(parentIndex));
		}
	}
	
	/**
	 * Clears the heap of all values by setting each element to
	 * null, then removing all null values from the list.
	 */
	public void clear() {
		while (heap.size() > 0) {
			heap.remove(0);
		}
		//heap.forEach(val -> val = null);
		//heap.removeAll(Collections.singleton(null));
	}
	
	/**
	 * Checks if the heap contains the given value
	 */
	public boolean contains(V val) {
		for (V value : heap) {
			if (value.equals(val))
				return true;
		} return false;
	}
	
	/**
	 * Returns the value currently stored at the top of 
	 * the heap (the minimum value in the heap).
	 */
	public V peek() {
		if (heap.size() <= 0) return null;
		return heap.get(0);
	}
	
	/**
	 * Removes and returns the value stored at the top of 
	 * the heap (the minimum value in the heap). It does so by
	 * storing the value, then replacing it in the arrayList with
	 * the very last element in the heap, this shrinking the arrayList
	 * by one. The element is then sifted down the heap using the
	 * heapify() method.
	 */
	public V poll() {
		//If the heap is empty or size 1, removing the minimum value is easy
		if (heap.size() <= 0) return null;
		else if (heap.size() == 1) return heap.remove(0);
		
		/* 
		 * If the heap size > 1, we must store and remove the minimum value, then
		 * bring the last element to top of the heap, and then swim it back down
		 * to its proper position
		 */
		V minimum = heap.get(0);
		heap.set(0, heap.get(heap.size() - 1));
		heap.remove(heap.size() - 1);
		heapify(0);
		
		return minimum;
	}
	
	/**
	 * Removes the given value from the heap, if it exists in the heap, and
	 * returns false if it doesn't. It does so by overwriting the element with
	 * the very last value in the heap, then shrinking the arrayList by one, then
	 * fixing the heap order. The new value at the element's index is shifted up
	 * as long as it is less than its parent element, then sifted down using the
	 * heapify() method.
	 */
	public boolean removeValue(V val) {
		if (!heap.contains(val)) return false;
		
		int valueIndex = heap.indexOf(val);
		heap.set(valueIndex, heap.get(heap.size() - 1));
		heap.remove(heap.size() - 1);
		
		int parentIndex = parent(valueIndex);
		while (heap.get(valueIndex).compareTo(heap.get(parentIndex)) < 0) {
			swap(valueIndex, parentIndex);
			valueIndex = parentIndex;
			parentIndex = parent(valueIndex);
		}
		
		int cmpLeft = heap.get(valueIndex).compareTo(heap.get(leftChild(valueIndex)));
		int cmpRight = heap.get(valueIndex).compareTo(heap.get(rightChild(valueIndex)));
		
		if (cmpLeft > 0 || cmpRight > 0) {
			heapify(valueIndex);
		}
		
		return true;
	}
	
	/**
	 * Checks if the heap is currently empty
	 */
	public boolean isEmpty() {
		return heap.size() == 0;
	}
	
	/**
	 * Gets the number of elements in the heap
	 */
	public int size() {
		return heap.size();
	}
	
	/**
	 * Returns the arrayList that stores all the values in the heap.
	 * @return The heap ArrayList
	 */
	public ArrayList<V> getHeap() {
		@SuppressWarnings("unchecked")
		ArrayList<V> heapClone = (ArrayList<V>) heap.clone();
		return heapClone;
	}
	
	/**
	 * Creates a string representation of the heap
	 */
	@Override
	public String toString() {
		if (heap.isEmpty()) return "";
		
		String output = "";
		for (V val : heap) {
			output += val + ": ";
		}	
		return output.substring(0, (output.length() - 2));
	}
	
	//Index of left child of element is [element's index * 2]
	private int leftChild(int i) {
		return 2*i + 1;
	}
	
	//Index of right child of element is [(element's index * 2) + 1]
	private int rightChild(int i) {
		return 2*i + 2;
	}
	
	//Index of parent of element is always (element's index / 2) - indices are ints, so are automatically floored
	private int parent(int i) {
		return((i - 1)/ 2);
	}
	
	private void heapify(int index) {
		//Get the indices of the left and right children
		int left = leftChild(index);
		int right = rightChild(index);
		int smallest;
		
		//Determine whether the left child is smaller than the current element or not
		if (left < heap.size() && heap.get(left).compareTo(heap.get(index)) < 0) {
			smallest = left;
		} else {
			smallest = index;
		}
		
		//Determine if the right child is smaller than both the current element or the left child
		if (right < heap.size() && heap.get(right).compareTo(heap.get(smallest)) < 0) {
			smallest = right;
		}
		
		/* 
		 * If one of the children is smaller than the current element, swap 
		 * the two elements, then recurse. This continuously swims the element
		 * down the heap to its proper position.
		 */
		if (smallest != index) {
			swap(index, smallest);
			heapify(smallest);
		}
	}
	
	/* Swaps the elements at the two given indices if possible. If either
	 * index is invalid, throws a NoSuchElementException
	 */
	private void swap(int index1, int index2) throws NoSuchElementException {
		if (index1 < 0 || index2 < 0) throw new NoSuchElementException();
		if (index1 < heap.size() && index2 < heap.size()) {
			V temp = heap.get(index1);
			heap.set(index1, heap.get(index2));
			heap.set(index2, temp);
		} else { 
			throw new NoSuchElementException();
		}
	}
}
