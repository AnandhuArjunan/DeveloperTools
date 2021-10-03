/*
 * 
 */
package com.anandhuarjunan.developertools.core.views;

import com.anandhuarjunan.developertools.core.exceptions.ToolError;

import javafx.scene.Node;

public interface Tool {

	
	public abstract void open() throws ToolError;
	public abstract Node view()  throws ToolError;
	
	
}
