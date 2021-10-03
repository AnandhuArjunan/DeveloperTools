/*
 * 
 */
package com.anandhuarjunan.developertools.core.database.persistables;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TOOL_ACTIVTY_HISTORY")
public class ToolActivityHistory implements Serializable {
	/**
     * 
     */
    private static final long serialVersionUID = -8122951125022993720L;
	/**
	 * 
	 */
	@Id
    @Column(name="TAH_KEY")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int key;
    @OneToOne
	@JoinColumn(name="TAH_TOOL_ID")
	private Tools tools;
    @Column(name="TAH_ACTIVITY_DATE")
	private Date toolAcivityDate;
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public Tools getTools() {
		return tools;
	}
	public void setTools(Tools tools) {
		this.tools = tools;
	}
	public Date getToolAcivityDate() {
		return new Date(toolAcivityDate.getTime());
	}
	public void setToolAcivityDate(Date toolAcivityDate) {
		this.toolAcivityDate = new Date(toolAcivityDate.getTime());
	}
    


}
