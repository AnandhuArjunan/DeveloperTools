/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.services.background;

import com.anzoft.developertools.exceptions.InternalError;

public interface BackgroundService {

 
    public void start() throws InternalError;
    public void stop() throws InternalError;
    public void execute() throws InternalError;

    
}
