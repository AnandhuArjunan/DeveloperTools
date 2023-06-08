package com.anzoft.developertools.database.persistables;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PLUGIN_TYPES database table.
 * 
 */
@Entity
@Table(name="PLUGIN_TYPES")
@NamedQuery(name="PluginType.findAll", query="SELECT p FROM PluginType p")
public class PluginType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PT_ID")
	private String ptId;

	@Column(name="PD_FILE_EXTENSION")
	private String pdFileExtension;

	@Column(name="PD_NAME")
	private String pdName;

	public PluginType() {
	}

	public String getPtId() {
		return this.ptId;
	}

	public void setPtId(String ptId) {
		this.ptId = ptId;
	}

	public String getPdFileExtension() {
		return this.pdFileExtension;
	}

	public void setPdFileExtension(String pdFileExtension) {
		this.pdFileExtension = pdFileExtension;
	}

	public String getPdName() {
		return this.pdName;
	}

	public void setPdName(String pdName) {
		this.pdName = pdName;
	
}}