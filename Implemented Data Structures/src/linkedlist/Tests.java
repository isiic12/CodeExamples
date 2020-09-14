package linkedlist;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;

/*
 * Many of the tests use LinkedList.Node to check that all values in the list are correct, 
 * both in value and in position. However, since Node is a raw type, this brings up a warning
 * in Eclipse that it should be parameterized. However, since there is no need to parameterize
 * Nodes, I want to suppress these warnings as they are not needed. 
 */
@SuppressWarnings("all")
public class Tests {

	//Tests adding to the head of a list
	@Test
	public void testAdd() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();

		assertEquals(0, linkedList.length());
		assertNull(linkedList.head);

		linkedList.add(0);
		linkedList.add(1);

		assertEquals(2, linkedList.length());

		linkedList.add(0);
		linkedList.add(1);

		assertEquals(4, linkedList.length());
		//System.out.println("Test Add: " + linkedList.toString());

		int index = 0;
		int[] values = new int[] {1, 0, 1, 0};
		LinkedList.Node current = linkedList.head;

		while (current != null) {
			assertEquals(values[index++], current.val);
			current = current.next;
		}

		assertEquals(values.length, index);
	}

	//Tests appending to the end of an empty list
	@Test
	public void testAppend1() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();

		linkedList.append(0);
		linkedList.append(1);

		assertEquals(2, linkedList.length());
		assertTrue(0 == linkedList.head.val);
		assertTrue(1 == linkedList.head.next.val);

		linkedList.append(2);
		linkedList.append(3);
		linkedList.append(8);
		linkedList.append(5);

		assertEquals(6, linkedList.length());
		//System.out.println("Test Append1: " + linkedList.toString());

		int index = 0;
		int[] values = new int[] {0, 1, 2, 3, 8, 5};
		LinkedList.Node current = linkedList.head;

		while (current != null) {
			assertEquals(values[index++], current.val);
			current = current.next;
		}

		assertEquals(values.length, index);
	}

	//Tests appending to the end of a list
	@Test
	public void testAppend2() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();

		linkedList.add(5);
		linkedList.add(7);
		linkedList.append(4);

		assertTrue(linkedList.contains(7));
		assertTrue(linkedList.contains(5));
		assertTrue(linkedList.contains(4));

		//System.out.println("Test Append2: " + linkedList.toString());
		assertEquals(3, linkedList.length());

		int index = 0;
		int[] values = new int[] {7, 5, 4};
		LinkedList.Node current = linkedList.head;

		while (current != null) {
			assertEquals(values[index++], current.val);
			current = current.next;
		}

		assertEquals(values.length, index);
	}

	//Tests appending to the end of an empty list
	@Test
	public void testAppend3() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();

		linkedList.append(4);

		//System.out.println("Test Append 3: " + linkedList.toString());
		assertEquals(1, linkedList.length());
		assertTrue(4 == linkedList.head.val);
	}

	//Tests inserting in the middle of the list
	@Test
	public void testInsertAt1() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();

		linkedList.add(4);
		linkedList.add(3);
		linkedList.add(1);
		linkedList.add(0);
		linkedList.insertAt(2, 2);

		//System.out.println("Test insertAt1: " + linkedList.toString());
		assertTrue(linkedList.contains(2));
		assertEquals(5, linkedList.length());

		int index = 0;
		int[] values = new int[] {0, 1, 2, 3, 4};
		LinkedList.Node current = linkedList.head;

		while (current != null) {
			assertEquals(values[index++], current.val);
			current = current.next;
		}

		assertEquals(values.length, index);

	}

	//Tests inserting at an index less than 0
	@Test
	public void testInsertAt2() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();

		try {
			linkedList.insertAt(4, -1);
		} catch (IndexOutOfBoundsException e) {
			//System.out.println("Test InsertAt2 Exception 1 Caught");
			assertEquals(0, linkedList.length());
		}

		linkedList.add(4);
		linkedList.add(5);
		linkedList.add(6);

		try {
			linkedList.insertAt(10, -9);
		} catch (IndexOutOfBoundsException e) {
			//System.out.println("Test InsertAt2 Exception 2 Caught");
			assertEquals(3, linkedList.length());
		}

		//System.out.println("Test insertAt2: " + linkedList.toString());

		int index = 0;
		int[] values = new int[] {6, 5, 4};
		LinkedList.Node current = linkedList.head;

		while (current != null) {
			assertEquals(values[index++], current.val);
			current = current.next;
		}

		assertEquals(values.length, index);
	}

	//Tests inserting at index 0 (the head of the list)
	@Test
	public void testInsertAt3() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();

		linkedList.add(4);
		linkedList.add(5);
		linkedList.add(6);

		linkedList.insertAt(7, 0);

		//System.out.println("Test insertAt3: " + linkedList.toString());

		int index = 0;
		int[] values = new int[] {7, 6, 5, 4};
		LinkedList.Node current = linkedList.head;

		while (current != null) {
			assertEquals(values[index++], current.val);
			current = current.next;
		}

		assertEquals(values.length, index);
		assertTrue(7 == linkedList.head.val);
		assertTrue(6 == linkedList.head.next.val);
		assertEquals(4, linkedList.length());
	}

	//Tests inserting at the last index of the list
	@Test
	public void testInsertAt4() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();

		linkedList.add(4);
		linkedList.add(5);
		linkedList.add(6);

		linkedList.insertAt(3, 3);

		//System.out.println("Test insertAt4: " + linkedList.toString());

		int index = 0;
		int[] values = new int[] {6, 5, 4, 3};
		LinkedList.Node current = linkedList.head;

		while (current != null) {
			assertEquals(values[index++], current.val);
			current = current.next;
		}

		assertEquals(values.length, index);
	}

	//Tests inserting at an index greater than the length of the list (invalid input)
	@Test
	public void testInsertAt5() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();

		try {
			linkedList.insertAt(4, 1);
		} catch (IndexOutOfBoundsException e) {
			//System.out.println("Test InsertAt5 Exception 1 Caught");
			assertEquals(0, linkedList.length());
		}

		linkedList.add(4);
		linkedList.add(5);
		linkedList.add(6);

		try {
			linkedList.insertAt(10, 4);
		} catch (IndexOutOfBoundsException e) {
			//System.out.println("Test InsertAt5 Exception 2 Caught");
			assertEquals(3, linkedList.length());
		}

		//System.out.println("Test insertAt5: " + linkedList.toString());

		int index = 0;
		int[] values = new int[] {6, 5, 4};
		LinkedList.Node current = linkedList.head;

		while (current != null) {
			assertEquals(values[index++], current.val);
			current = current.next;
		}

		assertEquals(values.length, index);
	}

	//Tests deleting the first element in the list
	@Test
	public void testDeleteHead() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();

		linkedList.add(4);
		linkedList.add(3);
		linkedList.add(2);
		linkedList.add(1);
		linkedList.add(0);

		assertEquals(5, linkedList.length());

		linkedList.delete(0);

		//System.out.println("Test Delete Head: " + linkedList.toString());
		assertEquals(4, linkedList.length());
		assertTrue(1 == linkedList.head.val);

		int index = 0;
		int[] values = new int[] {1, 2, 3, 4};
		LinkedList.Node current = linkedList.head;

		while (current != null) {
			assertEquals(values[index++], current.val);
			current = current.next;
		}

		assertEquals(values.length, index);
	}

	//Tests deleting the last element in the list
	@Test
	public void testDeleteTail() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();

		linkedList.add(4);
		linkedList.add(3);
		linkedList.add(2);
		linkedList.add(1);
		linkedList.add(0);

		linkedList.delete(4);

		//System.out.println("Test Delete Tail: " + linkedList.toString());

		LinkedList.Node tailNode = linkedList.head;
		while (tailNode.next != null) {
			tailNode = tailNode.next;
		}

		assertEquals(4, linkedList.length());
		assertEquals(3, tailNode.val);

		int index = 0;
		int[] values = new int[] {0, 1, 2, 3};
		LinkedList.Node current = linkedList.head;

		while (current != null) {
			assertEquals(values[index++], current.val);
			current = current.next;
		}

		assertEquals(values.length, index);
	}

	//Test deleting a middle element of a list
	@Test
	public void testDeleteMiddle() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();

		linkedList.add(4);
		linkedList.add(3);
		linkedList.add(2);
		linkedList.add(1);
		linkedList.add(0);

		linkedList.delete(2);

		//System.out.println("Test Delete Middle: " + linkedList.toString());
		assertEquals(4, linkedList.length());

		int index = 0;
		int[] values = new int[] {0, 1, 3, 4};
		LinkedList.Node current = linkedList.head;

		while (current != null) {
			assertEquals(values[index++], current.val);
			current = current.next;
		}
	}

	//Tests multiple deletions from the same list
	@Test
	public void testDeleteMultiple() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();

		linkedList.add(7);
		linkedList.add(6);
		linkedList.add(5);
		linkedList.add(4);
		linkedList.add(3);
		linkedList.add(2);
		linkedList.add(1);
		linkedList.add(0);

		linkedList.delete(3);
		linkedList.delete(4);
		linkedList.delete(5);

		//System.out.println("Test Delete Multiple: " + linkedList.toString());
		assertEquals(5, linkedList.length());

		int index = 0;
		int[] values = new int[] {0 ,1, 2, 6, 7};
		LinkedList.Node current = linkedList.head;

		while (current != null) {
			assertEquals(values[index++], current.val);
			current = current.next;
		}
	}

	@Test
	public void testDeleteEmpty() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();

		assertEquals(0, linkedList.length());

		try {
			linkedList.delete(0);
		} catch (NoSuchElementException e) {
			//System.out.println("Test Delete Empty Exception Caught");
			assertEquals(0, linkedList.length());
		}
	}

	//Tests if the linked list contains given values
	@Test
	public void testContains() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();

		linkedList.add(8);
		linkedList.add(7);
		linkedList.add(6);
		linkedList.add(5);

		//System.out.println("Test Contains: " + linkedList.toString());

		assertEquals(4, linkedList.length());
		assertTrue(linkedList.contains(5));
		assertTrue(linkedList.contains(6));
		assertTrue(linkedList.contains(8));
	}

	//Tests if the get method returns the correct values for the corresponding indices
	@Test
	public void testGet1() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();

		linkedList.add(8);
		linkedList.add(7);
		linkedList.add(6);
		linkedList.add(5);

		assertTrue(8 == linkedList.get(3));
		assertTrue(5 == linkedList.get(0));
		assertTrue(7 == linkedList.get(2));
	}

	//Tests if the get method correctly throws exceptions for invalid input
	@Test
	public void testGet2() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();

		linkedList.add(8);
		linkedList.add(7);
		linkedList.add(6);
		linkedList.add(5);

		try {
			linkedList.get(-1);
		} catch (NoSuchElementException e) {
			//System.out.println("Test Get 2 Exception 1 Caught");
			assertEquals(4, linkedList.length());
		}

		try {
			linkedList.get(4);
		} catch (NoSuchElementException e) {
			//System.out.println("Test Get 2 Exception 2 Caught");
			assertEquals(4, linkedList.length());
		}
	}

	//Tests if the get method correctly throws exceptions when called on an empty list
	@Test
	public void testGetEmptyList() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();

		try {
			linkedList.get(0);
		} catch (NoSuchElementException e) {
			//System.out.println("Test Get Empty List Exception Caught");
			assertEquals(0, linkedList.length());
		}
	}

	//Tests if the list can sort itself correctly
	@Test
	public void testSort1() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();

		linkedList.add(-4);
		linkedList.add(2);
		linkedList.add(4);
		linkedList.add(7);
		linkedList.add(-2);
		linkedList.add(3);
		linkedList.add(5);
		linkedList.add(81);
		linkedList.add(1);
		linkedList.add(9);
		linkedList.add(0);

		linkedList.sort();
		//System.out.println("Test Sort 1:" + linkedList.toString());

		int index = 0;
		int[] values = new int[] {-4, -2, 0, 1, 2, 3, 4, 5, 7, 9, 81};
		LinkedList.Node current = linkedList.head;

		while (current != null) {
			assertEquals(values[index++], current.val);
			current = current.next;
		}

		assertEquals(values.length, index);
	}

	//Tests if the list can reverse itself correctly
	@Test
	public void testReverse() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();

		linkedList.add(3);
		linkedList.add(4);
		linkedList.add(5);
		linkedList.add(6);
		linkedList.add(7);
		//System.out.println("Test Reverse: " + linkedList.toString());

		linkedList.reverse();
		//System.out.println("Test Reverse: " + linkedList.toString());

		int index = 0;
		int[] values = new int[] {3, 4, 5, 6, 7};
		LinkedList.Node current = linkedList.head;

		while (current != null) {
			assertEquals(values[index++], current.val);
			current = current.next;
		}

		assertEquals(values.length, index);
	}

	//Tests if all the constructors work properly
	@Test
	public void testConstructors() {
		Integer[] listArray = new Integer[] {2, 7, -4, 20, 0};

		HashSet<Integer> hs = new HashSet<Integer>();
		hs.add(2);
		hs.add(7);
		hs.add(-4);
		hs.add(20);
		hs.add(0);


		LinkedList<Integer> listInitVal = new LinkedList<Integer>(5);
		LinkedList<Integer> listFromArray = new LinkedList<Integer>(listArray);
		LinkedList<Integer> listFromSet = new LinkedList<Integer>(hs);

		//System.out.println(listFromSet.toString());
		//System.out.println(listFromArray.toString());
		//System.out.println(listInitVal.toString());

		int index = 0;
		int[] values = new int[] {0, 20, -4, 7, 2};
		LinkedList.Node current = listFromArray.head;

		while (current != null) {
			assertEquals(values[index++], current.val);
			current = current.next;
		}

		assertEquals(values.length, index);

		assertEquals(1, listInitVal.length());
		assertTrue(5 == listInitVal.head.val);

		assertTrue(listFromSet.contains(2));
		assertTrue(listFromSet.contains(7));
		assertTrue(listFromSet.contains(-4));
		assertTrue(listFromSet.contains(20));
		assertTrue(listFromSet.contains(0));
		assertEquals(5, listFromSet.length());
	}

	//Tests the clear method after creating a list from an array.
	@Test
	public void testClear() {
		Integer[] listArray = new Integer[] {2, 7, -4, 20, 0};	
		LinkedList<Integer> listFromArray = new LinkedList<Integer>(listArray);

		assertEquals(5, listFromArray.length());

		listFromArray.clear();

		assertEquals(0, listFromArray.length());
		assertNull(listFromArray.head);
	}

}
