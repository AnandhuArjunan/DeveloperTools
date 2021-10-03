package com.anandhuarjunan.developertools.core.constants;

public enum FileExtensions {

	TXT("txt"),
	CSS("css");
	
	
	private String fileExtension = null;
	
	FileExtensions(String fileExtension){
		this.fileExtension = fileExtension;
	}

	public String getFileExtension() {
		return fileExtension;
	}
	
	
}
