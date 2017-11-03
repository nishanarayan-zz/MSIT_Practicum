package com.cmu.tsp.codechange.base;

import com.cmu.tsp.codechange.codecounterutility.Controller;

public interface UserInterface {

	void initialize(Controller ctrl);
	
	void sendCommand(Command cmd);
	
	void cleanUp();
	
	void beginUserActions();

}
