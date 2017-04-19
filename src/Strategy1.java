import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;

public class Strategy1 {

	public static void main(String[] args) throws IOException 
	
	{
		// TODO Auto-generated method stub
		/* Calculating the start time of the execution of program */
		 long startTime = System.currentTimeMillis();
		 
		 /* Reading the input file and storing the values in appropriate matrices */
		 ReadFileExample readfile = new ReadFileExample();
	       readfile.readFile();
	       
	       int numberofvertices=readfile.numberofvertices;
	       int edgematrix[][]= readfile.EdgeMatrix;
	       int flowmatrix[][]= readfile.FlowMatrix;
	       int[][] capacitymatrix = readfile.CapacityMatrix;
	       
	       /* According to Strategy one we are sending the G matrix from 1 st Floydwarshall 
	        * as input to second Floydwarshall as  new edge cost matrix Thus reducing the Predicted Path delay*/
	       
		FloydWarshall F = new FloydWarshall(numberofvertices);
		F.floydwarshall(edgematrix, flowmatrix, capacitymatrix);
		double NewEdgeMatrix[][]=F.GMatrix;
		
		FloydWarshall1 F1 = new FloydWarshall1(numberofvertices);
		F1.floydwarshall(NewEdgeMatrix);
		
		System.out.println();
		System.out.println("Number of inputs(n) ="+numberofvertices);
		
		  /* Total number of processors or cores available to the JVM */
	    System.out.println("Available processors (cores): " + 
	        Runtime.getRuntime().availableProcessors());

	    /* Total amount of free memory available to the JVM */
	    System.out.println("Free memory (bytes): " + 
	        Runtime.getRuntime().freeMemory());

	    /* This will return Long.MAX_VALUE if there is no preset limit */
	    long maxMemory = Runtime.getRuntime().maxMemory();
	    
	    /* Maximum amount of memory the JVM will attempt to use */
	    System.out.println("Maximum memory (bytes): " + 
	        (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));

	    /* Total memory currently available to the JVM */
	    System.out.println("Total memory available to JVM (bytes): " + 
	        Runtime.getRuntime().totalMemory());

	    String PID = ManagementFactory.getRuntimeMXBean().getName();
	   // long pid1 = ProcessHandle.current().getPid();
	    System.out.println("My PID=="+PID);
	    
	  long endTime = System.currentTimeMillis();
	  System.out.println("Took "+(endTime - startTime) + " milliseconds to execute"); 

	}
}
