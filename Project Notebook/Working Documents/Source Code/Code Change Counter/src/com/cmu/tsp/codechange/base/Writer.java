package com.cmu.tsp.codechange.base;

/** 
 * The Reader interface is responsible for writing text lines to a given file.
 */
public interface Writer {
	
	// Writes a line to the opened file. 
	void writeLine(String linetext) throws Exception;
	 
	// Opens the file that has been set using setFileName
	String open() throws Exception;
	
	// Closes the open file. 
	String close() throws Exception;
	
	// Sets filename to be written 
	void setFileName(String filename);
	
}
