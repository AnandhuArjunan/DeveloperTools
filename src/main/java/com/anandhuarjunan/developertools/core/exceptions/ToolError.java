
package com.anandhuarjunan.developertools.core.exceptions;

public class ToolError extends Exception
{
	
	private static final long serialVersionUID = 1L;
	public ToolError() {
		super();
	}
	public ToolError(String msg) {
		super(msg);
	}
	public ToolError(String msg,Throwable e) {
		super(msg,e);
	}
	
	
}
