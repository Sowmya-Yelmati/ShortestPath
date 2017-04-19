import java.io.IOException;

/* CS5592 - Design Analysis and Algorithms
 * By Sowmya Yelmati and
 * Varun Chavakula
 */

public class FloydWarshall
{
    private int distancematrix[][];
    private int numberofvertices;
    public static final int INFINITY = 9999999;
    private int next[][];
    double GMatrix[][];
    private String path[][];
    private int count[][];
    double actualpathdelay[][];
	//private int loadmatrix[][];
	
	/*
	 * Initializing all the required matrices by creating the constructor
	 */
	 
    public FloydWarshall(int numberofvertices)
    
    {  
	    //G = new int[numberofvertices + 1][numberofvertices + 1];
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
    
    public void floydwarshall(int adjacencymatrix[][], int flowmatrix[][],int capacitymatrix[][])
    
    {
    	
    	/*
    	 * Calculating all Pairs shortest path distance using Floyd Warshall Logic  
    	 */
    	
        for (int source = 1; source <= numberofvertices; source++)
        {
            for (int destination = 1; destination <= numberofvertices; destination++)
            {
                distancematrix[source][destination] = adjacencymatrix[source][destination];
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
        System.out.println("Shortest Distance Matrix:");
        for (int source = 1; source <= numberofvertices; source++)
            System.out.print("\t" + source);
 
        System.out.println();
        for (int source = 1; source <= numberofvertices; source++)
        {
            System.out.print(source + "\t");
            for (int destination = 1; destination <= numberofvertices; destination++)
            {
                System.out.print(distancematrix[source][destination] + "\t");
            }
            	System.out.println();
        }
        System.out.println();
        System.out.println();
        
        /*
         * Calculating and printing Actual paths and hop counts between them
         */
   
        System.out.println("Shortest Paths are:");
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
                    int x = source + 1;
                    int y = destination + 1;
                    path[source][destination] = Integer.toString(source);
                    do {
                    	count[source][destination]+=1;
                        x = next[x-1][y-1];
                        path[source][destination] += "," + Integer.toString(x-1)  ;
                       } while (x != y);
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
        System.out.println();
        /*
         * Printing the hops between the shortest paths obtained
         */
        System.out.println("Hop Counts are");
        for (int source = 1; source <= numberofvertices; source++) 
        {
            for (int destination = 1; destination <= numberofvertices; destination++) 
            {
            	if(source!=destination)
            	{
            		count[source][destination]-=1;
            	}
            	System.out.print(count[source][destination]+"\t");
         
            }
            
            System.out.println();
         }
        /*
         * Calculating the Edge Traffic/load Matrix based on the flow matrix
         */
        int value=0;
    	String[] str_array;
    	int loadmatrix[][]= new int[numberofvertices+1][numberofvertices+1];
    	  for(int i=1;i<=numberofvertices;i++)
    	  {
      		for(int j=1;j<=numberofvertices;j++)
      		{
      			if(i==j)
      			{
      				loadmatrix[i][j]=0;
      			}
      			else if(capacitymatrix[i][j]== INFINITY&& adjacencymatrix[i][j]==INFINITY)
      			{
      				//System.out.println("There is infinity at position"+i+","+j);
      				loadmatrix[i][j]=INFINITY;
      			}
      			else
      				loadmatrix[i][j]= 0;
      			
      		}
      	}
          for (int i =1; i <= numberofvertices; i++) 
          {
              for (int j = 1; j <= numberofvertices; j++) 
              {
            	  if(i != j)
            	  {
            		  String substringtemp= i+","+j;
           	
            		  for(int m=1;m<=numberofvertices;m++)
            		  {
            			  for(int n=1;n<=numberofvertices;n++)
            			  {
            				  if(path[m][n].contains(substringtemp))
            				  {
            					  if(m != n && loadmatrix[i][j] == INFINITY )
            					  { 
            						  continue;
            					  }		
            					  value = value+flowmatrix[m][n];
            					  loadmatrix[i][j]=value; 
            				  }
            			  }
            		  }
            		  	value=0;  
           			}	
              	}
           	}
      
        /*
         * Printing the Edge traffic/Load Matrix
         */
        System.out.println();
        System.out.println("load matrix:");
        for (int source = 1; source <= numberofvertices; source++)
            System.out.print("\t" + source);
        
        System.out.println();
		   for (int source = 1; source <= numberofvertices; source++)
	        {
			   System.out.print(source+"\t");
	            for (int destination = 1; destination <= numberofvertices; destination++)
	            {
	            	if(loadmatrix[source][destination]==INFINITY)
	            	{
	            		System.out.print("na" + "\t");
	            	}
	            	else
	            		System.out.print(loadmatrix[source][destination] + "\t");
	            }
	            System.out.println();
	        }
		   
		  /*
		   * Calculating the Actual Path Delay
		   */
		 
		   String s;
		   double val =0;
		   GMatrix=calcGMatrixfunc(adjacencymatrix,loadmatrix,capacitymatrix);
		   actualpathdelay= calcGMatrixfunc(adjacencymatrix,loadmatrix,capacitymatrix);
		   System.out.println();
		   System.out.println("GMatrix:");
			 
		        for (int source = 1; source <= numberofvertices; source++)
		        {
		            //System.out.print(source + "\t");
		            for (int destination = 1; destination <= numberofvertices; destination++)
		            { 
		            	if(actualpathdelay[source][destination]==9.999999E13)
		            	{
		            		System.out.print("na" + "\t");
		            	}
		            	else if(actualpathdelay[source][destination]==INFINITY)
		            	{
		            		System.out.print("INF" + "\t");
		            	}
		            	else
		            		System.out.print(actualpathdelay[source][destination] + "\t");
		            }
		            System.out.println();
		        }
		   for(int i=1;i<=numberofvertices;i++)
		   {
			   for(int j=1;j<=numberofvertices;j++)
			   {
					 s = path[i][j];
					 str_array = s.split(",");
					 for(int k=1;k<str_array.length;k++)
					 {
					 int m = Integer.parseInt(str_array[k-1]);
					 int n = Integer.parseInt(str_array[k]);
					 val = actualpathdelay[m][n] + val;			 
					 }
					 actualpathdelay[i][j]=(double)Math.round(val*100)/100; 
					 val=0;
				   }
			   }
		   
		   /*
		    * Printing the Actual Path Delay
		    */
		   
		   System.out.println("Actual path Delay:");
		   System.out.println();
		   for (int source = 1; source <= numberofvertices; source++)
	        {
	            for (int destination = 1; destination <= numberofvertices; destination++)
	            {
	            	if(actualpathdelay[source][destination]>=INFINITY)
	            	{
	            		actualpathdelay[source][destination]= INFINITY;
	            		//System.out.print(G[source][destination]+ "\t");
	            		System.out.print("INF"+ "\t");
	            	}
	            	else
	            		System.out.print(actualpathdelay[source][destination] + "\t");
	            }
	            System.out.println();
	        }
		   
		   }
    /*
     * calcGMatrixfunc Function calculates and returns the G - Matrix (Actual Edge Delay) 
     * by taking edge,load and capacity Matrices as input
     */
    
    public double[][] calcGMatrixfunc(int edgematrix[][],int loadmatrix[][],int capacitymatrix[][])
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
   					double e = edgematrix[i][j];
   					double result = (c/l)*e;
   					//System.out.print("inside if");
   					GMatrix[i][j] = (double)Math.round(result*100)/100;
   				}
   				else
   				{
   					GMatrix[i][j] = (double)INFINITY;
   				}
   			}
   			
   		}
   		
   			return GMatrix;
   	}       
    
    public static void main(String... arg) throws IOException
     { 
    	/*
    	 * Reads the input file provided and categorizes the input into three different matrices
    	 * and those three matrices are passed into floydwarshall function for further calculations
    	 */
        ReadFileExample readfile = new ReadFileExample();
        readfile.readFile();
        int numberofvertices=readfile.numberofvertices;
        int edgematrix[][]= readfile.EdgeMatrix;
        int flowmatrix[][]= readfile.FlowMatrix;
        int[][] capacitymatrix = readfile.CapacityMatrix;
        System.out.println("Shortest path distance:");
        FloydWarshall floydwarshall = new FloydWarshall(numberofvertices);
        floydwarshall.floydwarshall(edgematrix, flowmatrix,capacitymatrix);
     }
}