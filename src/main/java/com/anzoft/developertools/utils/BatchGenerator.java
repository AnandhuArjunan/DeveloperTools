/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.utils;

import java.util.ArrayList;
import java.util.List;

public class BatchGenerator {

	List<Object> list ;
	int currentOffset = 0;
	private int batchSize = 0;
	public BatchGenerator(List<Object> list,int batchSize) {
		this.list = list;
		this.batchSize = batchSize;
	}
	
	
	public boolean hasNext() {
		return currentOffset< list.size();
	}
	
	public List<Object> next(){
		if(hasNext()) {
		    int endOffset = Math.min(currentOffset + batchSize, list.size());
			List<Object> ls= list.subList(currentOffset, endOffset);
			currentOffset= endOffset;
			return ls;
		}
		return new ArrayList<>();
	}
	
	
	
}
