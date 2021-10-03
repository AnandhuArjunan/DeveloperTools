/*
 * 
 */
package com.anandhuarjunan.developertools.core.controller.custom.licencereader;

import com.anandhuarjunan.developertools.core.app.UIResources;
import com.anandhuarjunan.developertools.core.constants.SoftwareLicences;
import com.anandhuarjunan.developertools.core.controller.MainController;
import com.anandhuarjunan.developertools.core.views.CustomFxml;

public class LicenceReaderInterface {

	private LicenceReaderInterface() {}
	public static void loadLicence(SoftwareLicences licence,String licenceName) {
		MainController mainController = UIResources.getInstance().getMainController();
		LicenceReaderController controller = new LicenceReaderController();
		CustomFxml customFxml = new CustomFxml(licenceName, "/fxml/custom/LicenceReader.fxml",controller);
		mainController.getCustomFXMLLoaderFactory().loadFXML(customFxml);
		controller.loadLicence(licence, "licenses/"+licenceName);
	}
	
	public static void loadTextContent(SoftwareLicences licence,String licenceName) {
	
	}

	
	
}
