
package com.anandhuarjunan.developertools.core.utils;

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
