/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.controller.custom.licencereader;

import com.anzoft.developertools.app.UIResources;
import com.anzoft.developertools.constants.SoftwareLicences;
import com.anzoft.developertools.controller.MainController;
import com.anzoft.developertools.views.CustomFxml;

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
