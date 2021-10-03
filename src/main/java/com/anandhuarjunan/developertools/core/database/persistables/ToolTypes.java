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
@Table(name = "ToolTypes")
public class ToolTypes  implements Serializable{
	
	   /**
     * 
     */
    private static final long serialVersionUID = 1185288705942623077L;

	@Id
	    @Column(name="TT_ID")
	    private String id;
	    
	    @Column(name="TT_NAME")
	    private String typeName;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

}
