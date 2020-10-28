package tests;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;
import tree.*;
import junit.framework.TestCase;
import junit.framework.AssertionFailedError;

public class StudentTests extends TestCase {
	
	@Test
	public void testInsert() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		assertEquals(0, ptree.size());
		
		ptree.put(10, "Ten");
		ptree.put(5, "Five");
		ptree.put(20, "Twenty");
		ptree.put(25, "Twenty-five");
		ptree.put(15, "Fifteen");
		ptree.put(18, "Eighteen");
		ptree.put(16, "Sixteen");
		
		assertEquals(7, ptree.size());
		
		ptree.put(10, "New ten");
		assertEquals("New ten", ptree.get(10));
		assertEquals(5, ptree.height());
		
		assertNull(ptree.get(100));
	}
	
	
	@Test
	public void testInOrder() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		ptree.put(10, "Ten");
		ptree.put(5, "Five");
		ptree.put(20, "Twenty");
		ptree.put(25, "Twenty-five");
		ptree.put(15, "Fifteen");
		ptree.put(18, "Eighteen");
		ptree.put(16, "Sixteen");
		
		PlaceKeysValuesInArrayLists<Integer, String> task = new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.inorderTraversal(task);
		assertEquals(task.getKeys().toString(), "[5, 10, 15, 16, 18, 20, 25]");
		assertEquals(task.getValues().toString(), "[Five, Ten, Fifteen, Sixteen, Eighteen, Twenty, Twenty-five]");
	}
	
	@Test
	public void testRightRootLeft() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		ptree.put(10, "Ten");
		ptree.put(5, "Five");
		ptree.put(20, "Twenty");
		ptree.put(25, "Twenty-five");
		ptree.put(15, "Fifteen");
		ptree.put(18, "Eighteen");
		ptree.put(16, "Sixteen");
		
		PlaceKeysValuesInArrayLists<Integer, String> task = new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.rightRootLeftTraversal(task);
		assertEquals(task.getKeys().toString(), "[25, 20, 18, 16, 15, 10, 5]");
		assertEquals(task.getValues().toString(), "[Twenty-five, Twenty, Eighteen, Sixteen, Fifteen, Ten, Five]");
	}
	
	@Test
	public void testDeleteLeaf() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		ptree.put(10, "Ten");
		ptree.put(5, "Five");
		ptree.put(20, "Twenty");
		ptree.put(25, "Twenty-five");
		ptree.put(15, "Fifteen");
		ptree.put(18, "Eighteen");
		ptree.put(16, "Sixteen");
		
		ptree.remove(16);
		assertEquals(6, ptree.size());
		assertEquals(4, ptree.height());
		
		PlaceKeysValuesInArrayLists<Integer, String> task = new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.inorderTraversal(task);
		assertEquals(task.getKeys().toString(), "[5, 10, 15, 18, 20, 25]");
		assertEquals(task.getValues().toString(), "[Five, Ten, Fifteen, Eighteen, Twenty, Twenty-five]");
	}
	
	@Test
	public void testDeleteRoot1() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		ptree.put(10, "Ten");
		ptree.put(5, "Five");
		ptree.put(20, "Twenty");
		ptree.put(25, "Twenty-five");
		ptree.put(15, "Fifteen");
		ptree.put(18, "Eighteen");
		ptree.put(16, "Sixteen");
		
		ptree.remove(10);
		assertEquals(6, ptree.size());
		assertEquals(4, ptree.height());
		
		PlaceKeysValuesInArrayLists<Integer, String> task = new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.inorderTraversal(task);
		assertEquals(task.getKeys().toString(), "[5, 15, 16, 18, 20, 25]");
		assertEquals(task.getValues().toString(), "[Five, Fifteen, Sixteen, Eighteen, Twenty, Twenty-five]");
		System.out.println("Delete Root Test 1:\n" + ptree.getRoot() + "\n");
	}
	
	@Test
	public void testDeleteRoot2() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		ptree.put(20, "Twenty");
		ptree.put(10, "Ten");
		ptree.put(5, "Five");
		ptree.put(12, "Twelve");
		
		ptree.remove(20);
		
		assertEquals(3, ptree.size());
		assertEquals(3, ptree.height());
		System.out.println("Delete Root Test 2:\n" + ptree.getRoot() + "\n");
		
	}
	
	@Test
	public void testDeleteEmptyTree() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		assertEquals(0, ptree.size());
		assertEquals(0, ptree.height());
		
		ptree.remove(10);
		
		assertEquals(0, ptree.size());
		assertEquals(0, ptree.height());
		assertTrue(ptree.getRoot() instanceof EmptyTree);
	}
	
	@Test
	public void testGetSubtree() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		ptree.put(10, "Ten");
		ptree.put(5, "Five");
		ptree.put(20, "Twenty");
		ptree.put(25, "Twenty-five");
		ptree.put(15, "Fifteen");
		ptree.put(18, "Eighteen");
		ptree.put(16, "Sixteen");
		
		PolymorphicBST<Integer, String> subTree = ptree.subMap(10, 20);
		assertEquals(5, subTree.size());
		assertEquals(5, subTree.height());
		
		PlaceKeysValuesInArrayLists<Integer, String> task = new PlaceKeysValuesInArrayLists<Integer, String>();
		subTree.inorderTraversal(task);
		assertEquals(task.getKeys().toString(), "[10, 15, 16, 18, 20]");
		assertEquals(task.getValues().toString(), "[Ten, Fifteen, Sixteen, Eighteen, Twenty]");
		System.out.println("Get SubTree test 1:\n" + subTree.getRoot() + "\n");
	}
	
	@Test
	public void testGetSubtree2() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		ptree.put(10, "Ten");
		ptree.put(5, "Five");
		ptree.put(20, "Twenty");
		ptree.put(25, "Twenty-five");
		ptree.put(15, "Fifteen");
		ptree.put(18, "Eighteen");
		ptree.put(16, "Sixteen");
		
		PolymorphicBST<Integer, String> subTree = ptree.subMap(10, 17);
		assertEquals(3, subTree.size());
		assertEquals(3, subTree.height());
		
		PlaceKeysValuesInArrayLists<Integer, String> task = new PlaceKeysValuesInArrayLists<Integer, String>();
		subTree.inorderTraversal(task);
		assertEquals(task.getKeys().toString(), "[10, 15, 16]");
		assertEquals(task.getValues().toString(), "[Ten, Fifteen, Sixteen]");
		System.out.println("Get SubTree test 2:\n" + subTree.getRoot() + "\n");
	}
	
	@Test
	public void testGetSubtree3() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		ptree.put(10, "Ten");
		ptree.put(5, "Five");
		ptree.put(20, "Twenty");
		ptree.put(25, "Twenty-five");
		ptree.put(15, "Fifteen");
		ptree.put(18, "Eighteen");
		ptree.put(16, "Sixteen");
		
		PolymorphicBST<Integer, String> subTree = ptree.subMap(11, 22);
		assertEquals(4, subTree.size());
		assertEquals(4, subTree.height());
		
		PlaceKeysValuesInArrayLists<Integer, String> task = new PlaceKeysValuesInArrayLists<Integer, String>();
		subTree.inorderTraversal(task);
		assertEquals(task.getKeys().toString(), "[15, 16, 18, 20]");
		assertEquals(task.getValues().toString(), "[Fifteen, Sixteen, Eighteen, Twenty]");
		System.out.println("Get SubTree test 3:\n" + subTree.getRoot() + "\n");
		
	}
	
	@Test
	public void testGetSubtreeEmptyTree() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		PolymorphicBST<Integer, String> subTree = ptree.subMap(10, 20);
		assertEquals(0, subTree.size());
		assertEquals(0, subTree.height());
		assertTrue(ptree.getRoot() instanceof EmptyTree);
	}
}