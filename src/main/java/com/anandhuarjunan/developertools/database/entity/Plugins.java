package com.anandhuarjunan.developertools.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "PLUGINS")
public class Plugins {

	@Id
	@Column(name = "PLUGIN_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long puginId;
	@Column(name = "PLUGIN_GROUP_ID")
	private String groupId;
	@Column(name = "PLUGIN_ARTIFACT_ID")
	private String artifactId;
	@Column(name = "PLUGIN_NAME")
	private String name;
	@Column(name = "PLUGIN_VERSION")
	private String version;
	@Column(name = "PLUGIN_DESCRIPTION")
	private String description;
	@Column(name = "PLUGIN_FILE_NAME")
	private String fileName;
	@Column(name = "PLUGIN_DEVTOOLS_VERSION")
	private String devToolsVersion;
}
