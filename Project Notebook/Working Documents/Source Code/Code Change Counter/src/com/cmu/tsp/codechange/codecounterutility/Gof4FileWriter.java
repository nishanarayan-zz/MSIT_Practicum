package com.cmu.tsp.codechange.codecounterutility;

import java.io.*;

import com.cmu.tsp.codechange.base.Writer;

public class Gof4FileWriter implements Writer {

	private String fileName = "";
	
	FileWriter fstream ;
    BufferedWriter out ;

    public String close() throws Exception {
		try{
			 out.close();
		}catch (IOException e) {
	        e.printStackTrace();
	        System.err.println("Error: " + e.getMessage());
	        throw e;
	    }
		return "";
	}

	public String open() throws Exception {
		
		try{
			fstream = new FileWriter(fileName);
	        out = new BufferedWriter(fstream);
	        return fileName;
	        
		}catch (IOException e) {
	        e.printStackTrace();
	        System.err.println("Error: " + e.getMessage());
	        throw e;
	    }
		
	}

	public void writeLine(String linetext) throws Exception {
		
		try{
			 out.write("\r\n"+linetext);
		}catch (IOException e) {
	        e.printStackTrace();
	        System.err.println("Error: " + e.getMessage());
	        throw e;
	    }

	}

	public void setFileName(String filename) {
		this.fileName = filename;
	}
	

}
