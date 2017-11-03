package com.cmu.tsp.codechange.base;

/**
 * This class represents a command that flows from the user interface to the controller.
 * Controller interprets the command and sets the result of the operation in the command.
 * 
 * @author Abhishek
 *
 */
public class Command {
	
	// Controller sets the result of the command (if applicable)
	protected Object result;
	
	// Indicates if the command was successfully executed by the controller
	protected boolean success;
	
	// Indicates the exception that cause the command failure at controller.
	protected String exceptionDescription;
	
	// Retrieves success of the command execution.
	public boolean isSuccess(){
		return success;
	}

	// Retrieves result of the command execution.
	public Object getResult() {
		return result;
	}

	// Retrieves exception details of the command execution.
	public String getExceptionDescription() {
		return exceptionDescription;
	}

	// Sets result of the command execution.
	public void setResult(Object result) {
		this.result = result;
	}

	// Sets success flag of the command execution.
	public void setSuccess(boolean success) {
		this.success = success;
	}

	// Sets exception details of the command execution.
	public void setExceptionDescription(String exceptionDescription) {
		this.exceptionDescription = exceptionDescription;
	}

	
}
