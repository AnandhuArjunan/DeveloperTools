/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.database.persistables;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Tools")
public class Tools implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	 @Column(name = "TL_ID")
	  private String id;
	 @Column(name = "TL_VISIBILITY")
	  private boolean visible;
	 @Column(name = "TL_MULTITAB_YN")
	  private boolean multiTabSupport;
	 @OneToOne
	 @JoinColumn(name = "TL_TOOL_TYPE")
	  private ToolTypes toolType;
	 @Column(name = "TL_TOOL_NAME")
	  private String toolName;
	 @OneToOne
	  @JoinColumn(name="TL_CT_ID")
	  private Category category;
	 @Column(name = "TL_IS_CLOSABLE")
	  private boolean closable;
	 
	 @Column(name = "TL_DESCRIPTION")
	  private String description;

	 
	 
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
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
	public void setClosable(boolean isClosable) {
		this.closable = isClosable;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public boolean isMultiTabSupport() {
		return multiTabSupport;
	}
	public void setMultiTabSupport(boolean multiTabSupport) {
		this.multiTabSupport = multiTabSupport;
	}
	public ToolTypes getToolType() {
		return toolType;
	}
	public void setToolType(ToolTypes toolType) {
		this.toolType = toolType;
	}
	public String getToolName() {
		return toolName;
	}
	public void setToolName(String toolName) {
		this.toolName = toolName;
	}
	@Override
	public String toString() {
		return toolName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	  
}
