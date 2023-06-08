package com.anzoft.developertools.database.persistables;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PLUGINS_DATA database table.
 * 
 */
@Entity
@Table(name="PLUGINS_DATA")
@NamedQuery(name="PluginsData.findAll", query="SELECT p FROM PluginsData p")
public class PluginsData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PD_FILE_NAME")
	private String pdFileName;

	@Column(name="PD_LOADED_YN")
	private boolean pdLoadedYn;

	@Lob
	@Column(name="PD_PLUGIN_PROPERTIES")
	private byte[] pdPluginProperties;

	//bi-directional many-to-one association to PluginType
	@ManyToOne
	@JoinColumn(name="PD_FILE_TYPE")
	private PluginType pluginType;

	public PluginsData() {
	}

	public String getPdFileName() {
		return this.pdFileName;
	}

	public void setPdFileName(String pdFileName) {
		this.pdFileName = pdFileName;
	}

	public boolean getPdLoadedYn() {
		return this.pdLoadedYn;
	}

	public void setPdLoadedYn(boolean pdLoadedYn) {
		this.pdLoadedYn = pdLoadedYn;
	}

	public byte[] getPdPluginProperties() {
		return this.pdPluginProperties;
	}

	public void setPdPluginProperties(byte[] pdPluginProperties) {
		this.pdPluginProperties = pdPluginProperties;
	}

	public PluginType getPluginType() {
		return this.pluginType;
	}

	public void setPluginType(PluginType pluginType) {
		this.pluginType = pluginType;
	}

}