/*
 * 
 */

package com.anandhuarjunan.developertools.core.function;

import com.anandhuarjunan.developertools.core.exceptions.InternalError;

/**
 * An interface that do some operations without taking any arguments in and without returning any output.
 * @author Anandhu Arjunan
 *
 */
@FunctionalInterface
public interface Action {
	
	public void execue() throws InternalError;
	

}
