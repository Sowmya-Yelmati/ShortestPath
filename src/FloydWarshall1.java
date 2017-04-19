import java.util.Scanner;
import static java.lang.String.format;

/* CS5592 - Design Analysis and Algorithms
 * By Sowmya Yelmati and
 * Varun Chavakula
 */
import java.io.IOException;
public class FloydWarshall1
{
    private int distancematrix[][];
    
    private static int numberofvertices;
    public static final int INFINITY = 9999999;
    private int next[][];
    double GMatrix[][];
    private String path[][];
    private int count[][];
	private int loadmatrix[][];
	
	/*
	 * Initializing all the required matrices by creating the constructor
	 */
    public FloydWarshall1(int numberofvertices)
    
    {  
	    loadmatrix = new int[numberofvertices + 1][numberofvertices + 1];
        distancematrix = new int[numberofvertices + 1][numberofvertices + 1];
        next = new int[numberofvertices + 1][numberofvertices + 1];
        path = new String[numberofvertices + 1][numberofvertices + 1];
        count = new int[numberofvertices+1][numberofvertices+1];
        GMatrix =new double[numberofvertices+1][numberofvertices+1];
        this.numberofvertices = numberofvertices;
    }
    /*
     * Calculating the All-pairs-shortest-path using FloydWarshall Algorithm and 
     * determining the shortest paths and the hop count between them.
     */
    
    public void floydwarshall(double newedgematrix[][])
    
    {
    	int value=0;
    	String[] str_array;
    	/*
    	 * Calculating all Pairs shortest path distance using Floyd Warshall Logic  
    	 */
        for (int source = 1; source <= numberofvertices; source++)
        {
            for (int destination = 1; destination <= numberofvertices; destination++)
            {
                distancematrix[source][destination] = (int)newedgematrix[source][destination];
            }
        }
        for (int source = 1; source <= numberofvertices; source++) 
        {
            for (int destination = 1; destination <= numberofvertices; destination++)
            {
                if (source != destination)
                { 
                	next[source][destination] = destination + 1;
                }
            }	
        }
 
        for (int intermediate = 1; intermediate <= numberofvertices; intermediate++)
        {
            for (int source = 1; source <= numberofvertices; source++)
            {
                for (int destination = 1; destination <= numberofvertices; destination++)
                {
                    if (distancematrix[source][intermediate] + distancematrix[intermediate][destination]
                         < distancematrix[source][destination])
                    {
                    	
                        distancematrix[source][destination] = distancematrix[source][intermediate] 
                            + distancematrix[intermediate][destination];
                   
					next[source][destination] = next[source][intermediate];
                    }
					
                }
                
            }
        }
        /*
         * Printing the all pairs shortest path Distance Matrix
         */
        System.out.println();
        System.out.println("Shortest Distance Matrix using strategy 1:");
        for (int source = 1; source <= numberofvertices; source++)
            System.out.print("\t" + source);
 
        System.out.println();
        for (int source = 1; source <= numberofvertices; source++)
        {
            System.out.print(source + "\t");
            for (int destination = 1; destination <= numberofvertices; destination++)
            {
            	if(distancematrix[source][destination]== INFINITY)
            	{
            		System.out.print("INF"+"\t");
            	}
            	else
                System.out.print(distancematrix[source][destination] + "\t");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        /*
         * Calculating and printing Actual paths and hop counts between them
         */
        System.out.println("Shortest Paths using strategy 1 are:");
        for (int source = 1; source <= numberofvertices; source++) 
        {
            for (int destination = 1; destination <= numberofvertices; destination++) 
            {
            	
            	if(source==destination) 
            	{
            		path[source][destination] = Integer.toString(source);
            	System.out.print(path[source][destination]+"\t");
            	}
                if (source != destination) 
                {
                    int u = source + 1;
                    int v = destination + 1;
                    path[source][destination] = Integer.toString(source);
                    do {
                    	count[source][destination]+=1;
                        u = next[u-1][v-1];
                        path[source][destination] += "," + Integer.toString(u-1)  ;
                    	} while (u != v);
                    if((destination-source)==1)
                    {
                    	count[source][destination]+=1;
                    	System.out.print("\t"+path[source][destination]+"\t\t");
                    }
                    else
                    {
                    	count[source][destination]+=1;
                    System.out.print(path[source][destination]+ "\t"+"\t");
                    }
                }
            }
            System.out.println();
        }
        /*
         * Printing the hops between the shortest paths obtained
         */
        System.out.println();
        System.out.println("Hop Counts using strategy 1 are:");
        for (int source = 1; source <= numberofvertices; source++) 
        {
            for (int destination = 1; destination <= numberofvertices; destination++)
            {
            	if(source!=destination)
            	{
            		count[source][destination]-=1;
            	}
            	System.out.print(count[source][destination]+"\t");
          /* 	if(count[source][destination]==1 );
            	System.out.println(source+" "+destination); */
            }
            
            System.out.println();
        }
          /*
		   * Calculating the Actual Path Delay for the G-Edgecost matrix
		   */
		   String s;
		   double val =0;
		 // double G[][]= calcGMatrixfunc(adjacencymatrix,loadmatrix,capacitymatrix);
		  System.out.println("GMatrix using strategy 1:");
			 System.out.println();
		        for (int source = 1; source <= numberofvertices; source++)
		        {
		            //System.out.print(source + "\t");
		            for (int destination = 1; destination <= numberofvertices; destination++)
		            {
		            	if(newedgematrix[source][destination]==9.999999E13)
		            	{
		            		System.out.print("na" + "\t");
		            	}
		            	else if(newedgematrix[source][destination]==INFINITY)
		            	{
		            		System.out.print("INF" + "\t");
		            	}
		            	else
		            		System.out.print(newedgematrix[source][destination] + "\t");
		            }
		            System.out.println();
		        }
		   for(int i=1;i<=numberofvertices;i++){
			   for(int j=1;j<=numberofvertices;j++){
				 
					 
					 s = path[i][j];
					  str_array = s.split(",");
					
					 for(int k=1;k<str_array.length;k++){

					 int m = Integer.parseInt(str_array[k-1]);
					 int n = Integer.parseInt(str_array[k]);
					 val = newedgematrix[m][n] + val;
					 
					 }
					 newedgematrix[i][j]=(double)Math.round(val*100)/100;
					
					 //System.out.println("g:"+GMatrix[i][j]); 
					 val=0;
				   }
			   }
		   
		   System.out.println("Predicted path Delay using strategy 1:");
		   System.out.println();
		   for (int source = 1; source <= numberofvertices; source++)
	        {
	            
	            for (int destination = 1; destination <= numberofvertices; destination++)
	            {
	            	if(newedgematrix[source][destination]>=INFINITY)
	            	{
	            		newedgematrix[source][destination]= INFINITY;
	            		//System.out.print(G[source][destination]+ "\t");
	            		System.out.print("INF"+ "\t");
	            	}
	            	else
	                System.out.print(newedgematrix[source][destination] + "\t");
	            }
	            System.out.println();
	        }
		   
		   }
   
    /*
0 9 11 12 8 12
18 0 15 10 17 18
17 18 0 14 10 10
17 8 10 0 17 18
15 9 12 14 0 16
18 16 15 8 9 0
     */
        
    public static void main(String... arg) throws IOException
    {
      /*  ReadFileExample readfile = new ReadFileExample();
       readfile.readFile();
       int numberofvertices=readfile.numberofvertices;
       int edgematrix[][]= readfile.EdgeMatrix;
       int flowmatrix[][]= readfile.FlowMatrix;
		int[][] capacitymatrix = readfile.CapacityMatrix;*/
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of vertices");
        numberofvertices = scan.nextInt();
 
        double[][] edgematrix = new double[numberofvertices + 1][numberofvertices + 1];
        System.out.println("Enter the Edge Matrix for the graph");
        for (int source = 1; source <= numberofvertices; source++)
        {
            for (int destination = 1; destination <= numberofvertices; destination++)
            {
            	edgematrix[source][destination] = scan.nextInt();
                if (source == destination)
                {
                	edgematrix[source][destination] = 0;
                    continue;
                }
                if (edgematrix[source][destination] == 999)
                {
                	edgematrix[source][destination] = INFINITY;
                }
            }
        
        }
       
    	
        System.out.println("Shortest path distance using strategy 1");
        FloydWarshall1 floydwarshall1 = new FloydWarshall1(numberofvertices);
        floydwarshall1.floydwarshall(edgematrix);
       
		
       // scan.close();
    }
}