/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.ui.styles;

import com.anzoft.developertools.utils.FxComponentUtils;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

public class TreeListWebViewStyle extends TreeListSplitPaneStyle {

	    public WebView webView;

	    public TreeListWebViewStyle() {
			super();
	    }
	    
	    @Override
	    protected Node addSecondaryComponent() {
	    	    webView = new WebView();
	    	    AnchorPane.setBottomAnchor(webView, 0.0);
		        AnchorPane.setLeftAnchor(webView, 0.0);
		        AnchorPane.setRightAnchor(webView, 0.0);
		        AnchorPane.setTopAnchor(webView, 0.0);
		        webView.setPrefHeight(200.0);
		        webView.setPrefWidth(200.0);
		        return webView;
	    }
	    
	    protected void setWebViewEditable(boolean toEditable) {
	    	FxComponentUtils.makeWebViewEditable(webView, toEditable);
	    }

	    
	    

	    
	    

}
