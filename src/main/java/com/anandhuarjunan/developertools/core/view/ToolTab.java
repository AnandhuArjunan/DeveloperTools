package com.anandhuarjunan.developertools.core.view;

import javafx.scene.Node;
import javafx.scene.Parent;

public final class ToolTab {

	private Node toolNode = null;
	private String id = null;
	private boolean closable = true;
	private boolean multiInstance = true;
	
	
	public ToolTab() {
		
	}


	public Node getToolNode() {
		return toolNode;
	}


	public void setToolNode(Node toolNode) {
		this.toolNode = toolNode;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public boolean isClosable() {
		return closable;
	}


	public void setClosable(boolean closable) {
		this.closable = closable;
	}


	public boolean isMultiInstance() {
		return multiInstance;
	}


	public void setMultiInstance(boolean multiInstance) {
		this.multiInstance = multiInstance;
	}
	
	
	
}
