/*
 * Matrix
 * 
 * The purpose of this class is to represent a 3x3 Matrix, which can do the following Matrix
 * operations:
 * 		1. Calculate its transpose
 * 		2. Calculate its determinant
 * 		3. Calculate the cofactor matrix
 * 		4. Calculate the cofactor of a given element
 * 		5. Calculate its inverse
 * 		6. Multiply itself by another given 3x3 matrix
 * 		7. Add itself to another given 3x3 matrix
 * 		8. Calculate its transpose
 * 		9. Multiply itself by the column of another given 3x3 matrix
 * 
 * Author(s):	Ryan Ellison
 * 				Nekesa Mercy
 * 
 * Date:		4/15/2017
 */

package MatrixProjectOnePackage;

//Imports
import java.io.BufferedReader;			// allows buffering
import java.io.FileReader;				// allows input stream with a file
import java.io.IOException;	
import java.util.StringTokenizer;


public class Matrix


{
private double matrix[][];	// the matrix
	
	/*
	 * Matrix()
	 * 
	 * The purpose of this constructor is to initialize all elements of the 3x3
	 * to 0.
	 * 
	 * Input:	none
	 * 
	 * Return:	none
	 */
	
	public Matrix()
	{
		matrix = new double[3][3];
		int r, c;				// row, column
		
		// initialize all elements of the 3x3 matrix to 0
		for(r = 0; r < matrix.length; ++r)
			for(c = 0; c < matrix[r].length; ++c)
				matrix[r][c] = 0.0;
		
	}
	
	/*
	 * Matrix()
	 * 
	 * The purpose of this constructor is to take a 3x3 matrix that is passed to it.
	 * 
	 * Input:	matrix	// 3x3 matrix to use
	 * 
	 * Return:	none
	 */
	
	public Matrix(double matrix[][])
	{
		int r, c;
		
		// make sure the matrix passed is 3x3
		// IndexOutOfBounds impossible as the if-statement will short-circuit for a matrix that doesn't have 3 rows
		if((matrix.length == 3) && (matrix[0].length == 3) && (matrix[1].length == 3) && (matrix[2].length == 3)) 
		{
			this.matrix = matrix;
		}
		else
		{
			// handle non-3x3 matrix
			System.out.println("ERROR: The matrix is not 3x3!");
			
			matrix = new double[3][3];
			
			// initialize all elements to 0
			for(r = 0; r < matrix.length; ++r)
				for(c = 0; c < matrix[r].length; ++c)
					matrix[r][c] = 0.0;
			
		}
		
	}
	
	/*
	 * Matrix()
	 * 
	 * The purpose of this constructor is to input a 3x3 matrix from a given file.
	 * 
	 * Input:	inputfile	// file to find the matrix in
	 * 
	 * Return:	none
	 */
	
	public Matrix(String inputfile) throws IOException
	{
		// Keyboard Input
		FileReader fr = new FileReader(inputfile);		// input stream to the file
		BufferedReader br = new BufferedReader(fr);		// buffering
		StringTokenizer st;								// allows breaking each line into seperate integers
		String currLine;								// current line being processed
		int r, c;
		
		matrix = new double[3][3];
		
		// go through each element in the array
		for(r = 0; r < matrix.length; ++r)
		{
			currLine = br.readLine();				// read a line from the file containing values for a row in the matrix
			st = new StringTokenizer(currLine);
			for(c = 0; c < matrix[r].length; ++c)
			{
				matrix[r][c] = Double.parseDouble(st.nextToken());
			}
		}

		// close the stream
		br.close();
	}
	
	/*
	 * getMatrix()
	 * 
	 * The purpose of this method is to return a copy of the matrix.
	 * 
	 * Input:	none
	 * 
	 * Return:	matrix	// copy of the matrix
	 */
	
	public double[][] getMatrix()
	{
		double matrix[][] = new double[3][3]; // copy of the matrix
		int r, c;
		
		// copy the values from the original matrix into the copy
		for(r = 0; r < matrix.length; ++r)
			for(c = 0; c < matrix[r].length; ++c)
				matrix[r][c] = this.matrix[r][c];
		
		return matrix;
	}
	
	/*
	 * transpose()
	 * 
	 * The purpose of this method is to compute and return the transpose of the 3x3 matrix.
	 * 
	 * Input:	none
	 * 
	 * Return:	transpose[][]	// the transpose of the matrix
	 */
	
	public double[][] transpose()
	{
		double transpose[][] = new double[3][3]; // the transpose of the matrix
		int r, c;
		
		// calculate the transpose by inverting the rows and columns
		for(r = 0; r < transpose.length; ++r)
			for(c = 0; c < transpose[r].length; ++c)
				transpose[c][r] = matrix[r][c];
		
		return transpose;
	}
	
	/*
	 * cofactorElement()
	 * 
	 * The purpose of this method is to compute and return the cofactor of a specified
	 * element in the 3x3 matrix.
	 * 
	 * Input:	row		// row of the element to find the cofactor of
	 * 			col		// column of the element to find the cofactor of
	 * 
	 * Return:	cofactor	// cofacter of the element
	 */
	
	public double cofactorElement(int row, int col)
	{
		double m[][] = new double[2][2];	// array to hold the elements left over after taking out all elements sharing the same row and col as the given element
		double cofactor;				// cofactor of the element
		int r, c;					// row and column for matrix
		int mr, mc;					// row and column for matrix m
		
		// initialize
		mr = 0;
		mc = 0;
		
		// traverse every element in matrix
		for(r = 0; r < matrix.length; ++r)
		{
			for(c = 0; c < matrix[r].length; ++c)
			{
				// if both the row and col of the element are different from the given row and col,
				// then add it to the matrix m
				if(r != row && c != col)
				{
					m[mr][mc] = matrix[r][c];
					
					// set the row and col variables for matrix m to the next empty space
					if((mr == 0) && (mc == 0))
					{
						mc = 1;
					}
					else if((mr == 0) && (mc == 1)) 
					{
						mr = 1;
						mc = 0;
					}
					else if((mr == 1) && (mc == 0))
					{
						mc = 1;
					}
				}
			}
		}
		
		// calculate the cofactor
		cofactor = (m[0][0] * m[1][1]) - (m[0][1] * m[1][0]);
		
		// check if row + col is odd, if so, multiply the cofactor by -1
		if(((row + col) % 2) != 0) 
			cofactor *= -1;
		
		return cofactor;
		
	}
	
	/*
	 * cofactorMatrix()
	 * 
	 * The purpose of this method is to compute and return the cofactor of the entire
	 * matrix.
	 * 
	 * Input:	none
	 * 
	 * Return:	cofactor	// cofactor matrix
	 */
	
	public double[][] cofactorMatrix()
	{
		double cofactor[][] = new double[3][3];	// cofactor matrix
		int r, c;
		
		// calculate and store the cofactor for every element in the matrix
		for(r = 0; r < cofactor.length; ++r)
			for(c = 0; c < cofactor[r].length; ++c)
				cofactor[r][c] = cofactorElement(r, c);
		
		return cofactor;

	}
	
	/*
	 * sum()
	 * 
	 * The purpose of this method is to compute and return the sum of
	 * the matrix and another specified matrix.
	 * 
	 * Input:	m2	// the other matrix to be added
	 * 
	 * Return:	sum	// the summed matrix
	 */
	
	public double[][] sum(double[][] m2)
	{
		double sum[][] = new double[3][3]; // the summed matrix
		int r, c;
		
		// add the elements of the matrix and passed matrix and store
		for(r = 0; r < sum.length; ++r)
			for(c = 0; c < sum[r].length; ++c)
				sum[r][c] = matrix[r][c] + m2[r][c];
		
		return sum;
	}
	
	
	/*
	 * determinant 
	 * 
	 * The purpose of this method is to compute the determinant of the matrix 
	 * 
	 * Input:	none
	 * 
	 * Return:	determinant // the determinant
	 */
	
	public double determinant() {
		
		double determinant;	// the determinant
		
		// initialize
		determinant = 0.0; 
		
		// calculate the determinant using cofactorElement()
		determinant =  matrix[0][0]*(cofactorElement(0,0))
					   + matrix[0][1]*(cofactorElement(0,1))
					   + matrix[0][2]*(cofactorElement(0,2));
		
		return determinant;
		
	    
	} 
	
	/*
	 * inverse()
	 * 
	 * The purpose of this method is to determine the inverse of the matrix.
	 * 
	 * Input:	none
	 * 
	 * Return:	inverse	// the inverse matrix
	 * 
	 */
	
	public double[][] inverse() {
		
		Matrix cofactor;						// Matrix object for the cofactor to easily get its transpose
		double inverse[][] = new double[3][3];	// the inverse matrix
		double m[][];								// holds the transpose of the cofactor matrix
	    double det;								// the determinant of the matrix
	    int r, c;
	    
	    // calculate the determinant
	    det = determinant();
	    
	    // calculate the cofactor matrix and store it in a Matrix object
	    cofactor = new Matrix(cofactorMatrix());
	    
	    // calculate and store the transpose of the cofactor matrix
	    m = cofactor.transpose();
	    
	    // calculate the inverse
	    for(r = 0; r < inverse.length; ++r)
	    	for(c = 0; c < inverse[r].length; ++c)
	    		inverse[r][c] = (1.0/det)*m[r][c];

	    // return the new matrix
	    return inverse;
	}
	
	
	/*
	 * standardDeviation()
	 * 
	 * The purpose of this method is to determine the standard deviation.
	 * 
	 * Input:	second	// the second matrix
	 * 
	 * Return:	sd		// the standard deviation
	 * 
	 */
	
	public double standardDeviation(Matrix second) {
	    // declare variables
		double sd;			// standard deviation 
	    double mean;
	    double variance;
	    
	    //initalize variables 
	    sd = 0.0;
	    mean = 0.0; 
	    variance = 0.0;

	    // Calculate the mean of the diagonals
	    for (int i = 0; i < 3; i++) {
	      mean += this.matrix[i][i] + second.matrix[i][i];
	    }

	    // Divide the sum by the number of elements summed
	    mean /= 6.0;


	    // Calculates the variance of the elements of the diagonals
	    for (int i = 0; i < 3; i++) {
	      // Calculates the mean of the diagonals
	      variance += Math.pow(((this.matrix[i][i] - mean)), 2.0) +
	                  Math.pow(((second.matrix[i][i] - mean)), 2.0);
	    }
	    // Divide the variance by the number of elements summed
	    variance /= 6.0;

	    // Return the standard deviation of the elements
	    sd = Math.sqrt(variance);
	    
	    return (sd);
	}
	
	/*
	 * multiply()
	 * 
	 * The purpose of this method is to multiply the matrix by a given matrix and return
	 * the result.
	 * 
	 * Input:	m		// the matrix to be multiplied by
	 * 
	 * Return:	result	// the resulting matrix
	 */
	
	public double[][] multiply(double m[][])
	{
		double result[][] = new double[3][m[0].length];	// the resulting matrix
		int r, c, i;
		double sum;
		
		// traverse every element of the result matrix
		for(r = 0; r < result.length; ++r)
		{
			for(c = 0; c < result[r].length; ++c)
			{
				// reset sum every time
				sum = 0;
				
				// multiply the respective row and column elements and add them together
				for(i = 0; i < result.length; ++i)
				{
					sum += matrix[r][i] * m[i][c];
				}
				
				// set the result element equal to the sum
				result[r][c] = sum;
			}
		}
		
		return result;
	}
	
	/*
	 * multiply()
	 * 
	 * The purpose of this method is to multiply the matrix by a given row of a given matrix
	 * and return the result.
	 * 
	 * Input:	m	// the matrix to be multiplied by
	 * 			mc	// the column
	 * 
	 * Return:	result	// the resulting matrix
	 */
	
	public double[] multiply(double m[][], int mc)
	{
		double result[] = new double[3];
		int r, i;
		double sum;
		
		// traverse every element of the result matrix
				for(r = 0; r < result.length; ++r)
				{
					// reset sum every time
					sum = 0.0;
						
					// multiply the respective row and column elements and add them together
					for(i = 0; i < result.length; ++i)
					{
						sum += matrix[r][i] * m[i][mc];
					}
					
					// set the result element equal to the sum
					result[r] = sum;
					
				}
				
		return result;
	}
	
}
	
