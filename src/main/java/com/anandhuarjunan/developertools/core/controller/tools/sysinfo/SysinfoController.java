package com.anandhuarjunan.developertools.core.controller.tools.sysinfo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.anandhuarjunan.developertools.core.utils.FxComponentUtils;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;

public class SysinfoController implements Initializable  {
	

    @FXML
    private TabPane sysinfotabs;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	
		
		try {
			FxComponentUtils.loadTab(sysinfotabs, FxComponentUtils.loadFXML("/fxml/sysinfo/memoryinfo.fxml"),"Memory");
			
			FxComponentUtils.loadTab(sysinfotabs, FxComponentUtils.loadFXML("/fxml/sysinfo/cpuinfo.fxml"),"CPU");

			//FxComponentUtils.loadTab(sysinfotabs, FxComponentUtils.loadFXML(""),"Processes");

			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

   
	
	

}
