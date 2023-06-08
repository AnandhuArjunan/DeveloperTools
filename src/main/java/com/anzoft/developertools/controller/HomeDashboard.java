/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.controller;

import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Date;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.SystemUtils;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;
import org.controlsfx.control.textfield.TextFields;

import com.anzoft.developertools.app.UIResources;
import com.anzoft.developertools.app.headerpanel.ShowPriority;
import com.anzoft.developertools.app.headerpanel.message.NotifyMessage;
import com.anzoft.developertools.controller.home.AddToolController;
import com.anzoft.developertools.controller.toolloaders.ToolLoaderFactory;
import com.anzoft.developertools.database.persistables.ToolActivityHistory;
import com.anzoft.developertools.database.persistables.ToolBookmark;
import com.anzoft.developertools.database.persistables.Tools;
import com.anzoft.developertools.database.services.DBService;
import com.anzoft.developertools.database.services.LogToolBookmarkService;
import com.anzoft.developertools.database.services.ServiceArguments;
import com.anzoft.developertools.database.services.common.FindEntitiesByQuery;
import com.anzoft.developertools.database.services.common.GetAllEntityService;
import com.anzoft.developertools.exceptions.InternalError;
import com.anzoft.developertools.exceptions.ServiceException;
import com.anzoft.developertools.exceptions.ToolError;
import com.anzoft.developertools.function.Action;
import com.anzoft.developertools.services.background.AbstractBackgroundService;
import com.anzoft.developertools.services.background.IntervalExecuteCustomService;
import com.anzoft.developertools.services.background.ServiceExecuter;
import com.anzoft.developertools.ui.components.homepage.InfoPane;
import com.anzoft.developertools.ui.components.homepage.ToolBlock;
import com.anzoft.developertools.utils.FxComponentUtils;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXPopup.PopupHPosition;
import com.jfoenix.controls.JFXPopup.PopupVPosition;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.PopupWindow.AnchorLocation;
import javafx.util.Duration;

public class HomeDashboard implements Initializable {

	@FXML
	private GridPane rctools;

	@FXML
	private GridPane customtools;

	@FXML
	private VBox rightPane;
	
	private AddToolController addTool;
	
	  @FXML
	   private TextField search;
	  
	  private List<Tools> tools = null;
	  
	        PopOver popOver = new PopOver();


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	        tools =   getTools(); 
	    
	        startNotfificationHistorySrevice();

		fillBookMarkPaneInitial();

		fillBookMarkPane();
	
		fillinfoPane();
		
		 
		 search.textProperty().addListener((o,c,newText)->{
		     buildSearchContextMenu(newText);
		    popOver.setArrowLocation(ArrowLocation.TOP_CENTER);
		     popOver.show(search);
		 });

	}

	
	
	private void startNotfificationHistorySrevice() {
	    Action e =()->Platform.runLater(()->fillrecentTools());

		IntervalExecuteCustomService customService = new IntervalExecuteCustomService(Duration.millis(20000), Duration.millis(0), "Home Dashboard History","",  e);
		try {
		    ServiceExecuter.startService(customService);
		} catch (InternalError e1) {
		    NotifyMessage.notifyWarning("Home Dashboard History Service Not loaded Properly",ShowPriority.HIGH_PRIORITY);
		}
	}
	
	List<Tools> getTools(){
		GetAllEntityService<Tools>  db = new GetAllEntityService<>();
		try {
		    return  db.getAllRecords(Tools.class);
		} catch (ServiceException e) {
		    e.printStackTrace();
		}
		return Collections.emptyList();
	}
	 public PopOver buildSearchContextMenu(String search) {
	        popOver.setContentNode(getSearchItemsList(search));
	        return popOver;
	    }

	    private Region getSearchItemsList(String search) {
	    	List<Tools> filteredTools = tools.stream().filter(predicate->  predicate.getToolName().toLowerCase().startsWith(search.toLowerCase())).collect(Collectors.toList());

	    	
	    	if(null == filteredTools || filteredTools.isEmpty()) {
		        VBox boxItem = new VBox( new Label("No Results !"));
		        boxItem.setPadding(new Insets(30));
		        boxItem.setAlignment(Pos.CENTER);
		        new Label("Add File").setPadding(new Insets(20));
		        return boxItem;
	    	}else {
	    	
	    	
	        HBox boxItem = new HBox();
	        VBox toolName = new VBox(new Label("Name"));
	        VBox toolID = new VBox(new Label("ID"));
	        VBox toolCategory = new VBox(new Label("Category"));
	        VBox toolType = new VBox(new Label("Type"));
	        filteredTools.forEach(tool->{
	            Label toolNm = new Label(tool.getToolName());
	            toolNm.setTextFill(Color.DODGERBLUE);
	            toolNm.setUnderline(true);
	            toolNm.setOnMouseClicked(e->{
	        	ToolLoaderFactory factory = UIResources.getInstance().getTabLoader();
	        	
			    factory.loadTool(tool.getId());
			    popOver.hide();
			
			
	            });
	            toolName.getChildren().add(toolNm);
	            toolID.getChildren().add(new Label(tool.getId()));
	            if(null != tool.getCategory()) {
		            toolCategory.getChildren().add(new Label(tool.getCategory().getCategoryName()));
	            }
	            toolType.getChildren().add(new Label(tool.getToolType().getTypeName()));
	            
	        });
	        boxItem.getChildren().addAll(toolName,toolID,toolCategory,toolType);
	        boxItem.setPadding(new Insets(20));
	        boxItem.setSpacing(10);
	        return boxItem;
	    	}
	    }
	
	
	
	private void fillBookMarkPane() {
		List<ToolBookmark> bookmarks = getBookmarks();
		if(null != bookmarks && !bookmarks.isEmpty()) {
			for(ToolBookmark bookmark : bookmarks) {
				FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.ALIGN_JUSTIFY);
				icon.setSize("20px");
				Label label = new Label(bookmark.getTools().getToolName());
				ToolBlock block = new ToolBlock(bookmark.getTools().getId(), icon,"#d4e157", label, this::onClickBookmark);
				customtools.add(block, bookmark.getToolPosition(), 0);
			}
		}
	}
	
	private void fillBookMarkPaneInitial() {
		for(int i=0;i<6;i++) {
			FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.PLUS_SQUARE);
			icon.setSize("20px");
			Label label = new Label("Add Tool");
			ToolBlock block = new ToolBlock(String.valueOf(i), icon,"#cddc39", label, e -> addTool(e));
			customtools.add(block, i, 0);
		}
	}
	
	private void addTool(MouseEvent e) {
		if(e.getButton().equals(MouseButton.PRIMARY)){
		int id = Integer.parseInt(((ToolBlock)e.getSource()).getId());
		addTool = FxComponentUtils.showAnotherView("/fxml/home/addTool.fxml", "Add Tool");
		addTool.setHome(this);
		addTool.setId(id);
		}
	}
	
	public void addToolAction() throws ServiceException {
		logBookmark(addTool.getTool().getId(), addTool.getId());
		fillBookMarkPane();
	}
	
	private void logBookmark(String toolId,int position) throws ServiceException	 {
		DBService<ToolBookmark> logService = new LogToolBookmarkService();
		ServiceArguments arguments = new ServiceArguments();
		arguments.getArgs().put(DBService.PRIMARY_KEY, toolId);
		arguments.getArgs().put("POSITION", position);
		logService.run(arguments);
	}

	private List<ToolBookmark> getBookmarks(){
		GetAllEntityService<ToolBookmark> dbService = new GetAllEntityService<>();
		try {
			return dbService.getAllRecords(ToolBookmark.class);
		} catch (ServiceException e) {
			return Collections.emptyList();
		}
	}

	void fillrecentTools() {
		List<ToolActivityHistory> history = getToolHistory();
		Deque<ToolActivityHistory> activityHistories = new ArrayDeque<>();
		activityHistories.addAll(history);
		if (!activityHistories.isEmpty()) {
			for (int j = 0; j < 6; j++) {
				if (activityHistories.isEmpty()) {
					break;
				}
				for (int i = 0; i < 4; i++) {
					if (activityHistories.isEmpty()) {
						break;
					}
					FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.ALIGN_JUSTIFY);
					icon.setSize("40px");
					ToolActivityHistory activityHistory = activityHistories.pop();
					Label label = new Label(activityHistory.getTools().getToolName());
					ToolBlock block = new ToolBlock(activityHistory.getTools().getId(), icon,"#64b5f6", label, this::onClickTool);
					rctools.add(block, i, j);

				}

			}
		}
	}

	private void onClickTool(MouseEvent e) {
		String id = ((ToolBlock) e.getSource()).getId();
		ToolLoaderFactory tabLoader = UIResources.getInstance().getTabLoader();
		tabLoader.loadTool(id);
	}
	
	private void onClickBookmark(MouseEvent e) {
		
		if(e.getButton() == MouseButton.PRIMARY) {
			onClickTool(e);
		}else if(e.getButton() == MouseButton.SECONDARY) {
			ToolBlock block = (ToolBlock) e.getSource();
			ContextMenu contextMenu = new ContextMenu();
			  
	        MenuItem menuItem1 = new MenuItem("Delete");

	        contextMenu.getItems().add(menuItem1);

	     
	        block.setContextmenu(contextMenu);
		}
		
	}

	List<ToolActivityHistory> getToolHistory() {
		FindEntitiesByQuery<List<ToolActivityHistory>> dbService = new FindEntitiesByQuery<>();
		List<ToolActivityHistory> activityHistories = dbService
				.executeHQL("FROM ToolActivityHistory T ORDER BY T.toolAcivityDate DESC", null);
		if (null != activityHistories && !activityHistories.isEmpty()) {
			return activityHistories.stream().filter(distinctByKey(t -> t.getTools().getId()))
					.collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	void fillinfoPane() {
		rightPane.getChildren().add(getUserName());
		rightPane.getChildren().add(getIP());
		rightPane.getChildren().add(getMemoryUsage());
		rightPane.getChildren().add(getCPUUsage());
		rightPane.getChildren().add(getUsedDiskSpace());
		rightPane.getChildren().add(getOsName());
		rightPane.getChildren().add(getJavaHome());
		rightPane.getChildren().add(getDay());
		rightPane.getChildren().add(getTime());

	}

	InfoPane getOsName() {
		MaterialIconView icon = new MaterialIconView(MaterialIcon.LAPTOP);

		Label label = new Label(com.anzoft.commonlibs.system.SystemUtils.getOsName());
		return new InfoPane(icon, label, e -> action());

	}

	InfoPane getTime() {
		MaterialIconView icon = new MaterialIconView(MaterialIcon.TIMER);
		icon.setSize("20px");

		Label label = new Label();

		Timeline digitaltimeline = new Timeline(
				new KeyFrame(Duration.millis(1), e -> label.setText("Time - " + getFormattedTimeValue()))

		);

		digitaltimeline.setCycleCount(Animation.INDEFINITE);
		digitaltimeline.play();

		return new InfoPane(icon, label, e -> action());

	}

	private String getFormattedTimeValue() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a z");
		return dateFormat.format(new Date());
	}

	InfoPane getDay() {
		FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.CALENDAR);

		Label label = new Label();

		Timeline digitaltimeline = new Timeline(
				new KeyFrame(Duration.millis(1), e -> label.setText("Day - " + getFormattedDayValue()))

		);

		digitaltimeline.setCycleCount(Animation.INDEFINITE);
		digitaltimeline.play();

		return new InfoPane(icon, label, e -> action());

	}

	private String getFormattedDayValue() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		return dateFormat.format(new Date());
	}

	InfoPane getJavaHome() {
		MaterialIconView icon = new MaterialIconView(MaterialIcon.APPS);

		Label label = new Label("JAVA_HOME - " + SystemUtils.getJavaHome().getPath());
		return new InfoPane(icon, label, e -> action());

	}

	InfoPane getIP() {
		FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.GLOBE);

		Label label = new Label(com.anzoft.commonlibs.system.SystemUtils.getIP());
		return new InfoPane(icon, label, e -> action());

	}

	InfoPane getUserName() {
		FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.USER);
		Label label = new Label(SystemUtils.USER_NAME);
		return new InfoPane(icon, label, e -> action());
	}

	InfoPane getMemoryUsage() {
		Label memoryUsage = new Label();
		MaterialIconView icon = new MaterialIconView(MaterialIcon.MEMORY);
		Timeline memorytimeline = new Timeline(new KeyFrame(Duration.millis(1),
				e -> memoryUsage.setText("Memory - " + getFormattedMemoryValue() + "%"))

		);

		memorytimeline.setCycleCount(Animation.INDEFINITE);
		memorytimeline.play();

		return new InfoPane(icon, memoryUsage, e -> action());

	}

	private String getFormattedCPUValue() {
		double cpu = 0;
		cpu = com.anzoft.commonlibs.system.SystemUtils.getCPUUsage();
		if (Double.isNaN(cpu)) { // redundant checking to see if value is equal to NaN, if so set the value to 0
			cpu = 0;
		}
		double multiplycpu = cpu * 100;
		DecimalFormat decformat = new DecimalFormat("#0.00");
		return decformat.format(multiplycpu);

	}

	InfoPane getCPUUsage() {
		MaterialIconView icon = new MaterialIconView(MaterialIcon.VIEW_QUILT);
		Label digitalDisplay = new Label();
		Timeline digitaltimeline = new Timeline(new KeyFrame(Duration.millis(1), e -> {
			digitalDisplay.setText("CPU - " + getFormattedCPUValue() + "%");
		})

		);

		digitaltimeline.setCycleCount(Animation.INDEFINITE);
		digitaltimeline.play();
		return new InfoPane(icon, digitalDisplay, e -> action());

	}

	private InfoPane getUsedDiskSpace() { // note: this is getting space for the main C: drive, it does not factor
											// additional drives
		MaterialIconView icon = new MaterialIconView(MaterialIcon.STORAGE);
		Label digitalDisplay = new Label();
		long totalspace;
		totalspace = new File("/").getTotalSpace();
		long freespace;
		freespace = new File("/").getFreeSpace();

		double usedspace = (totalspace - freespace);
		double currentspace = (usedspace / totalspace) * 100;

		DecimalFormat decformat = new DecimalFormat("#0.00");
		String formattedspace = decformat.format(currentspace);

		digitalDisplay.setText("Storage(OS) - " + formattedspace + "%");

		return new InfoPane(icon, digitalDisplay, null);
	}

	private String getFormattedMemoryValue() {
		long freemem = 0;
		freemem = com.anzoft.commonlibs.system.SystemUtils.getFreeMemoryUsage();
		long totalmem = 0;
		totalmem = com.anzoft.commonlibs.system.SystemUtils.getTotalMemory();
		double usedmem = (totalmem - freemem);
		double currentmem = (usedmem / totalmem) * 100;
		DecimalFormat decformat = new DecimalFormat("#0.00");
		return decformat.format(currentmem);

	}

	void action() {

	}

}
