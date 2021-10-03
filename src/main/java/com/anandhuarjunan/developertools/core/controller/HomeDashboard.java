/*
 * 
 */
package com.anandhuarjunan.developertools.core.controller;

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

import com.anandhuarjunan.developertools.core.app.UIResources;
import com.anandhuarjunan.developertools.core.app.headerpanel.ShowPriority;
import com.anandhuarjunan.developertools.core.app.headerpanel.message.NotifyMessage;
import com.anandhuarjunan.developertools.core.controller.home.AddToolController;
import com.anandhuarjunan.developertools.core.controller.toolloaders.ToolLoaderFactory;
import com.anandhuarjunan.developertools.core.database.persistables.ToolActivityHistory;
import com.anandhuarjunan.developertools.core.database.persistables.ToolBookmark;
import com.anandhuarjunan.developertools.core.database.persistables.Tools;
import com.anandhuarjunan.developertools.core.database.services.DBService;
import com.anandhuarjunan.developertools.core.database.services.LogToolBookmarkService;
import com.anandhuarjunan.developertools.core.database.services.ServiceArguments;
import com.anandhuarjunan.developertools.core.database.services.common.FindEntitiesByQuery;
import com.anandhuarjunan.developertools.core.database.services.common.GetAllEntityService;
import com.anandhuarjunan.developertools.core.exceptions.InternalError;
import com.anandhuarjunan.developertools.core.exceptions.ServiceException;
import com.anandhuarjunan.developertools.core.function.Action;
import com.anandhuarjunan.developertools.core.services.background.IntervalExecuteCustomService;
import com.anandhuarjunan.developertools.core.services.background.ServiceExecuter;
import com.anandhuarjunan.developertools.core.ui.components.homepage.InfoPane;
import com.anandhuarjunan.developertools.core.ui.components.homepage.ToolBlock;
import com.anandhuarjunan.developertools.core.utils.FxComponentUtils;
import com.anandhuarjunan.developertools.core.views.FxmlLoaderTool;

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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class HomeDashboard extends FxmlLoaderTool implements Initializable {

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
	  @FXML
	    private VBox infobox;

	   
	    @FXML
	    private Label username;

	  
	        PopOver popOver = new PopOver();


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	        tools =   getTools(); 
	    
	        startNotfificationHistorySrevice();

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
		customtools.getChildren().clear();

		
		List<ToolBookmark> bookmarks = getBookmarks();
		
		
		
		
		
		for(int i = 0;i<6;i++) {
			ToolBookmark bookmark = null;
			try {
				bookmark = bookmarks.get(i);
			}catch (IndexOutOfBoundsException e) {
			}
			if(null != bookmark) {
				FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.ALIGN_JUSTIFY);
				icon.setSize("19px");
				Label label = new Label(bookmark.getTools().getToolName());
				ToolBlock block = new ToolBlock(bookmark.getTools().getId(), icon,"#cddc39", label, this::onClickBookmark);
				block.setPadding(new Insets(10));

				customtools.getChildren().remove(FxComponentUtils.getNodeFromGridPane(customtools, bookmark.getToolPosition(), 0));
				customtools.add(block, bookmark.getToolPosition(), 0);
			}else {
				FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.PLUS_SQUARE);
				icon.setSize("19px");
				Label label = new Label("Add Tool");
				ToolBlock block = new ToolBlock(String.valueOf(i), icon,"#cddc39", label, e -> addTool(e));
				block.setPadding(new Insets(10));
				customtools.add(block, i, 0);
			}
			
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


	
	List<ToolBookmark> getBookmarks() {
		FindEntitiesByQuery<List<ToolBookmark>> dbService = new FindEntitiesByQuery<>();
		List<ToolBookmark> activityHistories = dbService
				.executeHQL("FROM ToolBookmark T ORDER BY T.toolPosition ASC", null);
		
		return activityHistories;
	}

	void fillrecentTools() {
		List<ToolActivityHistory> history = getToolHistory();
		Deque<ToolActivityHistory> activityHistories = new ArrayDeque<>();
		rctools.getChildren().clear();
		activityHistories.addAll(history);
		if (!activityHistories.isEmpty()) {
			for (int j = 0; j < 4; j++) {
				if (activityHistories.isEmpty()) {
					break;
				}
				for (int i = 0; i < 6; i++) {
					if (activityHistories.isEmpty()) {
						break;
					}
					ImageView imageView = FxComponentUtils.loadImage("images/3d.png");
					imageView.setPreserveRatio(true);
					imageView.setFitHeight(48);
					imageView.setFitWidth(48);
					ToolActivityHistory activityHistory = activityHistories.pop();
					Label label = new Label(activityHistory.getTools().getToolName());
					ToolBlock block = new ToolBlock(activityHistory.getTools().getId(), imageView,"#eeeeee", label, this::onClickTool);
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
		username.setText(SystemUtils.USER_NAME);
		infobox.getChildren().add(getIP());
	//	rightPane.getChildren().add(getMemoryUsage());
	//	rightPane.getChildren().add(getCPUUsage());
	//	rightPane.getChildren().add(getUsedDiskSpace());
		infobox.getChildren().add(getOsName());
		infobox.getChildren().add(getJavaHome());
		infobox.getChildren().add(getDay());
		infobox.getChildren().add(getTime());
		infobox.getChildren().add(getOsName());
		infobox.getChildren().add(getJavaHome());
		infobox.getChildren().add(getDay());
		infobox.getChildren().add(getTime());
		infobox.getChildren().add(getOsName());
		infobox.getChildren().add(getJavaHome());
		infobox.getChildren().add(getDay());
		infobox.getChildren().add(getTime());
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
