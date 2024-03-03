package com.anandhuarjunan.developertools.views;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang3.SystemUtils;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.material.Material;

import com.anandhuarjunan.jfxawesome.components.SimpleViewSwitcher;
import com.anandhuarjunan.jfxawesome.components.ViewSwitcherBean;
import com.anandhuarjunan.jfxawesome.templates.material.components.containers.infopane.InfoPane;

import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXRectangleToggleNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class DashboardPresenter implements Initializable
{

	private ToggleGroup toggleGroup = new ToggleGroup();

	    @FXML
	    private Label userName;
	    

	    @FXML
	    private StackPane dashboardContent;
	    
	    @FXML
	    private VBox navBar;

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadViews();
	}


	
	private void loadViews()  {
		SimpleViewSwitcher simpleViewSwitcher = new SimpleViewSwitcher();

		DashboardHomeView dashboardHomeView = new DashboardHomeView();
		
		simpleViewSwitcher.addView(ViewSwitcherBean.builder().ofDefault(true).ofActionButton(createToggle("mfx-home", "Home")).ofView(dashboardHomeView).get());
		simpleViewSwitcher.addView(ViewSwitcherBean.builder().ofActionButton(createToggle("mfx-circle-dot", "Tools Marketplace")).ofView(dashboardHomeView).get());
		simpleViewSwitcher.addView(ViewSwitcherBean.builder().ofActionButton(createToggle("mfx-circle-dot", "Simple Tool Creator")).ofView(new SimpleToolCreatorView()).get());

		simpleViewSwitcher.addContentPane(dashboardContent);
		simpleViewSwitcher.addToggleBar(navBar);
		simpleViewSwitcher.show();

	}


	private ToggleButton createToggle(String icon, String text) {
		MFXIconWrapper wrapper = new MFXIconWrapper(icon, 24, 32);
		MFXRectangleToggleNode toggleNode = new MFXRectangleToggleNode(text, wrapper);
		toggleNode.setAlignment(Pos.CENTER_LEFT);
		toggleNode.setMaxWidth(Double.MAX_VALUE);
		toggleNode.setToggleGroup(toggleGroup);
		return toggleNode;
	}
	

	

}
