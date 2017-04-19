import java.util.Scanner;

public class CalcGMatrix {
	int numberofvertices=0;
	public static final int INFINITY = 999;
	 private int EdgeMatrix[][];
	 private int loadmatrix[][];
	 private int capacitymatrix[][];
	 double GMatrix[][];
	 public CalcGMatrix(int numberofvertices)
	    {
		 EdgeMatrix = new int[numberofvertices + 1][numberofvertices + 1];
		 loadmatrix = new int[numberofvertices + 1][numberofvertices + 1];
	     capacitymatrix = new int[numberofvertices+1][numberofvertices+1];
	     GMatrix =new double[numberofvertices+1][numberofvertices+1];
	        this.numberofvertices = numberofvertices;
	    }
	
	public void calcGMatrixfunc(int EdgeMatrix[][], int loadmatrix[][],int capacitymatrix[][])
	{
		
		for(int i=1;i<=numberofvertices;i++)
		{
			for(int j=1;j<=numberofvertices;j++)
			{
				//System.out.println("inside for");
				if(loadmatrix[i][j]<=capacitymatrix[i][j] )
				{
					double c = capacitymatrix[i][j]+1;
					double l = c - loadmatrix[i][j];
					double e = EdgeMatrix[i][j];
					double result = (c/l)*e;
					//System.out.print("inside if");
					GMatrix[i][j] = (double)Math.round(result*100)/100;
					//((capacitymatrix[i][j]+1)/((capacitymatrix[i][j]+1)-loadmatrix[i][j]))*EdgeMatrix[i][j]
				}
				else
				{
					GMatrix[i][j] = 999;
				}
			}
		}
		 System.out.println();
	        for (int source = 1; source <= numberofvertices; source++)
	        {
	            System.out.print(source + "\t");
	            for (int destination = 1; destination <= numberofvertices; destination++)
	            {
	                System.out.print(GMatrix[source][destination] + "\t");
	            }
	            System.out.println();
	        }
		
	}
	
	public static void main(String args[])
	{
		
		 int EdgeMatrix[][];
		  int loadmatrix[][];
		  int capacitymatrix[][];
        int numberofvertices;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of vertices");
        numberofvertices = scan.nextInt();
        EdgeMatrix = new int[numberofvertices + 1][numberofvertices + 1];
		 System.out.println("Enter the edge Matrix for the graph");
	        for (int source = 1; source <= numberofvertices; source++)
	        {
	            for (int destination = 1; destination <= numberofvertices; destination++)
	            {
	            	EdgeMatrix[source][destination] = scan.nextInt();
	                if (source == destination)
	                {
	                	EdgeMatrix[source][destination] = 0;
	                    continue;
	                }
	                if (EdgeMatrix[source][destination] == 999)
	                {
	                	EdgeMatrix[source][destination] = INFINITY;
	                }
	            }
	        }
	        /*	
0 7 999 7 999 9
999 0 5 999 10 3
9 10 0 8 4 6
9 4 2 0 999 999
3 5 10 10 0 999
999 5 8 10 999 0 
*/
	        loadmatrix = new int[numberofvertices + 1][numberofvertices + 1];
			 System.out.println("Enter the load Matrix for the graph");
		        for (int source = 1; source <= numberofvertices; source++)
		        {
		            for (int destination = 1; destination <= numberofvertices; destination++)
		            {
		            	loadmatrix[source][destination] = scan.nextInt();
		                if (source == destination)
		                {
		                	loadmatrix[source][destination] = 0;
		                    continue;
		                }
		                if (loadmatrix[source][destination] == 999)
		                {
		                	loadmatrix[source][destination] = INFINITY;
		                }
		            }
		        }
/*	
0 9 999 31 999 12		        
999 0 60 999 0 52
0 0 0 24 132 10
17 26 46 0 999 999
68 43 12 14 0 999
999 16 42 8 999 0
*/
		        capacitymatrix = new int[numberofvertices + 1][numberofvertices + 1];
				 System.out.println("Enter the capacity Matrix for the graph");
			        for (int source = 1; source <= numberofvertices; source++)
			        {
			            for (int destination = 1; destination <= numberofvertices; destination++)
			            {
			            	capacitymatrix[source][destination] = scan.nextInt();
			                if (source == destination)
			                {
			                	capacitymatrix[source][destination] = 0;
			                    continue;
			                }
			                if (capacitymatrix[source][destination] == 999)
			                {
			                	capacitymatrix[source][destination] = INFINITY;
			                }
			            }
			        }
			        /*
0 13 999 33 999 20
999 0 67 999 5 55
5 5 0 32 134 17
23 34 55 0 999 999
68 47 20 14 0 999
999 16 44 16 999 0
			        */
			        CalcGMatrix calc = new CalcGMatrix(numberofvertices);
			        calc.calcGMatrixfunc(EdgeMatrix, loadmatrix, capacitymatrix);
	}

}
