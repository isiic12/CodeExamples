package binarysearchtree;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;
import junit.framework.TestCase;
import junit.framework.AssertionFailedError;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class tests {
	
	@Test
	public void testInsertAndSize() {
		BST<Integer, String> bst = new BST<Integer, String>();
		assertEquals(0, bst.size());
		
		bst.insert(50, "Fifty");
		bst.insert(20, "Twenty");
		bst.insert(10, "Ten");	
		bst.insert(30, "Thirty");
		
		assertEquals(4, bst.size());
		
		bst.insert(70, "Seventy");
		bst.insert(60, "Sixty");
		bst.insert(80, "Eighty");
		
		assertEquals(7, bst.size());
	}

	@Test
	public void testContains() {
		BST<Integer, String> bst = new BST<Integer, String>();
		
		bst.insert(20, "Twenty");
		bst.insert(50, "Fifty");
		bst.insert(10, "Ten");	
		bst.insert(30, "Thirty");
		bst.insert(15, "Fifteen");
		
		assertTrue(bst.containsKey(20));
		assertTrue(bst.containsKey(30));
		assertTrue(bst.containsKey(15));
		
		assertFalse(bst.containsKey(80));
		assertFalse(bst.containsKey(0));
		
		assertTrue(bst.containsValue("Twenty"));
		assertTrue(bst.containsValue("Fifty"));
		assertTrue(bst.containsValue("Fifteen"));
		
		assertFalse(bst.containsValue("Eighty"));
		assertFalse(bst.containsValue("Zero"));
	}
	
	@Test
	public void testDeleteLeaves() {
		BST<Integer, String> bst = new BST<Integer, String>();
		
		bst.insert(20, "Twenty");
		bst.insert(10, "Ten");
		bst.insert(30, "Thirty");
		
		bst.delete(20);
		bst.inOrder();
		
		bst.delete(30);
		bst.inOrder();
		
		bst.delete(10);
		bst.inOrder();
	}
	
	@Test
	public void testDeleteInternal() {
		BST<Integer, String> bst = new BST<Integer, String>();
	}
	
	/*
	@Test
	public void testInOrder() {
		BST<Integer, String> bst = new BST<Integer, String>();
		
		bst.insert(50, "Fifty");
		bst.insert(20, "Twenty");
		bst.insert(10, "Ten");	
		bst.insert(30, "Thirty");
		bst.insert(70, "Seventy");
		bst.insert(60, "Sixty");
		bst.insert(80, "Eighty");
		
		System.out.print("\nIn Order: ");
		bst.inOrder();
	}
	
	@Test
	public void testReverseOrder() {
		BST<Integer, String> bst = new BST<Integer, String>();
		
		bst.insert(50, "Fifty");
		bst.insert(20, "Twenty");
		bst.insert(10, "Ten");	
		bst.insert(30, "Thirty");
		bst.insert(70, "Seventy");
		bst.insert(60, "Sixty");
		bst.insert(80, "Eighty");
		
		System.out.print("\nReverse Order: ");
		bst.reverseOrder();
	}
	
	@Test
	public void testPreOrder() {
		BST<Integer, String> bst = new BST<Integer, String>();
		
		bst.insert(50, "Fifty");
		bst.insert(20, "Twenty");
		bst.insert(10, "Ten");	
		bst.insert(30, "Thirty");
		bst.insert(70, "Seventy");
		bst.insert(60, "Sixty");
		bst.insert(80, "Eighty");
		
		System.out.print("\nPre-Order: ");
		bst.preOrder();
	}
	
	@Test
	public void testPostOrder() {
		BST<Integer, String> bst = new BST<Integer, String>();
		
		bst.insert(50, "Fifty");
		bst.insert(20, "Twenty");
		bst.insert(10, "Ten");	
		bst.insert(30, "Thirty");
		bst.insert(70, "Seventy");
		bst.insert(60, "Sixty");
		bst.insert(80, "Eighty");
		
		System.out.print("\nPost-Order: ");
		bst.postOrder();
	}
	*/
}
