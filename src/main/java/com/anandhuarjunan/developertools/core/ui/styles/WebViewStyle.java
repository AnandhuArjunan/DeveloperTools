/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
βπ§  π  π΅π  πππΆππΉπ½π πππΏπππΆπ  π  π§ 
 */
package com.anandhuarjunan.developertools.core.ui.styles;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

public abstract class WebViewStyle  extends AnchorPane {


    protected final AnchorPane anchorpane1;
    protected final WebView webView;
    protected final DropShadow dropShadow;

	    public WebViewStyle() {

	    	  anchorpane1 = new AnchorPane();
	          webView = new WebView();
	          dropShadow = new DropShadow();

	          setPrefHeight(720.0);
	          setPrefWidth(1080.0);
	         // setStyle("-fx-background-color: white;");

	          AnchorPane.setBottomAnchor(anchorpane1, 40.0);
	          AnchorPane.setLeftAnchor(anchorpane1, 40.0);
	          AnchorPane.setRightAnchor(anchorpane1, 40.0);
	          AnchorPane.setTopAnchor(anchorpane1, 40.0);
	          anchorpane1.setFocusTraversable(true);
	          anchorpane1.setMinHeight(0.0);
	          anchorpane1.setMinWidth(0.0);
	          anchorpane1.setPrefHeight(661.0);
	          anchorpane1.setPrefWidth(1029.0);

	          AnchorPane.setBottomAnchor(webView, 0.0);
	          AnchorPane.setLeftAnchor(webView, 0.0);
	          AnchorPane.setRightAnchor(webView, 0.0);
	          AnchorPane.setTopAnchor(webView, 0.0);
	          webView.setPrefHeight(200.0);
	          webView.setPrefWidth(200.0);

	         // webView.setEffect(dropShadow);

	          anchorpane1.getChildren().add(webView);
	          getChildren().add(anchorpane1);

	    }
	}


