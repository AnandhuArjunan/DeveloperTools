package com.anandhuarjunan.developertools.database.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "TOOLS")
public class Tools {
	@Id
	@Column(name = "TOOL_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long toolId;

	@Column(name = "TOOL_NAME")
	private String toolName;
	
	@Column(name = "TOOL_CATEGORY")
	private String toolCategory;

	@Column(name = "TOOL_DESCRIPTION")
	private String toolDescription;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TOOL_PLUGIN_ID")
	private Plugins toolPlgin;

	@Column(name = "TOOL_IMPLEMENTATION")
	private String toolImplementation;


}
