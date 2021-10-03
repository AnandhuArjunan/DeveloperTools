package com.anandhuarjunan.developertools.core.database.persistables.app;

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

import com.anandhuarjunan.developertools.core.database.persistables.FxmlLoaderTools;

@Entity
@Table(name = "FXML_ADD_ON")
public class FXMLAddOn implements Serializable{
	
	private static final long serialVersionUID = 1L;

		@Id
	    @Column(name="FAO_ID")
	    @GeneratedValue(strategy = GenerationType.AUTO)
		private int faoId;

	    @Column(name="FAO_FILE_LOCATION")
		private String faoFileLocation;

	    @Column(name="FAO_FILE_NAME")
	  	private String faoFileName;
	    
	    @Column(name="FAO_VERSION")
	  	private String faoVersion;
	    
		@OneToOne(cascade=CascadeType.ALL)
		@JoinColumn(name="FAO_FLT_ID")
	  	private FxmlLoaderTools fxmlTool;

		public int getFaoId() {
			return faoId;
		}

		public void setFaoId(int faoId) {
			this.faoId = faoId;
		}

		public String getFaoFileLocation() {
			return faoFileLocation;
		}

		public void setFaoFileLocation(String faoFileLocation) {
			this.faoFileLocation = faoFileLocation;
		}

		public String getFaoFileName() {
			return faoFileName;
		}

		public void setFaoFileName(String faoFileName) {
			this.faoFileName = faoFileName;
		}

		public String getFaoVersion() {
			return faoVersion;
		}

		public void setFaoVersion(String faoVersion) {
			this.faoVersion = faoVersion;
		}

		public FxmlLoaderTools getFxmlTool() {
			return fxmlTool;
		}

		public void setFxmlTool(FxmlLoaderTools fxmlTool) {
			this.fxmlTool = fxmlTool;
		}
	    
	    
	    
	    
	
}
