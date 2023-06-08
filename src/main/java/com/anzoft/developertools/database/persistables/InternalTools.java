/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.database.persistables;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="InternalTools")
public class InternalTools {
	
	@Id
    @Column(name="IT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    @Column(name="IT_CLASS_NAME")
	private String className;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="IT_TOOLS_ID")
	private Tools tool;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Tools getTool() {
		return tool;
	}
	public void setTool(Tools tool) {
		this.tool = tool;
	}
	
}
