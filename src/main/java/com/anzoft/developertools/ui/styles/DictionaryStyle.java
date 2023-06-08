/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.ui.styles;
import java.util.Collection;

import com.anzoft.developertools.ui.components.DTButton;
import com.anzoft.developertools.ui.components.DTListView;
import com.anzoft.developertools.ui.components.DTTextField;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;

public abstract class DictionaryStyle extends AnchorPane{

	protected final AnchorPane anchorPane;
    protected final HBox hBox;
    protected final AnchorPane anchorPane0;
    protected final ListView<String> listView;
    protected final HBox hBox0;
    protected final TextField textField;
    protected final Button button;
    protected final WebView webView;

    public DictionaryStyle() {

        anchorPane = new AnchorPane();
        hBox = new HBox();
        anchorPane0 = new AnchorPane();
        listView = new DTListView<>();
        hBox0 = new HBox();
        textField = new DTTextField();
        button = new DTButton();
        webView = new WebView();

        setPrefHeight(720.0);
        setPrefWidth(1280.0);

        AnchorPane.setBottomAnchor(anchorPane, 0.0);
        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setRightAnchor(anchorPane, 0.0);
        AnchorPane.setTopAnchor(anchorPane, 0.0);
        anchorPane.setMaxHeight(USE_PREF_SIZE);
        anchorPane.setMaxWidth(USE_PREF_SIZE);
        anchorPane.setMinHeight(USE_PREF_SIZE);
        anchorPane.setMinWidth(USE_PREF_SIZE);
        anchorPane.setPrefHeight(400.0);
        anchorPane.setPrefWidth(600.0);

        AnchorPane.setBottomAnchor(hBox, 0.0);
        AnchorPane.setLeftAnchor(hBox, 0.0);
        AnchorPane.setRightAnchor(hBox, 0.0);
        AnchorPane.setTopAnchor(hBox, 0.0);
        hBox.setPrefHeight(100.0);
        hBox.setPrefWidth(200.0);

        anchorPane0.setPrefHeight(720.0);
        anchorPane0.setPrefWidth(349.0);
        anchorPane0.setPadding(new Insets(20.0));

        AnchorPane.setBottomAnchor(listView, 0.0);
        AnchorPane.setLeftAnchor(listView, 0.0);
        AnchorPane.setRightAnchor(listView, 0.0);
        AnchorPane.setTopAnchor(listView, 100.0);
        listView.setLayoutX(40.0);
        listView.setLayoutY(127.0);
        listView.setPrefHeight(600.0);
        listView.setPrefWidth(309.0);

        AnchorPane.setLeftAnchor(hBox0, 0.0);
        AnchorPane.setRightAnchor(hBox0, 0.0);
        hBox0.setAlignment(javafx.geometry.Pos.CENTER);
        hBox0.setLayoutY(30.0);
        hBox0.setPrefHeight(63.0);
        hBox0.setPrefWidth(200.0);
        
        textField.textProperty().addListener((obs, oldText, newText)->onSearch(newText));
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldText, newText)->onclickDictionaryItems(newText));
        HBox.setHgrow(textField, javafx.scene.layout.Priority.ALWAYS);

        button.setMnemonicParsing(false);
        button.setText("GO");
        button.setOnAction(event->onGO(textField.getText().toLowerCase()));
        HBox.setHgrow(webView, javafx.scene.layout.Priority.ALWAYS);
        webView.setPrefHeight(200.0);
        webView.setPrefWidth(200.0);
        webView.getEngine().setUserStyleSheetLocation(DictionaryStyle.class.getResource("/css/dictionarystyle.css").toString());
        anchorPane0.getChildren().add(listView);
        hBox0.getChildren().add(textField);
        hBox0.getChildren().add(button);
        anchorPane0.getChildren().add(hBox0);
        hBox.getChildren().add(anchorPane0);
        hBox.getChildren().add(webView);
        anchorPane.getChildren().add(hBox);
        getChildren().add(anchorPane);
    }
    
    
    public void addToListview(Collection<String> collection) {
    	if(null != listView) {
    		listView.getItems().clear();
    		listView.getItems().addAll(collection);
    	}
    }
    
    public void setWebView(String html) {
    	webView.getEngine().loadContent(html);
    }
    
    
    public abstract void onSearch(String keyWord);
    public abstract void onGO(String keyWord);
    public abstract void onclickDictionaryItems(String text);
    

    
    

}
