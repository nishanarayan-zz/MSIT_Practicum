package com.cmu.tsp.codechange.codecounterutility;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.cmu.tsp.codechange.base.ChangeDescriptor;
import com.cmu.tsp.codechange.base.Command;
import com.cmu.tsp.codechange.base.Comparator;
import com.cmu.tsp.codechange.base.Reader;
import com.cmu.tsp.codechange.base.UserInterface;
import com.cmu.tsp.codechange.base.Writer;

/**
 * This class is the heart of the program. This is responsible to bind user interface,
 * comparator and file reader/writers together.
 * 
 * @author Abhishek
 *
 */
public class Controller {
	
	// Input file 1
	protected Reader readerStream1;
	
	// Input file 2
	protected Reader readerStream2;
	
	// File names of the two input files
	protected String file1;
	protected String file2;
	
	// Writer writes summary to an output file
	protected Writer writer;
	
	// Comparator which compares two files and returns results
	Comparator comparator;
	
	// User interface
	UserInterface ui;
	
	/**
	 * This method must be called before calling any other method. This initializes
	 * the user interface and the comparator. Currently it is using a GUI. 
	 */
	void initialize(){	
		
		// Instanitiate a comparator
		comparator = new ComparatorImpl();
		
		// Initialize a GUI
		ui = new GUI();
		ui.initialize(this);
		
		// Begin accepting use inputs
		beginUserActions();
	}
	
	/**
	 * This method initiates the comparison operation.
	 * @throws Exception
	 */
	void beginComparison() throws Exception{
		// Sets the two files to be compared
		comparator.setSources(readerStream1, readerStream2);
		
		// Notifies comparator to compare these two files.
		comparator.compareAllLines();
	}
	
	/**
	 * This method returns the summary of changes. The summary includes number of lines
	 * added, deleted and modified. 
	 * @return Map which has the summary details.
	 */
	public Map getChangeSummary(){
		
		List changes = getAllChanges();
		Map summary = new Hashtable();
		int addedLines = 0;
		int changedLines = 0;
		int deletedLines = 0;
		
		ChangeDescriptor cd = null;
		for(int i=0;i<changes.size();i++){
			cd = (ChangeDescriptor) changes.get(i);
			
			switch(cd.getType()) {
				case ChangeDescriptor.CH_ADDED:
						addedLines+=cd.getDestLines().size();
						break;
						
				case ChangeDescriptor.CH_DELETED:
						deletedLines+=cd.getSourceLines().size();
						break;
						
				case ChangeDescriptor.CH_MODIFIED:
						changedLines+=cd.getDestLines().size();
						break;		
			}
		
		
		}
		
		summary.put("added", new Integer(addedLines));
		summary.put("deleted", new Integer(deletedLines));
		summary.put("changed", new Integer(changedLines));
		
		return summary;
	}
	
	/**
	 * This method initiates the reading of user input operations. 
	 */
	void beginUserActions(){
		
		// Notify the user interface to accept the inputs
		ui.beginUserActions();	
	}
	
	/**
	 * Program stops when the user interface notifies to terminate
	 */
	void terminate(){
		System.exit(0);
	}
	
	/**
	 * This method sets the filename of the first source file.
	 * @param filename: String representing an absolute path
	 */
	void setSource1(String filename){
		// Instantiates a file reader object
		readerStream1 = new Gof4FileReader();
		readerStream1.setFileName(filename);
		file1 = filename;
	}
	
	/**
	 * This method sets the filename of the second source file.
	 * @param filename: String representing an absolute path
	 */
	void setSource2(String filename){
		// Instantiates a file reader object
		readerStream2 = new Gof4FileReader();
		readerStream2.setFileName(filename);
		file2 = filename;
	}
	
	/**
	 * This method sets the filename of the output file where summary of 
	 * comparison operation will be written.
	 * @param filename: String representing an absolute path
	 */
	void setOutput(String filename){
		// Instantiates a file writer object
		writer = new Gof4FileWriter();
		writer.setFileName(filename);
	}
	
	/**
	 * Entry point of the program
	 * @param args
	 */
	public static void main(String args[]){
		Controller ctrl = new Controller();
		
		// Controller is initialized with the GUI
		ctrl.initialize();
	}
	
	/**
	 * This function accepts commands from the user interface and then interprets those
	 * commands. 
	 * @param comm: Represents a command.
	 */
	public void executeCommand(Command comm){

		// Check if the command is for one click comparison
		// this command contains two source filenames and the output
		// filename.
		if(comm instanceof CmdOneClickCompare ){
			CmdOneClickCompare cmd = (CmdOneClickCompare)comm;
			
			// Retrieve filenames and set corresponding reader writers.
			setSource1(cmd.getSourceFile1());
			setSource2(cmd.getSourceFile2());
			setOutput(cmd.getOutputFile());
		
			try {
				// Compare two files 
				beginComparison();
				
				List changes = getAllChanges();
				// Write comparison operation results to a file
				outputChanges(changes);
				
				// Return the results to the user interface
				cmd.setResult(changes);
				
				// Mark the operation as successfull.
				cmd.setSuccess(true);
			} catch (Exception e) {
				
				e.printStackTrace();
				
				// set flags to indicate operation failure
				cmd.setExceptionDescription(e.getMessage());
				cmd.setSuccess(false);
			}
		}
	}
	
	/**
	 * This function retrieve a list of changes. 
	 * @return List of changes. The changes are represented in terms of ChangeDesciptor 
	 * objects
	 */
	public List getAllChanges(){
		// Retrive changes from the comporator.
		return comparator.getAllChanges();
	}
	
	/**
	 * This function writes summary of comparison and list of changes (additions, modifications
	 * and deletion of lines) to a specified output file.
	 * @param changes: This is obtained from the comparator
	 * @throws Exception
	 */
	public void outputChanges(List changes) throws Exception{
		
		ChangeDescriptor cd = null;
		List changeDesc = null;
		
		// Get summary from the comparator
		Map summary = getChangeSummary();
		
		// Open the output file
		writer.open();
		
		// Write summary of changes
		writer.writeLine("======================SUMMARY===========================");
		writer.writeLine("File#1 : " + file1);
		writer.writeLine("File#2 : " + file2);
		writer.writeLine(" ");
		writer.writeLine("Total number of added Lines    = "+summary.get("added"));
		writer.writeLine("Total number of deleted Lines  = "+summary.get("deleted"));
		writer.writeLine("Total number of modified Lines = "+summary.get("changed"));
		writer.writeLine("========================================================");
		writer.writeLine(" ");
		
		// Write list of changes. This writes, the changed/added/deleted lines with
		// line numbers
		writer.writeLine("====================List of changes======================");
		
		// Iterate over a list of changes and display them one by one
		for(int i=0; i<changes.size();i++){
		
			// Obtain a change from the list of changes
			cd = (ChangeDescriptor)changes.get(i);
			
			// Get description of the change
			changeDesc = cd.getChangeDescription();
		
			writer.writeLine("--------------------------------------------------");
		
			for(int j=0; j<changeDesc.size();j++){
		
				// Write the change description
				writer.writeLine((String)changeDesc.get(j));	
			}
			
		}
		writer.writeLine("========================================================");
		
		// Close the output file
		writer.close();
	}
}
