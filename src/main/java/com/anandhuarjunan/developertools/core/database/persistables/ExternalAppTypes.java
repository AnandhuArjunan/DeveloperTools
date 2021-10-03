/*
 * 
 */
package com.anandhuarjunan.developertools.core.database.persistables;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EXTERNAL_APP_TYPES")
public class ExternalAppTypes implements Serializable {
	/**
     * 
     */
    private static final long serialVersionUID = 8604736944458231465L;
	@Id
    @Column(name="EAT_TYPE")
	private String type;
    @Column(name="EAT_TYPE_NAME")
	private String typeName;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Override
	public String toString() {
		return typeName;
	}


	}
    
 
