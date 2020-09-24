package binarysearchtree;

import static org.junit.Assert.*;
import java.util.HashMap;
import org.junit.Test;

public class tests {
	
	//Method to quickly create a new binary search tree for many tests
	public BST<Integer, String> createBST() {
		BST<Integer, String> bst = new BST<Integer, String>();
		
		bst.insert(50, "Fifty");
		bst.insert(30, "Thirty");
		bst.insert(20, "Twenty");
		bst.insert(40, "Fourty");
		bst.insert(70, "Seventy");
		bst.insert(60, "Sixty");
		bst.insert(80, "Eighty");
		
		return bst;
	}
	
	//Tests insertion to a tree and the size method
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

	//Tests the containsKey and containsValue methods for proper true and false evaluations
	@Test
	public void testContains() {
		BST<Integer, String> bst = createBST();
		
		assertTrue(bst.containsKey(20));
		assertTrue(bst.containsKey(40));
		assertTrue(bst.containsKey(50));
		assertTrue(bst.containsKey(80));
		
		assertFalse(bst.containsKey(90));
		assertFalse(bst.containsKey(0));
		
		assertTrue(bst.containsValue("Thirty"));
		assertTrue(bst.containsValue("Sixty"));
		assertTrue(bst.containsValue("Seventy"));
		
		assertFalse(bst.containsValue("Ten"));
		assertFalse(bst.containsValue("Zero"));
	}
	
	//Tests proper deleting of leaf nodes
	@Test
	public void testDeleteLeaves() {
		BST<Integer, String> bst = createBST();
		
		bst.delete(80);
		bst.delete(20);
		
		String traversal = bst.inOrder();
		String correct = "30:Thirty 40:Fourty 50:Fifty 60:Sixty 70:Seventy";
		assertEquals(correct, traversal);
		
		bst.delete(40);
		bst.delete(60);
		
		traversal = bst.inOrder();
		correct = "30:Thirty 50:Fifty 70:Seventy";
		assertEquals(correct, traversal);
	}
	
	//Tests proper deleting of internal nodes (have at least one child)
	@Test
	public void testDeleteInternal1() {
		BST<Integer, String> bst = createBST();
		
		bst.delete(30);
		
		String traversal = bst.inOrder();
		String correct = "20:Twenty 40:Fourty 50:Fifty 60:Sixty 70:Seventy 80:Eighty";
		assertEquals(correct, traversal);
		
		bst.insert(90, "Ninety");
		bst.insert(75, "Seventy-Five");
		bst.delete(70);
		
		traversal = bst.inOrder();
		correct = "20:Twenty 40:Fourty 50:Fifty 60:Sixty 75:Seventy-Five 80:Eighty 90:Ninety";
		assertEquals(correct, traversal);
	}
	
	//Another test of proper deleting of internal nodes
	@Test
	public void testDeleteInternal2() {
		BST<Integer, String> bst = new BST<Integer, String>();
		
		bst.insert(50, "Fifty");
		bst.insert(70, "Seventy");
		bst.insert(60, "Sixty");
		bst.insert(80, "Eighty");
		bst.delete(70);
		
		String traversal = bst.preOrder();
		String correct = "50:Fifty 80:Eighty 60:Sixty";
		assertEquals(correct, traversal);
		assertTrue(bst.getRoot().right.key == 80);
		assertTrue(bst.getRoot().right.left.key == 60);
		
		bst.delete(80);
		traversal = bst.reverseOrder();
		correct = "60:Sixty 50:Fifty";
		
		assertEquals(correct, traversal);
		assertTrue(bst.getRoot().right.key == 60);
		assertNull(bst.getRoot().right.left);
		assertNull(bst.getRoot().right.right);
	}
	
	//Tests deleting of the root
	@Test
	public void testDeleteRoot() {
		BST<Integer, String> bst = createBST();
		bst.delete(50);

		String traversal = bst.preOrder();
		String correct = "60:Sixty 30:Thirty 20:Twenty 40:Fourty 70:Seventy 80:Eighty";
		
		assertEquals(correct, traversal);
		assertTrue(bst.getRoot().key == 60);
		assertTrue(bst.getRoot().right.key == 70);
		assertTrue(bst.getRoot().left.key == 30);
		
		bst.delete(60);
		traversal = bst.preOrder();
		correct = "70:Seventy 30:Thirty 20:Twenty 40:Fourty 80:Eighty";
		
		assertEquals(correct, traversal);
		assertTrue(bst.getRoot().key == 70);
		assertTrue(bst.getRoot().right.key == 80);
		assertTrue(bst.getRoot().left.key == 30);
	}
	
	//Tests multiple deletions of nodes in the tree
	@Test
	public void testDeleteChain() {
		BST<Integer, String> bst = new BST<Integer, String>();
		bst.insert(50, "Fifty");
		bst.insert(90, "Ninety");
		bst.insert(80, "Eighty");
		bst.insert(70, "Seventy");
		bst.insert(60, "Sixty");
		bst.delete(80);
		
		String traversal = bst.preOrder();
		String correct = "50:Fifty 90:Ninety 70:Seventy 60:Sixty";

		assertEquals(correct, traversal);
	}

	//Tests the max/min Key/Val methods for proper results
	@Test
	public void testMinMax() {
		BST<Integer, String> bst = createBST();
		
		int maxKey = bst.maxKey();
		int minKey = bst.minKey();
		String maxVal = bst.maxVal();
		String minVal = bst.minVal();
		
		assertEquals(80, maxKey);
		assertEquals(20, minKey);
		assertEquals("Eighty", maxVal);
		assertEquals("Twenty", minVal);
		
		BST<Integer, String> bst2 = new BST<Integer, String>();
		
		assertNull(bst2.maxKey());
		assertNull(bst2.minKey());
		assertNull(bst2.maxVal());
		assertNull(bst2.minVal());
		
		bst2.insert(50, "Fifty");
		
		assertTrue(bst2.maxKey() == 50);
		assertTrue(bst2.maxKey() == bst2.minKey());
		assertTrue(bst2.maxVal() == "Fifty");
		assertTrue(bst2.maxVal() == bst2.minVal());
	}

	//Tests the get method for proper returned Values
	@Test
	public void testGet() {
		BST<Integer, String> bst = createBST();
		
		String result1 = bst.get(50);
		String result2 = bst.get(20);
		String result3 = bst.get(80);
		String result4 = bst.get(100);
		String result5 = bst.get(-20);
		
		assertEquals("Fifty", result1);
		assertEquals("Twenty", result2);
		assertEquals("Eighty", result3);
		assertNull(result4);
		assertNull(result5);
	}
	
	//Tests the height (max depth) and minHeight (min depth) of tree methods
	@Test
	public void testMaxAndMinHeights() {
		BST<Integer, String> bst = createBST();
		BST<Integer, String> bst2 = new BST<Integer, String>();
		
		assertEquals(bst.height(), bst.minHeight());
		
		bst.insert(90, "Ninety");
		bst.insert(100, "One Hundred");
		bst.delete(20);
		bst.delete(40);
		
		assertEquals(5, bst.height());
		assertEquals(2, bst.minHeight());
		assertEquals(0, bst2.height());
		assertEquals(0, bst2.minHeight());
	}
	
	//Tests the clear method (resets the tree to be empty)
	@Test
	public void testClear() {
		BST<Integer, String> bst = createBST();
		
		assertEquals(7, bst.size());
		
		bst.clear();
		assertEquals(0, bst.size());
		assertEquals(0, bst.height());
		assertEquals(0, bst.minHeight());
		assertNull(bst.getRoot());
	}

	//Tests the numLeaves method for proper counting of leaves
	@Test
	public void testNumLeaves() {
		BST<Integer, String> bst = createBST();
		
		assertEquals(4, bst.numLeaves());

		bst.delete(30);
		bst.delete(60);		
		assertEquals(2, bst.numLeaves());
		
		bst.clear();
		assertEquals(0, bst.numLeaves());
	}
	
	//Tests the numInternal method for proper counting of internal nodes
	@Test
	public void testNumInternal() {
		BST<Integer, String> bst = createBST();
		
		assertEquals(3, bst.numInternal());
		
		bst.insert(90, "Ninety");
		bst.insert(10, "Ten");
		bst.insert(45, "Fourty-five");
		assertEquals(6, bst.numInternal());
		
		bst.clear();		
		assertEquals(0, bst.numInternal());
	}
	
	//Tests the isFull method, which checks if all nodes in the tree have either 0 or 2 nodes
	@Test
	public void testIsFull() {
		BST<Integer, String> bst = createBST();
		
		assertTrue(bst.isFull());
		
		bst.delete(20);
		assertFalse(bst.isFull());
		
		bst.delete(40);
		assertTrue(bst.isFull());
		
		bst.clear();
		assertTrue(bst.isFull());
	}
	
	//Tests the isComplete method, which checks if all levels but the last are filled out and the last level's nodes are left-justified
	@Test
	public void testIsComplete() {
		BST<Integer, String> bst = createBST();
		
		assertTrue(bst.isComplete());
		
		bst.delete(80);
		assertTrue(bst.isComplete());
		
		bst.delete(40);
		assertFalse(bst.isComplete());
		
		bst.delete(60);
		assertTrue(bst.isComplete());
		
		bst.delete(70);
		assertFalse(bst.isComplete());
		
		bst.clear();
		assertTrue(bst.isComplete());
	}
	
	//Tests the isBST method, which checks if the tree has violated the proper ordering of nodes for a bst
	@Test
	public void testIsBST() {
		BST<Integer, String> bst = createBST();
		
		assertTrue(bst.isBST());
		
		bst.invert();
		assertFalse(bst.isBST());
	}
	
	//Tests the invert method, which flips the tree to be a mirror image of itself
	@Test
	public void testInvert() {
		BST<Integer, String> bst = createBST();
		
		String originalInOrder = bst.inOrder();
		String originalReverse = bst.reverseOrder();
		
		bst.invert();

		String invertedInOrder = bst.inOrder();
		String invertedReverse = bst.reverseOrder();
		
		assertEquals(originalInOrder, invertedReverse);
		assertEquals(originalReverse, invertedInOrder);
	}
	
	//Tests pre-order traversal of the tree
	@Test
	public void testPreOrder() {
		BST<Integer, String> bst = createBST();
		
		String traversal = bst.preOrder();
		String correct = "50:Fifty 30:Thirty 20:Twenty 40:Fourty 70:Seventy 60:Sixty 80:Eighty";
		assertEquals(correct, traversal);
		
		bst.clear();
		assertEquals("", bst.preOrder());
	}
	
	//Tests in-order traversal of the tree
	@Test
	public void testInOrder() {
		BST<Integer, String> bst = createBST();
		
		String traversal = bst.inOrder();
		String correct = "20:Twenty 30:Thirty 40:Fourty 50:Fifty 60:Sixty 70:Seventy 80:Eighty";
		assertEquals(correct, traversal);
		
		bst.clear();
		assertEquals("", bst.inOrder());
	}
	
	//Tests post-order traversal of the tree
	@Test
	public void testPostOrder() {
		BST<Integer, String> bst = createBST();
		
		String traversal = bst.postOrder();
		String correct = "20:Twenty 40:Fourty 30:Thirty 60:Sixty 80:Eighty 70:Seventy 50:Fifty";
		assertEquals(correct, traversal);
		
		bst.clear();
		assertEquals("", bst.postOrder());
	}
	
	//Tests reverse order traversal of the tree
	@Test
	public void testReverseOrder() {
		BST<Integer, String> bst = createBST();
		
		String traversal = bst.reverseOrder();
		String correct = "80:Eighty 70:Seventy 60:Sixty 50:Fifty 40:Fourty 30:Thirty 20:Twenty";
		assertEquals(correct, traversal);
		
		bst.clear();
		assertEquals("", bst.reverseOrder());
	}
	
	//Tests the various constructors of the binary search tree
	@Test
	public void testConstructors() {
		BST<Integer, String> bst1 = new BST<Integer, String>();
		
		assertNull(bst1.getRoot());
		assertEquals(0, bst1.size());
		
		BST<Integer, String> bst2 = new BST<Integer, String>(10, "Ten");
		
		assertNotNull(bst2.getRoot());	
		assertTrue(10 == bst2.getRoot().key);
		assertTrue("Ten" == bst2.getRoot().val);
		assertEquals(1, bst2.size());
		
		HashMap<Integer, String> hm = new HashMap<Integer, String>();
		hm.put(50, "Fifty");
		hm.put(20, "Twenty");
		hm.put(70, "Seventy");
		hm.put(60, "Sixty");
		
		BST<Integer, String> bst3 = new BST<Integer, String>(hm);
		
		assertNotNull(bst3.getRoot());
		assertEquals(4, bst3.size());
		assertTrue(bst3.containsKey(50));
		assertTrue(bst3.containsValue("Fifty"));
		assertTrue(bst3.containsKey(20));
		assertTrue(bst3.containsValue("Twenty"));
		assertTrue(bst3.containsKey(70));
		assertTrue(bst3.containsValue("Seventy"));
		assertTrue(bst3.containsKey(60));
		assertTrue(bst3.containsValue("Sixty"));
		
		System.out.println(bst3.inOrder());
	}
}
