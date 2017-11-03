package com.cmu.tsp.codechange.codecounterutility;

import java.io.*;

import com.cmu.tsp.codechange.base.Reader;

public class Gof4FileReader implements Reader {

	private String fileName;
	
	private  File file;
	private boolean isEmpty = true;
	private FileInputStream fis = null; 
	private BufferedReader buff = null;
	private DataInputStream dis = null; 
    
	public void close() throws Exception {
		
		try{
		// dispose all the resources after using them.
	        fis.close();
	        buff.close();
	        dis.close();
		} catch (IOException e) {
	        e.printStackTrace();
	        System.err.println("Error: " + e.getMessage());
	        throw e;
	    }
	}

	public void open() throws Exception {
		
		try{
				file = new File(fileName);
				
				fis = new FileInputStream(file);
				// Get the object of DataInputStream
				dis = new DataInputStream(fis);
				
		        // Here BufferedInputStream is added for fast reading.
				buff = new BufferedReader(new InputStreamReader(dis));
		        
				this.isEmpty = false;
			
		    } catch (IOException e) {
		        e.printStackTrace();
		        System.err.println("Error: " + e.getMessage());
		    	throw e;
		    }
	
	}

	public String readLine() throws Exception {
	    
	    try {
	       
	    	String strLine;
	        //Read File Line By Line
	        if ((strLine = buff.readLine()) != null)   {
	        	this.isEmpty = false;
	          	return strLine;
	        }
	        else{
	        	this.isEmpty = true;
	        	return "";
	        }

	      } catch (IOException e) {
	        e.printStackTrace();
	        System.err.println("Error: " + e.getMessage());
	        throw e;
	      }
	      
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setFileName(String filename) {
		this.fileName = filename;
	}

}
