package binaryheaps;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

public class Tests {
	
	//Creates a minHeap for testing purposes. Heap order is: 20 | 30 | 40 | 70 | 50 | 60
	public MinHeap<Integer> createMinHeap() {
		MinHeap<Integer> minHeap = new MinHeap<Integer>();
		minHeap.offer(60);
		minHeap.offer(70);
		minHeap.offer(50);
		minHeap.offer(30);
		minHeap.offer(20);
		minHeap.offer(40);
		
		return minHeap;
	}
	
	//Tests the offer() method for proper addition to the heap and element reordering and the size() method
	@Test
	public void testOfferSize() {
		MinHeap<Integer> minHeap = new MinHeap<Integer>();

		assertEquals(0, minHeap.size());
		assertTrue(minHeap.isEmpty());
		
		minHeap.offer(50);
		minHeap.offer(20);
		minHeap.offer(30);
		minHeap.offer(70);
		minHeap.offer(40);
		
		ArrayList<Integer> correctHeap = new ArrayList<Integer>(Arrays.asList(20, 40, 30, 70, 50));
		ArrayList<Integer> testHeap = minHeap.getHeap();
	
		assertEquals(correctHeap.size(), testHeap.size());
		assertEquals(testHeap.size(), minHeap.size());
		assertTrue(correctHeap.equals(testHeap));
	}
	
	//Tests if the contains method returns true if the element exists, and false if it doesn't
	@Test
	public void testContains() {
		MinHeap<Integer> heap = createMinHeap();
		
		assertTrue(heap.size() == 6);
		assertTrue(heap.contains(40));
		assertTrue(heap.contains(70));
		assertTrue(heap.contains(60));
		assertFalse(heap.contains(10));
		assertFalse(heap.contains(90));
	}
	
	//Tests if the isEmpty function returns true if the heap is empty, false otherwise
	@Test
	public void testEmpty() {
		MinHeap<Integer> heap = new MinHeap<Integer>();
		
		assertTrue(heap.isEmpty());
		
		heap = createMinHeap();
		
		assertFalse(heap.isEmpty());
	}

	//Tests if the peek method is properly returning the top element of the heap
	@Test
	public void testPeek() {
		MinHeap<Integer> heap = new MinHeap<Integer>();
		
		assertNull(heap.peek());
		
		heap = createMinHeap();
		assertEquals(20, (int)heap.peek());
		
		heap.poll();
		assertEquals(30, (int)heap.peek());
	}
	
	//Test the poll function for proper removal from the heap and reordering of remaining elements
	@Test
	public void testPoll() {
		MinHeap<Integer> heap = new MinHeap<Integer>();
		
		assertNull(heap.poll());
		
		heap = createMinHeap();
		
		assertEquals(6, heap.size());
		assertTrue(20 == heap.poll());
		assertEquals(5, heap.size());
		assertEquals(heap.peek(), heap.poll());
		assertEquals(4, heap.size());
		assertTrue(40 == heap.poll());
		assertEquals(3, heap.size());
		assertTrue(50 == heap.poll());
		assertTrue(60 == heap.poll());
		assertTrue(70 == heap.poll());
		assertTrue(heap.isEmpty());
	}
	
	//Tests the clear() method to ensure proper reseting of the heap
	@Test
	public void testClear() {
		MinHeap<Integer> heap = createMinHeap();
		
		assertEquals(6, heap.size());
		assertNotNull(heap.peek());
		
		heap.clear();
		
		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());
		assertNull(heap.peek());
		assertNull(heap.poll());
	}
	
	//Tests the toString() method, which lists the elements in the order they are stored in the ArrayList
	@Test
	public void testToString() {
		MinHeap<Integer> heap = new MinHeap<Integer>();
		
		assertEquals("", heap.toString());
		
		heap = createMinHeap();
		String correct = "20: 30: 40: 70: 50: 60";
		assertEquals(correct, heap.toString());
		
		heap.poll();
		correct = "30: 50: 40: 70: 60";	
		assertEquals(correct, heap.toString());
	}
	
	/*
	 * First test of the removeValue() method. The method removes the given element from
	 * the heap if it exists. This test checks the scenarios where after removing the element,
	 * the element that initially replaces it before reordering only needs to be sifted down 
	 * the heap (it is larger than all elements above it).
	 */
	@Test
	public void testRemoveByValue() {
		MinHeap<Integer> heap = createMinHeap();
		heap.offer(10);
		heap.offer(80);
		ArrayList<Integer> heapArray = heap.getHeap();
		
		assertEquals(8, heap.size());
		assertEquals(8, heapArray.size());
		assertEquals(20, (int) heapArray.get(2));
		assertFalse(heap.removeValue(90));
		
		heap.removeValue(20);
		assertEquals(7, heap.size());
		String correct = "10: 30: 40: 70: 50: 60: 80";
		assertEquals(correct, heap.toString());
		
		heap.removeValue(30);
		assertEquals(6, heap.size());
		correct = "10: 50: 40: 70: 80: 60";
		assertEquals(correct, heap.toString());
	}
	
	/*
	 * Second test of the removeValue() method. This test checks the scenario where after removing
	 * the element, the element that initially replaces it before reordering needs to be sifted up
	 * BEFORE it is sifted down (it is smaller than at least one the elements above it, and must
	 * be swapped up before it can be sifted down).
	 */
	@Test
	public void testRemoveByValue2() {
		MinHeap<Integer> heap = new MinHeap<Integer>();
		
		heap.offer(1);
		heap.offer(9);
		heap.offer(22);
		heap.offer(17);
		heap.offer(11);
		heap.offer(33);
		heap.offer(27);
		heap.offer(21);
		heap.offer(19);
		heap.removeValue(33);
		
		String correct = "1: 9: 19: 17: 11: 22: 27: 21";
		assertEquals(correct, heap.toString());
		assertEquals(8, heap.size());
	}

}
