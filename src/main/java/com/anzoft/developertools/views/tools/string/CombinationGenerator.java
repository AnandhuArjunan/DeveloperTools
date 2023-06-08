/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.views.tools.string;

public class CombinationGenerator{
	/*
	JFXButton plus = new JFXButton("+");
    protected final Label elements = new Label();
    protected final AnchorPane anchorPaneMaster = new AnchorPane();
    protected final Label connections = new Label();
    protected final AnchorPane anchorPaneDataSet1 = new AnchorPane();
    protected final Label combinations = new Label();
    protected final AnchorPane anchorPaneDataSet2 = new AnchorPane();
    protected final ToolBar toolBar = new ToolBar();

	

	public void init() throws ToolError {
		
		    anchorPaneMaster.setPadding(new Insets(5.0));
		    AnchorPane.setBottomAnchor(elements, 0.0);
	        AnchorPane.setLeftAnchor(elements, 0.0);
	        AnchorPane.setRightAnchor(elements, 0.0);
	        AnchorPane.setTopAnchor(elements, 0.0);
	        elements.setAlignment(javafx.geometry.Pos.CENTER);
	        elements.setText("Elements");
	        anchorPaneMaster.getChildren().add(elements);
        masterBox.getChildren().add(0,anchorPaneMaster);
        masterBox.getChildren().add(1,toolBar);

        
        
        anchorPaneDataSet1.setPadding(new Insets(5.0));
	    AnchorPane.setBottomAnchor(connections, 0.0);
        AnchorPane.setLeftAnchor(connections, 0.0);
        AnchorPane.setRightAnchor(connections, 0.0);
        AnchorPane.setTopAnchor(connections, 0.0);
        connections.setAlignment(javafx.geometry.Pos.CENTER);
        connections.setText("Connections");
        anchorPaneDataSet1.getChildren().add(connections);
    datasetBox1.getChildren().add(0,anchorPaneDataSet1);
    
    anchorPaneDataSet2.setPadding(new Insets(5.0));
    AnchorPane.setBottomAnchor(combinations, 0.0);
    AnchorPane.setLeftAnchor(combinations, 0.0);
    AnchorPane.setRightAnchor(combinations, 0.0);
    AnchorPane.setTopAnchor(combinations, 0.0);
    combinations.setAlignment(javafx.geometry.Pos.CENTER);
    combinations.setText("Combinations");
    anchorPaneDataSet2.getChildren().add(combinations);
datasetBox2.getChildren().add(0,anchorPaneDataSet2);
		
		//addToolsToToolbar(plus);
		plus.setOnAction(new ActionEventHandler());
	}
	
	
	
	
	

	@Override
	public void process() throws InternalError, ValidateError {
		
		dataset1.getItems().clear();
		for(Object node : master.getItems()) {
			TextField textField = (TextField)node;
			if(!StringUtils.isEmpty(textField.getText())) {
				dataset1.getItems().add(((TextField)node).getText());
			}
		}
		
		for(int i = 0;i<master.getItems().size();i++) {
			for(int ii = i;ii<master.getItems().size()-1;ii++) {
				dataset1.getItems().add(((TextField)master.getItems().get(i)).getText()+ " X " +((TextField)master.getItems().get(ii+1)).getText());
			}
		}
		
		
			}

	@Override
	public ToolMetaData metadata() {
		return null;
	}
	
	class ActionEventHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			DTTextField textField = new DTTextField();
			//fillListsViews(master, textField);
			textField.textProperty().addListener(new ChangeEventHandler());
			
		}
		
	}
	
	class ChangeEventHandler implements ChangeListener<String>{



		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			try {
				process();
			} catch (ValidateError | InternalError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	
		
	}
	
	
	*/

}
