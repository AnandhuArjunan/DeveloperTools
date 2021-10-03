/*
 * 
 */
package com.anandhuarjunan.developertools.core.services.background;

import com.anandhuarjunan.developertools.core.exceptions.InternalError;

public interface BackgroundService {

 
    public void start() throws InternalError;
    public void stop() throws InternalError;
    public void execute() throws InternalError;

    
}
