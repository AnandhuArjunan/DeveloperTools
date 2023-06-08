/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.views;

import com.anzoft.developertools.exceptions.ToolError;

public abstract class CustomTool implements Tool {

	

	@Override
	public boolean close() throws ToolError {
		return true;
	}

	public abstract String getId();
	public abstract String getHeaderName();
	

}
