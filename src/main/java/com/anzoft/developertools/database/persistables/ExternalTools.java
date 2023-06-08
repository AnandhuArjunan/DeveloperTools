/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.database.persistables;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ExternalTools")
public class ExternalTools implements Serializable{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Id
	    @OneToOne(cascade=CascadeType.ALL)
	    @JoinColumn(name="ET_TOOLS_ID")
	    private Tools tools;
	    
	    @OneToOne
		@JoinColumn(name = "ET_APP_TYPE")
		private ExternalAppTypes appType;
	    
	    @Column(name="ET_TOOL_COMMAND")
	    private String toolCommand;
	    
	    @Column(name="ET_WORKING_DIR")
	    private String workingDir;
	    
	    @Column(name="ET_FILE_NAME")
	    private String fileName;


		public ExternalAppTypes getAppType() {
			return appType;
		}

		public void setAppType(ExternalAppTypes appType) {
			this.appType = appType;
		}

		public String getToolCommand() {
			return toolCommand;
		}

		public void setToolCommand(String toolCommand) {
			this.toolCommand = toolCommand;
		}

		public String getWorkingDir() {
			return workingDir;
		}

		public void setWorkingDir(String workingDir) {
			this.workingDir = workingDir;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public Tools getTools() {
			return tools;
		}

		public void setTools(Tools tools) {
			this.tools = tools;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((appType == null) ? 0 : appType.hashCode());
			result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
			result = prime * result + ((toolCommand == null) ? 0 : toolCommand.hashCode());
			result = prime * result + ((tools == null) ? 0 : tools.hashCode());
			result = prime * result + ((workingDir == null) ? 0 : workingDir.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ExternalTools other = (ExternalTools) obj;
			if (appType == null) {
				if (other.appType != null)
					return false;
			} else if (!appType.equals(other.appType))
				return false;
			if (fileName == null) {
				if (other.fileName != null)
					return false;
			} else if (!fileName.equals(other.fileName))
				return false;
			if (toolCommand == null) {
				if (other.toolCommand != null)
					return false;
			} else if (!toolCommand.equals(other.toolCommand))
				return false;
			if (tools == null) {
				if (other.tools != null)
					return false;
			} else if (!tools.equals(other.tools))
				return false;
			if (workingDir == null) {
				if (other.workingDir != null)
					return false;
			} else if (!workingDir.equals(other.workingDir))
				return false;
			return true;
		}
	    
	    
	 
	 
	
	
	
	
}
