/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.app.headerpanel;

import java.time.Duration;
import java.util.List;

import javafx.scene.Node;

public abstract class HeaderPanelContent implements Comparable<HeaderPanelContent> {
    private int showPriority = ShowPriority.HIGH_PRIORITY;
    private String headingName = "Notification";

    public HeaderPanelContent(int priority) {
	showPriority = priority;
    }
    
    public HeaderPanelContent(int priority,String headingName) {
   	showPriority = priority;
   	this.headingName = headingName;
       }
    
 
  
    public long showDuration() {
	long duration = 0;
	if(showPriority == ShowPriority.HIGH_PRIORITY) {
	    duration = Duration.ofMillis(10000).toMillis();
	}else if(showPriority == ShowPriority.MEDIUM_PRIORITY) {
	    duration = Duration.ofMillis(6000).toMillis();

	}else if(showPriority == ShowPriority.LOW_PRIORITY) {
	    duration = Duration.ofMillis(3000).toMillis();

	}else if(showPriority == ShowPriority.SHOW_NOW) {
	    duration = Duration.ofMillis(10000).toMillis();
	}
	return duration;
    }

    public int getShowPriority() {
        return showPriority;
    }

    public void setShowPriority(int showPriority) {
        this.showPriority = showPriority;
    }
    
    public String getHeadingName() {
        return headingName;
    }

    public void setHeadingName(String headingName) {
        this.headingName = headingName;
    }

    @Override
    public int compareTo(HeaderPanelContent o) {
        return o.showPriority < this.showPriority ? 1 : -1;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + showPriority;
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
	HeaderPanelContent other = (HeaderPanelContent) obj;
	if (showPriority != other.showPriority)
	    return false;
	return true;
    }
    
    
    public abstract void load(Object...objects);
    public abstract List<Node> show();
    
    public String heading() {
	return headingName;
    }
    
}
