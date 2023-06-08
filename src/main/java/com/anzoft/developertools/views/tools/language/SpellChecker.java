/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */

package com.anzoft.developertools.views.tools.language;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.BreakIterator;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import com.anzoft.developertools.app.messages.Labels;
import com.anzoft.developertools.cache.DataCache;
import com.anzoft.developertools.exceptions.ToolError;
import com.anzoft.developertools.ui.styles.AnchorPanePlane;
import com.anzoft.developertools.views.AbstractTool;
import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class SpellChecker extends AbstractTool{
	
	private AnchorPanePlane anchorPanePlane = new AnchorPanePlane();
    private static final Set<String> dictionary = new HashSet<>();
    private static final String ENGLISH_WORDS = "ENGLISH_WORDS";

	@Override
	public void open() throws ToolError {
		
		
		try {
			loadDictionary();
		} catch (IOException e) {
			throw new ToolError("Unable to load Default English Dictionary");
		}
		loadUIElements();
		 
	}

	private void loadUIElements() {
		Platform.runLater(()->{
		 StyleClassedTextArea textArea = new StyleClassedTextArea();
	        textArea.setWrapText(true);

	        textArea.multiPlainChanges()
	                .successionEnds(Duration.ofMillis(500))
	                .subscribe(change -> {
	                    textArea.setStyleSpans(0, computeHighlighting(textArea.getText()));
	                });
	        VirtualizedScrollPane<StyleClassedTextArea> v = new VirtualizedScrollPane<>(textArea);
	        textArea.setPadding(new Insets(20));
	       // textArea.setStyle("-fx-font-size: 20px;");
	        VBox box = new VBox();
	        AnchorPane.setBottomAnchor(box, 0.0);
	        AnchorPane.setLeftAnchor(box, 0.0);
	        AnchorPane.setRightAnchor(box,0.0);
	        AnchorPane.setTopAnchor(box, 0.0);
	        ToolBar bar = new ToolBar();
	        loadToolBarNodes(bar);
	        box.getChildren().add(bar);
	        box.getChildren().add(v);
	        VBox.setVgrow(v, javafx.scene.layout.Priority.ALWAYS);

	        anchorPanePlane.anchorPane.getChildren().add(box);
	        anchorPanePlane.anchorPane.getStylesheets().add("/css/spellchecking.css");});
		
	}

	private void loadDictionary() throws IOException {
		String[] words = null;
		if(DataCache.getInstance().fetch(ENGLISH_WORDS) == null) {
		     words = FileUtils.readFileToString(new File(getDataFolder()+"English_words.txt"),StandardCharsets.UTF_8).split("\n");
			DataCache.getInstance().insert(ENGLISH_WORDS, words);
		}
		words = (String[])DataCache.getInstance().fetch(ENGLISH_WORDS);
		
		dictionary.addAll(Arrays.asList(words));
	}
	private void loadToolBarNodes(ToolBar bar) {
		Label field = new Label("Default Dictionary Loaded.");
		field.setPrefWidth(Region.USE_COMPUTED_SIZE);
		bar.getItems().add(field);
		JFXButton  button = new JFXButton(Labels.SpellChecker.BUTTON_CHANGE);
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Data", "*.txt"));
				File data = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
				if(null != data && data.exists()) {
					
				String[] words = null;
				try {
					words = FileUtils.readFileToString(data,StandardCharsets.UTF_8).split("\n");
					if(null != words) {
					     DataCache.getInstance().insert(ENGLISH_WORDS, words);
					      dictionary.clear();
					     dictionary.addAll(Arrays.asList(words));
					     field.setText("Loaded "+data.getAbsolutePath());  
					}
				} catch (IOException e) {
				//
				}
			

			}
		}
		});
		
		bar.getItems().add(button);
	}


    private static StyleSpans<Collection<String>> computeHighlighting(String text) {
        
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
        
        BreakIterator wb = BreakIterator.getWordInstance();
        wb.setText(text);

        int lastIndex = wb.first();
        int lastKwEnd = 0;
        while(lastIndex != BreakIterator.DONE) {
            int firstIndex = lastIndex;
            lastIndex = wb.next();

            if (lastIndex != BreakIterator.DONE
                && Character.isLetterOrDigit(text.charAt(firstIndex))) {
                String word = text.substring(firstIndex, lastIndex).toLowerCase(Locale.ENGLISH);
                if (!dictionary.contains(word)) {
                    spansBuilder.add(Collections.emptyList(), firstIndex - lastKwEnd);
                    spansBuilder.add(Collections.singleton("underlined"), lastIndex - firstIndex);
                    lastKwEnd = lastIndex;
                }
            }
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);

        return spansBuilder.create();
    }

	
	@Override
	public Node view() throws ToolError {
		return anchorPanePlane;
	}

}
