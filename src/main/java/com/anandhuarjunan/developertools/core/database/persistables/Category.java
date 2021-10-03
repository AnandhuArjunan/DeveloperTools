/*
 * 
 */
package com.anandhuarjunan.developertools.core.database.persistables;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ToolCategory")
public class Category implements Serializable {
	/**
     * 
     */
    private static final long serialVersionUID = -8726462240045475356L;
	@Id
    @Column(name="TC_ID")
	private String id;
    @Column(name="TC_NAME")
	private String categoryName;
    @OneToOne
    @JoinColumn(name="TC_PARENT_ID")
	private Category parentCategory;
    
    @OneToMany
    @JoinColumn(name="TC_TOOLS_ID")
    private List<Tools> tools;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Category getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}
	public List<Tools> getTools() {
		return tools;
	}
	public void setTools(List<Tools> tools) {
		this.tools = tools;
	}
	@Override
	public String toString() {
		return categoryName;
	}
	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((id == null) ? 0 : id.hashCode());
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
	    Category other = (Category) obj;
	    if (id == null) {
		if (other.id != null)
		    return false;
	    } else if (!id.equals(other.id))
		return false;
	    return true;
	}
	
    
    
    
}
