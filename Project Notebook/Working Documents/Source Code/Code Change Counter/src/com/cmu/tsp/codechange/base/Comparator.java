package com.cmu.tsp.codechange.base;

import java.util.List;



/**
 * Comparator interface defines the responsibilities that compares two files and
 * returns the differences.
 * 
 * @author Abhishek
 *
 */
public interface Comparator {

	void setSources(Reader reader1, Reader reader2);
	
	void compareAllLines() throws Exception;
	
	ChangeDescriptor voidCompareNextLine();
	
	List getAllChanges();
	
}
