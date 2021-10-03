/*
 * 
 */
package com.anandhuarjunan.developertools.core.database.persistables;

import java.io.Serializable;

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
@Table(name = "FXML_LOADER_TOOLS")
public class FxmlLoaderTools implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
    @Column(name="FLT_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int key;
		@OneToOne(cascade=CascadeType.ALL)
		@JoinColumn(name="FLT_TOOL_ID")
		private Tools tools;

	    @Column(name="FLT_FXML_PATH")
		private String fxmlPath;

	    @Column(name="FLT_STYLESHEETS")
	  	private String stylesheets;
	    
		public Tools getTools() {
			return tools;
		}
		public void setTools(Tools tools) {
			this.tools = tools;
		}
		public String getFxmlPath() {
			return fxmlPath;
		}
		public void setFxmlPath(String fxmlPath) {
			this.fxmlPath = fxmlPath;
		}
		public String getStylesheets() {
			return stylesheets;
		}
		public void setStylesheets(String stylesheets) {
			this.stylesheets = stylesheets;
		}
		public int getKey() {
			return key;
		}
		public void setKey(int key) {
			this.key = key;
		}
	
	
}
