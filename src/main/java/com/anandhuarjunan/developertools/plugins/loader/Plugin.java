package com.anandhuarjunan.developertools.plugins.loader;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Plugin {

	@JsonProperty("groupId")
	private String groupId;
	
	@JsonProperty("artifactId")
	private String artifactId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("version")
	private String version;
	
	@JsonProperty("description")
	private String description;


	@JsonProperty("devToolsVersion")
	private String devToolsVersion;

}