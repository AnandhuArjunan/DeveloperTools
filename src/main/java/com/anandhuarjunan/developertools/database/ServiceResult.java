/*
 * 
 */
package com.anandhuarjunan.developertools.database;

import java.time.Duration;

public class ServiceResult<T> {
	private Duration timeTaken = null;
	private T result;
	public Duration getTimeTaken() {
		return timeTaken;
	}
	public void setTimeTaken(Duration timeTaken) {
		this.timeTaken = timeTaken;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	
	
	
}
