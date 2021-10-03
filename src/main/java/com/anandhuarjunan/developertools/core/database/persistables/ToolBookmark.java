/*
 * 
 */
package com.anandhuarjunan.developertools.core.database.persistables;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "TOOL_BOOKMARK")
public class ToolBookmark implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Id
		@OneToOne(cascade=CascadeType.ALL)
		@JoinColumn(name="TB_TOOL_ID")
		private Tools tools;

	    @Column(name="TB_TOOL_POSITION")
		private int toolPosition;

		public Tools getTools() {
			return tools;
		}
		public void setTools(Tools tools) {
			this.tools = tools;
		}
		public int getToolPosition() {
			return toolPosition;
		}
		public void setToolPosition(int toolPosition) {
			this.toolPosition = toolPosition;
		}
	
}
