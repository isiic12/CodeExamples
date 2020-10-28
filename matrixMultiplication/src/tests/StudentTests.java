package tests;

import static org.junit.Assert.*;
import com.pholser.junit.quickcheck.Property;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;

import static tests.TestUtils.*;

import org.junit.*;
import java.io.IOException;
import matrix.Matrix;

public class StudentTests {
	
	@Test
	public void testTranspose1() {
		/*
		 * Matrix:  [1, 2]     -->   [1, 3]
		 * 			[3, 4]       	 [2, 4]
		 */
		int [][] aMatrix = {{1,2}, 
							{3,4}};
		
		Matrix a = new Matrix(3, 3);
		
		int [][] bMatrix = a.transpose(aMatrix);
		
		assertTrue(bMatrix[0][0] == 1);
		assertTrue(bMatrix[0][1] == 3);
		assertTrue(bMatrix[1][0] == 2);
		assertTrue(bMatrix[1][1] == 4);
	}
	
	@Test
	public void testTranspose2() {
		/*
		 * Matrix:		[1, 2]		 [1, 3, 5]
		 * 				[3, 4]  -->  [2, 4, 6]
		 * 				[5, 6]
		 */
		int [][] aMatrix = {{1,2},
							{3,4},
							{5,6}};
		
		Matrix a = new Matrix(3, 2);
		
		int [][] bMatrix = a.transpose(aMatrix);
		
		assertEquals(2, bMatrix.length);
		assertEquals(3, bMatrix[0].length);
	}
	
	@Test
	public void testMultiThreading1() {
		Matrix a = new Matrix(3,3);
		Matrix b = new Matrix(3,2);
		
		a.matrix = new int [][] {{1, 2, 3},
								 {7, 4, 2},
								 {3, 5, 6}};
					
		b.matrix = new int [][] {{1, 2}, 
								 {4, 5},
								 {3, 6}};
								 
		Matrix c = a.multiply(b, 3);
		
		int [][] correct = new int[][] {{18, 30}, 
										{29, 46},
										{41, 67}};
										
		assertEquals(correct, c.matrix);
										
	}
	
	@Test
	public void testMultiThreading2() {
		Matrix a = new Matrix(3, 3);
		Matrix b = new Matrix(3, 4);
		Matrix correct = new Matrix(3, 4);
		
		loadMatrix(a, "3x3.txt");
		loadMatrix(b, "3x4.txt");
		loadMatrix(correct, "test2.txt");
		
		long multiTime1 = System.currentTimeMillis();
		Matrix c = a.multiply(b, 2);
		long multiTime2 = System.currentTimeMillis();
		long multiDiff = multiTime2 - multiTime1;
		
		assertEquals(correct, c);
		
		long singleTime1 = System.currentTimeMillis();
		Matrix d = a.multiply(b);
		long singleTime2 = System.currentTimeMillis();
		long singleDiff = singleTime2 - singleTime1;
		
		assertEquals(correct, d);
		
		System.out.println("Multi-threaded time: " + multiDiff);
		System.out.println("Single-threaded time: " + singleDiff);
		
	}

}
