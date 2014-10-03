import java.util.*;
import java.io.*;

class ACPC10D{

	public static int rows;
	public static int costs[][];
	public static int memo[][];

	public static void main(String[] args) throws Exception {
		Parser in = new Parser (System.in);
		StringBuilder string = new StringBuilder();
		rows = in.nextInt();
		int count = 1;
		while (rows != 0){
			costs = new int [rows][3];
			memo = new int[rows][3];

			for (int r = 0; r < rows; r++){
				for (int c = 0; c < 3; c++){
					memo[r][c] = Integer.MIN_VALUE;
				}
			}

			for (int r = 0; r < rows; r++){
				for (int c = 0; c < 3; c++){
					costs[r][c] = in.nextInt();
				}
			}
			string.append(count + ". " + recurse(0, 1)+"\n");
			count++;
			rows = in.nextInt();
		}
		System.out.print(string);
	}

	public static int recurse(int r, int c){
		if (memo[r][c] != Integer.MIN_VALUE){
			return memo[r][c];
		}
		if (r == rows - 1 && c == 1){
			return costs[rows - 1][1];
		}

		int minCost = Integer.MAX_VALUE;
		
		// go to the right
		if (c >= 0 && c <= 1){
			minCost = Math.min(recurse(r, c + 1), minCost);
		}
		// go down
		if (r < rows - 1){
			minCost = Math.min(recurse(r + 1, c), minCost);
		}
		// go right diagonal
		if ((c >= 0 && c <= 1) && r < rows - 1){
			minCost = Math.min(recurse(r + 1, c + 1), minCost);
		}
		// go left diagonal
		if ((c >= 1 && c <= 2) && r < rows - 1){
			minCost = Math.min(recurse(r + 1, c - 1), minCost);
		}
		if (minCost == Integer.MAX_VALUE) minCost = 0;
		return memo[r][c] = minCost + costs[r][c]; 
		
	}

		public static class Parser
	{
	   final private int BUFFER_SIZE = 1 << 16;
	 
	   private DataInputStream din;
	   private byte[] buffer;
	   private int bufferPointer, bytesRead;
	 
	   public Parser(InputStream in)
	   {
	      din = new DataInputStream(in);
	      buffer = new byte[BUFFER_SIZE];
	      bufferPointer = bytesRead = 0;
	   }
	 
	   public long nextLong() throws Exception
	   {
	      long ret = 0;
	      byte c = read();
	      while (c <= ' ') c = read();
	      boolean neg = c == '-';
	      if (neg) c = read();
	      do
	      {
	         ret = ret * 10 + c - '0';
	         c = read();
	      } while (c > ' ');
	      if (neg) return -ret;
	      return ret;
	   }
	   
	   //reads in the next string
	   public String next() throws Exception
	   {
	      StringBuilder ret =  new StringBuilder();
	      byte c = read();
	      while (c <= ' ') c = read();
	      do
	      {
	         ret = ret.append((char)c);
	         c = read();
	      } while (c > ' ');
	      return ret.toString();
	   }
	 
	   public int nextInt() throws Exception
	   {
	      int ret = 0;
	      byte c = read();
	      while (c <= ' ') c = read();
	      boolean neg = c == '-';
	      if (neg) c = read();
	      do
	      {
	         ret = ret * 10 + c - '0';
	         c = read();
	      } while (c > ' ');
	      if (neg) return -ret;
	      return ret;
	   }
	   
	   private void fillBuffer() throws Exception
	   {
	      bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
	      if (bytesRead == -1) buffer[0] = -1;
	   }
	 
	   private byte read() throws Exception
	   {
	      if (bufferPointer == bytesRead) fillBuffer();
	      return buffer[bufferPointer++];
	   }
	}
}
