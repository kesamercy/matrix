/*
 * RunMatrix
 * 
 * The purpose of this class is to use our Matrix class to complete the following tasks:
 * 		1. Read two 3x3 matrices
 * 		2. Compute the sum
 * 		3. Compute the product
 * 		4. Compute the tranpose, cofactor matrix, and determinant of the resultant matrices
 * 		5. Find the inverse of the first matrix and multiply it by the first column of the second matrix
 * 		6. Compute the standard deviation of the diagonal elements of the first and second matrices
 * 
 *
 */



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class RunMatrix
{
	// file constants
		private static String OUTPUTFILE = "outputfile.txt";
		private static String INPUTFILE1 = "inputfile.txt";
		private static String INPUTFILE2 = "inputfile2.txt";


	public static void main(String[] args) throws IOException
	{
		FileWriter fw = new FileWriter(OUTPUTFILE);		// output stream to OUTPUTFILE
		BufferedWriter bw = new BufferedWriter(fw);		// allow buffering
		PrintWriter pw = new PrintWriter(bw);

		
		Matrix mult;	// Matrix for the product of m1 and m2
		Matrix sum;		// Matrix for the sum of m1 and m2

		Matrix m1 = new Matrix(INPUTFILE1);
		Matrix m2 = new Matrix(INPUTFILE2);
		Matrix m1Inverse;
		
		// write the first matrix
		pw.printf("The first matrix:\r\n-------------\r\n\r\n");
		appendMatrix(m1.getMatrix(), pw);
		
		// write the second matrix
		pw.printf("The second matrix:\r\n-------------\r\n\r\n");
		appendMatrix(m2.getMatrix(), pw);
		
		// write the sum of the two matrices
		pw.printf("The sum of the two matrices:\r\n--------------------\r\n\r\n");
		sum = new Matrix(m1.sum(m2.getMatrix()));
		appendMatrix(sum.getMatrix(), pw);
		
		// write the product of the two matrices
		pw.printf("The product of the two matrices:\r\n------------------\r\n\r\n");
		mult = new Matrix(m1.multiply(m2.getMatrix()));
		appendMatrix(mult.getMatrix(), pw);
		
		// write the transpose of the sum matrix
		pw.printf("The transpose of the sum matrix:\r\n-------------------\r\n\r\n");
		appendMatrix(sum.transpose(), pw);
		
		// write the cofactor of the sum matrix
		pw.printf("The cofactor of the sum matrix:\r\n---------------------\r\n\r\n");
		appendMatrix(sum.cofactorMatrix(), pw);
		
		// write the determinant of the sum matrix
		pw.printf("The determinant of the sum matrix:\t\t%f\r\n\r\n", sum.determinant());
		
		// write the tranpose of the product matrix
		pw.printf("The transpose of the product matrix:\r\n-------------------\r\n\r\n");
		appendMatrix(mult.transpose(), pw);
		
		// write the cofact of the product matrix
		pw.printf("The cofactor of the product matrix:\r\n---------------------\r\n\r\n");
		appendMatrix(mult.cofactorMatrix(), pw);
		
		// write the determinant of the product matrix
		pw.printf("The determinant of the product matrix:\t\t%f\r\n\r\n", mult.determinant());
		
		// write the inverse of the first matrix
		pw.printf("The inverse of the first matrix:\r\n-------------------------\r\n\r\n");
		m1Inverse = new Matrix(m1.inverse());
		appendMatrix(m1Inverse.getMatrix(), pw);
		
		// write the inverse of the first matrix multiplied by the first column of the second matrix
		pw.printf("The inverse of the first matrix multiplied by the first column of the second matrix:\r\n-----------------------------\r\n\r\n");
		appendMatrix(m1Inverse.multiply(m2.getMatrix(), 0), pw);
		
		// write the standard deviation of the diagonal elements
		pw.printf("The standard devation of the diagonal elements of the first and second matrix is:\t\t%f\r\n\r\n", m1.standardDeviation(m2));

		pw.close();
	}
	
	
	/*
	 * appendMatrix()
	 * 
	 * The purpose of this method is to write a matrix of type double to the file attached to the
	 * given stream attached to the PrintWriter object.
	 * 
	 * Input:	matrix	// the matrix to write
	 * 			pw		// the PrintWriter object
	 * 
	 * Return:	none
	 */
	
	public static void appendMatrix(double matrix[][], PrintWriter pw) throws IOException
	{
		
		int r, c;
		
		for(r = 0; r < matrix.length; ++r)
		{
			for(c = 0; c < matrix[r].length; ++c)
			{
				pw.printf("%f\t", matrix[r][c]);
			}
			pw.println();
		}
	
		pw.println();
		
	}
	
	/*
	 * appendMatrix()
	 * 
	 * The purpose of this method is to write a matrix of type double to the file attached to the
	 * given stream attached to the PrintWriter object.
	 * 
	 * Input:	matrix	// the matrix to write
	 * 			pw		// the PrintWriter object
	 * 
	 * Return:	none
	 */
	
	public static void appendMatrix(double matrix[], PrintWriter pw) throws IOException
	{
		int r;
		
		for(r = 0; r < matrix.length; ++r)
		{
			pw.printf("%f\t", matrix[r]);
		}
		
		pw.println();
	
		
	}

}
