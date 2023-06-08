/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.function;

import com.anzoft.developertools.exceptions.InternalError;

/**
 * An interface that do some operations without taking any arguments in and without returning any output.
 * @author Anandhu Arjunan
 *
 */
@FunctionalInterface
public interface Action {
	
	public void execue() throws InternalError;
	

}
