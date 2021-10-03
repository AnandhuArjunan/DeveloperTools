/*
 * 
 */
package com.anandhuarjunan.developertools.core.views.tools.java;

import java.io.IOException;

import com.anandhuarjunan.developertools.core.ui.styles.FXMLImporter;

public class JavaRegExTester extends FXMLImporter{

	public JavaRegExTester() throws IOException {
		super();
	}

	@Override
	public String fxmlPath() {
		return "/fxml/javaregx/javaregx.fxml";
	}

	@Override
	public String[] styleSheetPath() {
		return null;
	}


}
