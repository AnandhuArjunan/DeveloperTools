package com.anandhuarjunan.developertools.core.views;

import com.anandhuarjunan.developertools.core.utils.DialogBox;

public interface ClosableTool {

	
	boolean canBeClosed();
	void close();
	
	default boolean performClose() {
		boolean canBeClosed = canBeClosed();
		if(!canBeClosed) {
			boolean boole = DialogBox.confirmationDialog("Do you want to force close ? ", "Some actions are still processing ", "");
			if(boole) {
				close();
			}else {
				return false;
			}
		}else {
			close();
		}
		return true;
	}
	
	
}
