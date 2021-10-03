/*
 * 
 */
package com.anandhuarjunan.developertools.core.services.background;

import java.util.UUID;

import com.anandhuarjunan.developertools.core.exceptions.InternalError;

public abstract class AbstractBackgroundService implements BackgroundService{

     volatile ServiceStatus serviceStatus = null;
    private String name = null;
    private String id = null;
    private String description = null;
    
    
    
    public AbstractBackgroundService(String name,String description) {
	super();
	this.name = name;
	this.description = description;
	setId(UUID.randomUUID().toString());
    }

    @Override
    public synchronized void start() throws InternalError {

	setServiceStatus( ServiceStatus.STARTED);
    }

    @Override
    public synchronized void stop() throws InternalError {
	setServiceStatus( ServiceStatus.STOPPED);

    }
    @Override
    public void execute() throws InternalError {
	setServiceStatus( ServiceStatus.RUNNING);
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
	        this.serviceStatus = serviceStatus;
    }

    public abstract ServiceTypes serviceType();
}
