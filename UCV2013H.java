import java.util.*;

class UCV2013H{
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner (System.in); 			// read all the shit in
		StringBuilder string = new StringBuilder(); 	// print the shit out super fast

		int rows = in.nextInt(); // the rows
		int cols = in.nextInt(); // the cols

		while (rows != 0 && cols != 0){
			int [][] array = new int [rows][cols];

			for (int r = 0; r < rows; r++){
				for (int c = 0; c < cols; c++){				// cool cool, now all the shit is in an array
					array[r][c] = in.nextInt();
				}
			}

			//now comes the fun part, just search the whole graph and solve the problem

			int [] rValues = {0,0,-1,1};     					// use these arrays to search the squares around -
			int [] cValues = {1,-1, 0, 0};   					// the current square

			Queue<Point> queue = new LinkedList<Point>(); 		// a queue to keep the search going
			int [] slicks = new int [251]; 						// store the slick data here
			boolean [][] visited = new boolean [rows][cols]; 	// keep track of if it is visited 
			int totalSlicks = 0;

			//go through all of the points and just see if it is a slick
			for (int r = 0; r < rows; r++){
				for (int c = 0; c < cols; c++){

					if (array[r][c] == 1 && !visited[r][c]){  	// only do it if it == 1 and hasnt been visited
						queue.add(new Point(r,c)); 				// add it to the queue
						visited[r][c] = true;      				// make sure to record that you visited this one
						int size = 1;							// the size of this slick obviously starts at 0
						totalSlicks++;  						// since youve found one bump up the total

						while(!queue.isEmpty()){
							Point curPoint = queue.remove();  // remove the point and start the process

							for(int j = 0; j < 4; j++){
								int newRow = curPoint.row + rValues[j];		// this is getting all of the points directly around the current point
								int newCol = curPoint.col + cValues[j];		// must do both the row and the coulumn

								if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols
									 && array[newRow][newCol] == 1 && !visited[newRow][newCol]){		// make sure it is a legal space and the other conditions are true
									queue.add(new Point(newRow, newCol));	// add it to the queue so that the process will repeat
									visited[newRow][newCol] = true; 		// make sure to say its visited
									size++;									// keep track of the size of this slick
								}	
							}
						}

						slicks[size]++;		// increment the array at the index of the size of the slick
											// since all of them have been initilized at zero you can do this
											// the index will be the size of the slick
											// the value will be the total number of slicks that are that size
					}
				}
			}

			string.append(totalSlicks+"\n"); // add the total to the string

			for (int i = 0; i < slicks.length; i++){
				if(slicks[i] > 0){
					string.append(i + " " + slicks[i] + "\n"); // add all the ones with slicks
				}
			}

			rows = in.nextInt();
			cols = in.nextInt();
		}

		System.out.print(string); // last but not least, print out the amazing results
	}

	public static class Point{ // a class just to keep track of the current row and col
		int row, col;

		Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
}
