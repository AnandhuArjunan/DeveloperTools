/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
â•šðŸ�§  ðŸŽ€  ð��µð�“Ž  ð�’œð�“ƒð�’¶ð�“ƒð�’¹ð�’½ð�“Š ð�’œð�“‡ð�’¿ð�“Šð�“ƒð�’¶ð�“ƒ  ðŸŽ€  ðŸ�§ 
 */
package com.anandhuarjunan.developertools.core.ui.styles;

import com.anandhuarjunan.developertools.core.utils.FxComponentUtils;

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
