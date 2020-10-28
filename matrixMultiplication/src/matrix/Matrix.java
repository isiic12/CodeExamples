package matrix;

import java.io.*;
import java.util.ArrayList;

public class Matrix {

    public int[][] matrix;
    public int[][] trans;
    public int x, y;
    private boolean transposed;

    public Matrix(int x, int y){
        matrix = new int[x][y];
        this.x = x;
        this.y = y;
    }


    /*
     * This method takes in a 2d matrix array and returns the transposed matrix
     * https://en.wikipedia.org/wiki/Transpose
     */
    
    //CHANGE BACK TO PRIVATE BEFORE SUBMISSION
    public int[][] transpose(int[][] arr){
    	int [][] trans = new int[arr[0].length][arr.length];
    	
		for (int i = 0; i < trans.length; i++) {
			for (int j = 0; j < trans[i].length; j++) {
				trans[i][j] = arr[j][i];
			}
		}
		return trans;
	}

    
    public void set(int[][] in){
        this.matrix = in;
    }
    
    //Do NOT modify this method
    public void load(String path) throws IOException{
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(path));
        }catch(FileNotFoundException e){
            System.err.println("file not found: " + path);
        }
        int row = 0;
        while(true){
            String line = br.readLine();
            if(line == null){
                break;
            }
            String arr[] = line.split(" ");
            for(int i = 0; i < arr.length; i ++) {
                matrix[row][i] = Integer.parseInt(arr[i]);
            }
            row++;
        }
        trans = transpose(matrix);
        transposed = true;
    }

    //Do NOT modify this method
    public String toString(){
        String aString = "";
        for(int row = 0; row < matrix.length; row++) {
            for(int col = 0; col < matrix[row].length; col++) {
                aString += " " + matrix[row][col];
            }
            aString += "\r\n";
        }
        return aString;
    }

    //This is a Single Threaded matrix multiply.
    //Takes in a matrix and multiplies itself by it so (this x b)
    public Matrix multiply(Matrix b){
    	if (b == null)
    		return null;
    	if (this.y != b.x)
    		return null;
    	if (this.x == 0 || this.y == 0)
    		return null;
    	if (b.x == 0 || b.y == 0)
    		return null;
    	
    	//Create matrix to hold result and be returned
    	//Result matrix has same # of rows as Matrix 1 and same # of columns as Matrix 2
    	Matrix result = new Matrix(this.x, b.y);

    	for (int i = 0; i < this.x; i++) {
    		for (int j = 0; j < b.y; j++) {
    			result.matrix[i][j] = 0;
    			for (int k = 0; k < b.x; k++) {
    				result.matrix[i][j] += this.matrix[i][k] * b.matrix[k][j];
    			}
    		}
    	}
        return result;
    }

    
    //This method takes in a Matrix, and a number of threads and uses that number of threads to 
    //multiply the two matrices together. It should be in the order (this x m) 
    public Matrix multiply(Matrix m, int threads) {
    	if (m == null)
    		return null;
    	if (this.y != m.x)
    		return null;
    	if (this.x == 0 || this.y == 0)
    		return null;
    	if (m.x == 0 || m.y == 0)
    		return null;
    	if (threads <= 0)
    		return null;
    	
    	//if (threads == 1)
    		//return multiply(m);
    	
    	//Create matrix to hold result and be returned
    	//Result matrix has same # of rows as Matrix 1 and same # of columns as Matrix 2
    	Matrix result = new Matrix(this.x, m.y);
    	
    	//Set each cell in the result to be 0
    	for (int i = 0; i < result.matrix.length; i++) { 
    		for (int j = 0; j < result.matrix[i].length; j++ ) {
    			result.matrix[i][j] = 0;
    		}
    	}
    	
    	int numThreads;
    	if (threads >= this.y)
    		numThreads = this.y / 2;
    	else
    		numThreads = threads;
    	
    	//Calculate the minimum number of rows each thread must compute
    	int runsPerThread = this.x / numThreads;
    	//Calculate how many (if any) rows still need to be assigned to a thread
    	int remainder = this.x % numThreads;
    	
    	//Array will hold the number of runs each thread must do
    	int [] listRunsPerThread = new int[numThreads];
    	
    	//Set each index to hold the proper number of runs
    	for (int i = 0; i < listRunsPerThread.length; i++) {
    		//Each thread MUST run at least this many times
    		listRunsPerThread[i] = runsPerThread;
    		//If there is still a remainder, increment the number of runs thread must perform then decrement the remainder
    		if (remainder > 0) {
    			listRunsPerThread[i]++;
    			remainder--;
    		}
    	}
    	
    	int [][] transposed = transpose(m.matrix);
    	
    	//Create arrayList to hold threads
    	ArrayList<Thread> threadsBag = new ArrayList<Thread>(numThreads);
    	
    	/* Each thread takes as arguments: the two 2D arrays of this and the other matrices, 
    	 * its index in the array list (to determine the first row it must multiply),
    	 * the number of runs it must complete (as determined by the listrunsPerThread array),
    	 * and the total number of threads (to increase the index of the row being calculated properly)
    	 */
    	int startingRow = 0;
    	for (int i = 0; i < numThreads; i++) {
    		threadsBag.add(new MultiplyWorker(this.matrix, transposed, result, startingRow, listRunsPerThread[i]));
    		startingRow += listRunsPerThread[i];
    	}
		
    	//Begin running threads
    	for (Thread t : threadsBag) {
    		t.start();
    	}
    	
    	for (Thread t : threadsBag) {
    		try {
    			t.join();
    		} catch (InterruptedException e) {
    			System.out.println("Thread Error");
    		}
    	}
    	  	
    	return result;
	}
    
    //a method that should take in a matrix and determine if it is equal to this matrix
    @Override
    public boolean equals(Object in) {
    	//Self check
    	if (this == in)
    		return true;
    	//Null check
		if (in == null)
			return false;
		//in class check
		if (!(in instanceof Matrix))
			return false;
		
		//Cast to matrix to continue checking attributes
		Matrix m = (Matrix) in;
		
		//Rows check
		if (matrix.length != m.matrix.length)
			return false;
		//Columns check
		if (matrix[0].length != m.matrix[0].length)
			return false;
		//Values check
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] != m.matrix[i][j])
					return false;
			}
		}
		//Reaching this point means matrices are identical
		return true;
	}
    
    private class MultiplyWorker extends Thread {
    	
    	private int index, numRuns, currentRun;
    	private Matrix results;
    	private int [][] matrix1,
    				     matrix2;
    	
    	
    	public MultiplyWorker(int [][] matrix1, int [][] matrix2, Matrix results, int startIndex, int numRuns) {
    		this.matrix1 = matrix1;
    		this.matrix2 = matrix2;
    		this.results = results;
    		this.numRuns = numRuns;				//Number of runs this thread must do
    		this.currentRun = 0;				//Number of current run
    		this.index = startIndex;			//Index of row currently being multiplied/calculated
    	}
    	
    	@Override
    	public void run() {
    		//Run as many times as necessary to do all multiplications
    		while (currentRun < numRuns) {
    			//If the index is invalid, break to avoid errors
    			if (index >= results.matrix.length)
        			break;
    			//Multiplication happens here
    			for (int j = 0; j < matrix1[index].length; j++) {
        			for (int k = 0; k < matrix2.length; k++) {
        				results.matrix[index][k] += matrix1[index][j] * matrix2[k][j];
        			}
        		}
    			//Jump to next index by adding number of threads also working on problem
    			//(avoids multiple threads doing the same calculation
    			index++;
    			
    			//Increment run number
    			currentRun++;
    		}
       	}
    }
    

    //this is given as potentially useful starting point for testing
    public static void main(String[] args){
        Matrix a = new Matrix(3,4);
        Matrix b = new Matrix(4,4);
        int[][] ain = {
            {1, 2, 3, 4},
            {1, 2, 3, 4},
            {1, 2, 3, 4}//,
            //   {1, 2, 3, 4}
        };
        int[][] bin = {
            {1, 2, 3, 4},
            {1, 2, 3, 4},
            {1, 2, 3, 4},
            {1, 2, 3, 4}
        };
        a.set(ain);
        b.set(bin);
        Matrix rem = a.multiply(b, 3);

		/*
        System.out.println(a);
        System.out.println(b);
        System.out.println(rem);
        */

    }

}

