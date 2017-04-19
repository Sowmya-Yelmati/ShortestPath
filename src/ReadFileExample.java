import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/* CS5592 - Design Analysis and Algorithms
 * By Sowmya Yelmati and
 * Varun Chavakula
 */
public class ReadFileExample {
	/*
	 * ReadFileExample class is used to interpret the input files and store the given data into three arrays
	 * Flow,Capacity and Edge Matrices
	 */
	int FlowMatrix[][];
	int EdgeMatrix[][];
	int CapacityMatrix[][];
	int numberofvertices = 0;
	int sourcetobecalculated = 0;
	int destinationtobecalculated = 0;
	public static final int INFINITY = 9999999;
	
	public void readFile() throws IOException {
		
		 List<String> list = new ArrayList<String>();
		 /* 
		  * Input the file here
		  */
		 BufferedReader reader = new BufferedReader(new FileReader("C:/users/sowmy/desktop/input/inputexample.txt"));
		 Scanner s = null;
		 /*
		  * Here we are removing all the preceding and trailing spaces in the input file
		  * and we are using space as delimiter to read the input
		  */
	        try 
	        {
	            s = new Scanner(reader);
	            s.useDelimiter(" ");
	            while(s.hasNext())
	            {
	              String str = s.next();
	               str= str.trim();
	               str= str.replaceAll("[\\s\\u00A0]+","");
	              str= str.replaceAll(",","");
	              if(str.length() > 0)
	              list.add(str); //adding the strings that are read into the list
	            }
	            /*
	             * Converting the List into String array to parse the input
	             */
	            String[] stringArr  = list.toArray(new String[list.size()]);
	            
	            numberofvertices = Integer.parseInt(stringArr[0]);
	            System.out.println("numberofvertices(n) = "+numberofvertices);
            	sourcetobecalculated = Integer.parseInt(stringArr[1]);
            	System.out.println("source (a) = "+sourcetobecalculated);
            	destinationtobecalculated = Integer.parseInt(stringArr[2]);
            	System.out.println("destination (b) = "+destinationtobecalculated);
            	
            	FlowMatrix =new int[stringArr.length][stringArr.length];
	   		  	EdgeMatrix=new int[stringArr.length][stringArr.length];
	   		  	CapacityMatrix=new int[stringArr.length][stringArr.length];
	   	    /*
	   	     * Reading the each line from input and storing them into respective array
	   	     * for eg: C,i,j,C[i][j] The C[i][j] value will be stored in ith and jth positions of Capacity Matrix
	   	     */
	            
	            for(int i=0; i<stringArr.length; i++ )
	            {
	            	//System.out.println("inside for"+i);
	            	 if( stringArr[i].equals("F"))
						{
						
						 	FlowMatrix[Integer.parseInt(stringArr[i+1])][Integer.parseInt(stringArr[i+2])]= Integer.parseInt(stringArr[i+3]);
						
						}
						else if(stringArr[i].equals("E"))
						{
							//System.out.println("inside E");
							EdgeMatrix[Integer.parseInt(stringArr[i+1])][Integer.parseInt(stringArr[i+2])]= Integer.parseInt(stringArr[i+3]);
						}
						else if(stringArr[i].equals("C"))
						{
							//System.out.println("inside C");
							CapacityMatrix[Integer.parseInt(stringArr[i+1])][Integer.parseInt(stringArr[i+2])]= Integer.parseInt(stringArr[i+3]);
						}
						
	            	
	            }

	           /*
	            * Printing all the three matrices
	            */
	            
            	System.out.println("EdgeMatrix:");
            	for (int source = 1; source <=numberofvertices; source++) {
                    for (int destination = 1; destination <=numberofvertices; destination++) {
                    	if(source==destination)
                    	{
                    		EdgeMatrix[source][destination]=0;
                    	}
                    	if(source!=destination&&EdgeMatrix[source][destination]==0)
                    	{
                    		EdgeMatrix[source][destination]=INFINITY;
                    		
                    	}
                    	if(EdgeMatrix[source][destination]==INFINITY)
                    	{
                    		System.out.print("na" + "\t");
                    	}
                    	else
                    	System.out.print(EdgeMatrix[source][destination]+ "\t");
                    	
                    }
                    System.out.println();
                    }
            	
            	 System.out.println("FlowMatrix:");
             	for (int source = 1; source <= numberofvertices; source++) {
                     for (int destination = 1; destination <= numberofvertices; destination++) {
                     	
                     	System.out.print(FlowMatrix[source][destination]+ "\t");
                     	
                     }
                     System.out.println();
                     }
             	
            	System.out.println("CapacityMatrix:");
            	for (int source = 1; source <= numberofvertices; source++) {
                    for (int destination = 1; destination <= numberofvertices; destination++) {
                    	if(source==destination)
                    	{
                    		CapacityMatrix[source][destination]=0;
                    	}
                    	if(source!=destination&&CapacityMatrix[source][destination]==0)
                    	{
                    		CapacityMatrix[source][destination]=INFINITY;
                    	}
                    	if(CapacityMatrix[source][destination]==INFINITY)
                    	{
                    		System.out.print("na" + "\t");
                    	}
                    	else
                    	System.out.print(CapacityMatrix[source][destination]+ "\t");
                    	
                    }
                    System.out.println();
                    }
	            reader.close();
	         
	        }
	        catch(NumberFormatException e)
	        {
	        	e.printStackTrace();
	        }
	}
}
