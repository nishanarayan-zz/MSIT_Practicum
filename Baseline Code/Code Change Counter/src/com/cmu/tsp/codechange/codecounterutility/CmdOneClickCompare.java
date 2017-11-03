package com.cmu.tsp.codechange.codecounterutility;

import com.cmu.tsp.codechange.base.Command;

public class CmdOneClickCompare extends Command {

	protected String sourceFile1;
	
	protected String sourceFile2;
	
	protected String outputFile;

	public CmdOneClickCompare(String sourceFile1, String sourceFile2,
			String outputFile) {
		super();
		this.sourceFile1 = sourceFile1;
		this.sourceFile2 = sourceFile2;
		this.outputFile = outputFile;
	}

	public String getSourceFile1() {
		return sourceFile1;
	}

	public String getSourceFile2() {
		return sourceFile2;
	}

	public String getOutputFile() {
		return outputFile;
	}
	
}
