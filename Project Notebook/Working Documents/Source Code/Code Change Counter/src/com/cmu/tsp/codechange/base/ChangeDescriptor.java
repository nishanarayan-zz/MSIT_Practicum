package com.cmu.tsp.codechange.base;

import java.util.ArrayList;
import java.util.List;

import com.cmu.tsp.codechange.library.Difference;

/**
 * Change Descriptor describes the details about one instance of the differnce between
 * two files. Multiple instances of this class indicates multiple differenes.
 * 
 * @author Abhishek
 *
 */
public class ChangeDescriptor {
	
	// Types include, added line, deleted line or modified line
	public final static int CH_ADDED = 0;
	public final static int CH_MODIFIED = 1;
	public final static int CH_DELETED = 2;
	
	// Type of the difference
	private int type;
	
	// Line details. Lines where difference identified.
	private int delStart;
	private int delEnd;
	private int addStart;
	private int addEnd;
	
	// Lines in the source 1 that are different from source 2 file
	private List<String> sourceLines;
	
	// Lines in the source 2 that are different from source 1 file	
	private List<String> destLines;

  /**
    * This method computes the number of lines changed and puts all line numbers
    * in a string.
    * @param start: Start line number
    * @param end: end line number
    * @return: String
    */
   protected String toString(int start, int end)
   {
       // adjusted, because file lines are one-indexed, not zero.

       StringBuffer buf = new StringBuffer();

       // match the line numbering from diff(1):
       buf.append(end == Difference.NONE ? start : (1 + start));
       
       if (end != Difference.NONE && start != end) {
           buf.append(",").append(1 + end);
       }
       return buf.toString();
   }
	   
   /**
    * This method returns the details about the difference. It returns type of the difference,
    * line numbers and differing lines from both the files.
    * @return
    */
   public List getChangeDescription() {
	
	List result=new ArrayList();
	
	// Identify the type
	switch(this.type){
		case ChangeDescriptor.CH_ADDED:   
							  result.add("Added =>");
							  break;
		case ChangeDescriptor.CH_DELETED: 
							  result.add("Deleted =>");
							  break;							
		case ChangeDescriptor.CH_MODIFIED: 
							  result.add("Modified =>");
							  break;							
	}

	// Identify the differing lines from the source 1
	if(this.type!=ChangeDescriptor.CH_ADDED){
		result.add("Line Numbers (File#1) "+this.addStart+"-"+this.addEnd);
		for(int i=0; i<this.sourceLines.size(); i++){
			result.add("< "+sourceLines.get(i));
		}
	}
	
	//Identify the differing lines from the source 2
	if(this.type!=ChangeDescriptor.CH_DELETED){
		result.add("Line Numbers (File#2) "+this.delStart+"-"+this.delEnd);
		for(int i=0; i<this.destLines.size(); i++){
			result.add("> "+destLines.get(i));
		}	
	}
	return result;
   }
	   
	// Returns the type of difference
	public int getType() {
		return type;
	}

	public int getDelStart() {
		return delStart;
	}

	public void setDelStart(int delStart) {
		this.delStart = delStart;
	}

	public int getDelEnd() {
		return delEnd;
	}

	public void setDelEnd(int delEnd) {
		this.delEnd = delEnd;
	}

	public int getAddStart() {
		return addStart;
	}

	public void setAddStart(int addStart) {
		this.addStart = addStart;
	}

	public int getAddEnd() {
		return addEnd;
	}

	public void setAddEnd(int addEnd) {
		this.addEnd = addEnd;
	}

	public List<String> getSourceLines() {
		return sourceLines;
	}

	public void setSourceLines(List<String> sourceLines) {
		this.sourceLines = sourceLines;
	}

	public List<String> getDestLines() {
		return destLines;
	}

	public void setDestLines(List<String> destLines) {
		this.destLines = destLines;
	}

	public void setType(int type) {
		this.type = type;
	}

}
