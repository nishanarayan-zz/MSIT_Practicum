package com.cmu.tsp.codechange.base;

/** 
 * The Reader interface is responsible for reading lines from the given file.
 */
public interface Reader {
	
	// Reads and returns one text line from the opened file. 
	String readLine() throws Exception;
	
	// Opens the file that has been set using setFileName
	void open() throws Exception;
	
	// Closes the open file. 
	void close() throws Exception;
	
	// This method returns True if more records can be read from the file,
	// otherwise it returns false
	boolean isEmpty() throws Exception;
	
	// Sets filename to be written 
	void setFileName(String filename);
	
}
