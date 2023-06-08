/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.views;

import com.anzoft.developertools.exceptions.ToolError;

import javafx.scene.Node;

public interface Tool {

	
	public abstract void open() throws ToolError;
	public abstract boolean close()  throws ToolError;
	public abstract Node view()  throws ToolError;
	
	
}
