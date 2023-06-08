/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.controller.custom.licencereader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

import com.anzoft.developertools.app.headerpanel.ShowPriority;
import com.anzoft.developertools.app.headerpanel.message.NotifyMessage;
import com.anzoft.developertools.constants.SoftwareLicences;
import com.anzoft.developertools.logging.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LicenceReaderController {

    @FXML
    private ImageView licenceIcon;

    @FXML
    private Label licenceName;

    @FXML
    private ListView<String> licenceSpec;

    @FXML
    private TextArea licence;
    
    public void loadLicence(SoftwareLicences licences,String path) {
    File 	filelocation	= new File(path);
    try {
    	licence.setText(FileUtils.readFileToString(filelocation,StandardCharsets.UTF_8));
	} catch (IOException e) {
	      	NotifyMessage.notifyError("high",ShowPriority.HIGH_PRIORITY);
		LoggerFactory.getInstance().getLogger().warning(e.getMessage());
	}
    
    switch(licences) {
    
        case APACHE:
        	licenceName.setText("The Apache Licence 2.0");
        	  licenceIcon.setImage(new Image((LicenceReaderController.class.getResource("/images/licence_icons/support-apache.jpg").toExternalForm())));
        	licenceSpec.getItems().add("Non-copyleft");
        	licenceSpec.getItems().add("The Work is suitable for commercial use");
        	licenceSpec.getItems().add("Licensees can modify the work");
        	licenceSpec.getItems().add("Licensees must provide proper attribution for the Work");
        	licenceSpec.getItems().add("Licensees may redistribute Derivative Work under different terms");
        	licenceSpec.getItems().add("Licensees do not have to distribute the source code alongside with their Derivative Work");
        	break;

    	case MIT:
    		
    		licenceName.setText("The MIT Licence");
      	  licenceIcon.setImage(new Image((LicenceReaderController.class.getResource("/images/licence_icons/mit.png").toExternalForm())));
      	licenceSpec.getItems().add("Non-copyleft");
      	licenceSpec.getItems().add("The Work is suitable for commercial use");
      	licenceSpec.getItems().add("Licensees can modify the work");
      	licenceSpec.getItems().add("Licensees must provide proper attribution for the Work");
      	licenceSpec.getItems().add("Licensees may redistribute Derivative Work under different terms");
      	licenceSpec.getItems().add("Licensees do not have to distribute the source code alongside with their Derivative Work");
      	break;
    	case GNU_PUBLIC:
    		licenceName.setText("GNU General Public License (GPL)");
        	  licenceIcon.setImage(new Image((LicenceReaderController.class.getResource("/images/licence_icons/gnu-gpl-logo.png").toExternalForm())));
        	licenceSpec.getItems().add("Strong copyleft");
        	licenceSpec.getItems().add("The Work is suitable for commercial use");
        	licenceSpec.getItems().add("Licensees can modify the work");
        	licenceSpec.getItems().add("Derivative Work must be released under the same terms");
        	licenceSpec.getItems().add("Licensees must release the source alongside with Derivative Work");
        	break;
    	default:
    }
    
	
    }
    
}