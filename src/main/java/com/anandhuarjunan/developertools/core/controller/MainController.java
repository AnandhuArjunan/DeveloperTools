/*
 * 
 */
package com.anandhuarjunan.developertools.core.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.anandhuarjunan.developertools.core.app.UIResources;
import com.anandhuarjunan.developertools.core.app.config.global.GlobalConfig;
import com.anandhuarjunan.developertools.core.app.headerpanel.DisplayPanelContent;
import com.anandhuarjunan.developertools.core.app.headerpanel.message.NotifyMessage;
import com.anandhuarjunan.developertools.core.app.plugin.PluginLoaderFactory;
import com.anandhuarjunan.developertools.core.app.theme.ThemeManager;
import com.anandhuarjunan.developertools.core.controller.custom.filereader.FileReader;
import com.anandhuarjunan.developertools.core.controller.handlers.closehandler.ToolCloseHandler;
import com.anandhuarjunan.developertools.core.controller.home.FooterViewController;
import com.anandhuarjunan.developertools.core.controller.servicemanager.ServiceManager;
import com.anandhuarjunan.developertools.core.controller.toolloaders.ToolLoaderFactory;
import com.anandhuarjunan.developertools.core.controller.toolloaders.custom.CustomFXMLLoaderFactory;
import com.anandhuarjunan.developertools.core.controller.toolloaders.custom.CustomViewLoaderFactory;
import com.anandhuarjunan.developertools.core.database.persistables.Category;
import com.anandhuarjunan.developertools.core.database.persistables.Tools;
import com.anandhuarjunan.developertools.core.database.services.common.GetAllEntityService;
import com.anandhuarjunan.developertools.core.exceptions.InternalError;
import com.anandhuarjunan.developertools.core.exceptions.LoggerError;
import com.anandhuarjunan.developertools.core.exceptions.ServiceException;
import com.anandhuarjunan.developertools.core.exceptions.ToolError;
import com.anandhuarjunan.developertools.core.exceptions.ValidateError;
import com.anandhuarjunan.developertools.core.logging.LoggerFactory;
import com.anandhuarjunan.developertools.core.utils.DialogBox;
import com.anandhuarjunan.developertools.core.utils.FXWindowUtils;
import com.anandhuarjunan.developertools.core.utils.PluginLoaderUtils;
import com.anandhuarjunan.developertools.core.views.ClosableTool;
import com.anandhuarjunan.developertools.core.views.CustomFxml;
import com.anandhuarjunan.developertools.core.views.FxmlLoaderTool;
import com.anandhuarjunan.developertools.core.views.constants.FileReaderTypeControllerMapping;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainController extends DTController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private MenuItem newWindow;

	@FXML
	private MenuItem closeBtn;

	@FXML
	private URL location;

	@FXML
	private Label ssgs;

	@FXML
	public TabPane tabs;

	@FXML
	private MenuBar menuBar;
	
    @FXML
    private HBox toolFooter;

	@FXML
	private AnchorPane mainFrame;

	@FXML
	private Label appname;


	@FXML
	private Menu theme;

	@FXML
	private HBox progressPane;

	@FXML
	private Label byWhom;

	@FXML
	private Label footerCopyright;

	@FXML
	private Label pgText;

	
	
    @FXML
    private Menu toolsMenu;
    
    @FXML
    private MenuItem managePlugin;
    
    @FXML
    private MenuItem manageTool;
    
    @FXML
    private HBox footerHbox;

    @FXML
    private MenuItem opnlic;
    
    @FXML
    private VBox headerPanel;
    
    @FXML
    private MenuItem manageServices;

	private ToolLoaderFactory loader = null;

	private ExecutorService executorService = null;
	
	private List<Tools>  tools;
	
	
	private CustomFXMLLoaderFactory customFXMLLoaderFactory = null;
	
	private FooterViewController footerViewController;

	private CustomViewLoaderFactory customToolLoaderFactory;
	
	private ThemeManager themes = null;
	
	MenuLoader menuLoader = null;
	

	@FXML
	void onClickMenu(ActionEvent event) {
		try {
			openTool(event);
		} catch (ToolError e) {
			NotifyMessage.notifyError("Unable to Open the App !");
			LoggerFactory.getInstance().getLogger().info(e.getMessage()+"\n"+ExceptionUtils.getStackTrace(e));
		} 
		catch(RuntimeException e) {
			NotifyMessage.notifyError("Runtime Error Occured !");
			LoggerFactory.getInstance().getLogger().info(e.getMessage()+"\n"+ExceptionUtils.getStackTrace(e));
		}
		catch(Exception e) {
			NotifyMessage.notifyError("Some error Occured !");
			LoggerFactory.getInstance().getLogger().info(e.getMessage()+"\n"+ExceptionUtils.getStackTrace(e));
		}
		
		
	}

	@FXML
	void onClickClose(ActionEvent event) {
onCloseApplication(event);
	}



	@FXML
	void onMouseClickTab(MouseEvent event) {
		//need to check
	}

	@FXML
	void initialize() {
		try {
			tools = getTools();
			loadToolsInMenu();
			loadGlobalResources();
			//loadThemes();
			loadHomePage();
			loadPlugins();
			//initializeFooter();
			addEvents();
		} catch (Exception e) {
		    NotifyMessage.notifyError("Runtime Error Occured");
		}

	}
	
	

	private void initializeFooter() {
		footerViewController = new FooterViewController(footerHbox);
		
	}
	private void loadToolsInMenu() throws ServiceException {
	    menuLoader = new MenuLoader();
	    menuLoader.loadToolsInMenu();
          }
	
	
	public void loadThemes() throws InternalError {
		themes = new ThemeManager(getScene());
		themes.loadThemesOnMenu(theme);
		themes.loadTheme();
	}
	private void loadGlobalResources() {
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		loader = new ToolLoaderFactory(tabs);
		UIResources.getInstance().setExecutorService(executorService);
		UIResources.getInstance().setTabLoader(loader);
		UIResources.getInstance().setMainController(this);
		UIResources.getInstance().setMenuLoader(menuLoader);
		DisplayPanelContent.setPanel(headerPanel);
		customToolLoaderFactory = new CustomViewLoaderFactory(tabs);
		customFXMLLoaderFactory = new CustomFXMLLoaderFactory(tabs);
	
	}
	
	
	private void addEvents() {
		
		managePlugin.setOnAction(value->{
			loader.loadTool("MANAGE_PLUGIN");
		});
		
		manageTool.setOnAction(value->{
			loader.loadTool("MANAGE_TOOLS");
		});
		manageServices.setOnAction(value->{
		
		CustomFxml customFxml = new CustomFxml("Manage Services", "/fxml/servicemanager/ManageServices.fxml", new ServiceManager());    
		customFXMLLoaderFactory.loadFXML(customFxml);

		  
		});
	}

	private void loadPlugins() throws InternalError {
	    try {
		PluginLoaderFactory.loadAllPlugins();
	    } catch (ServiceException e) {
		throw new InternalError("Error while adding Plugins - ");
	    }
	}

	private void loadHomePage() throws ToolError {

		Optional<Tools> optional = tools.stream().filter(t->t.getId().equalsIgnoreCase("HOME_DASHBOARD")).findFirst();
		
		if(optional.isPresent()) {
			loader.loadTool(optional.get().getId());

		}
		
		
		try {
			FileReader.read(FileReaderTypeControllerMapping.TXT, "NewTextDocument.txt");
		} catch (ValidateError | InternalError e) {
		    NotifyMessage.notifyError(e.getMessage());

		}

	}


	void openTool(ActionEvent event) throws ToolError {
		MenuItem menuItem = ((MenuItem) event.getSource());
		loader.loadTool(menuItem.getId());

		
	}

	private String getToolType(MenuItem menuItem) {
		Optional<Tools> tool = tools.stream().filter(tl->menuItem.getId().equalsIgnoreCase(tl.getId())).findFirst();
		if(tool.isPresent()) {
			return tool.get().getToolType().getId();
		}
		return null;
	}

	// Fired from App.java when the Application stage is being closed.
	public void onCloseApplication(Event ev) {
		
		ToolCloseHandler toolCloseHandler = new ToolCloseHandler(tabs,ev);
		toolCloseHandler.handle();
		
		
		executorService.shutdownNow();
		if(!ev.isConsumed()) {
			System.exit(0);
		}
	}
	
    @FXML
    void onClickLabelEditor(ActionEvent event) {
    	try {
			FXWindowUtils.loadUndecoratedStage("/fxml/tooleditor/ToolEditor.fxml",new Stage());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

       
       
	public Menu getToolsMenu() {
        return toolsMenu;
    }

    public void setToolsMenu(Menu toolsMenu) {
        this.toolsMenu = toolsMenu;
    }

	public HBox getToolFooter() {
		return toolFooter;
	}

	public void setToolFooter(HBox toolFooter) {
		this.toolFooter = toolFooter;
	}

	public FooterViewController getFooterViewController() {
		return footerViewController;
	}

	public void setFooterViewController(FooterViewController footerViewController) {
		this.footerViewController = footerViewController;
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}
	
	List<Tools> getTools() throws ServiceException{
		GetAllEntityService<Tools>  db = new GetAllEntityService<>();
		return  db.getAllRecords(Tools.class);
	}
	
	
	 public void setTools(List<Tools> tools) {
	    this.tools = tools;
	}

	public CustomFXMLLoaderFactory getCustomFXMLLoaderFactory() {
		return customFXMLLoaderFactory;
	}

	public void setCustomFXMLLoaderFactory(CustomFXMLLoaderFactory customFXMLLoaderFactory) {
		this.customFXMLLoaderFactory = customFXMLLoaderFactory;
	}
	




	public class MenuLoader {
	    
	    
	    
	    List<Category> categories = null;
	    
	    public  synchronized void loadToolsInMenu() throws ServiceException {
		toolsMenu.getItems().clear();
	        tools = getTools();
	        categories = getCategories();
		
		
		List<Category> rootCategories = findRootParentCategery(categories).stream().distinct().collect(Collectors.toList());
		for(Category category : rootCategories) {
		    addCategory(category,toolsMenu);
		}
		addTools(toolsMenu,tools.stream().filter(t->t.getCategory() == null).collect(Collectors.toList()));
		
	    }
	    
	    
	    
	    private void addTools(Menu menu,List<Tools> tools) {
		 tools.forEach(tool->{
			 if(tool.isVisible()) {
			 if("E".equalsIgnoreCase(tool.getToolType().getId())) {
				 MenuItem item = new MenuItem(tool.getToolName());
				 FontAwesomeIconView awesomeIconView = new FontAwesomeIconView(FontAwesomeIcon.EXTERNAL_LINK_SQUARE);
				 awesomeIconView.setSize("20px");
				 item.setGraphic(awesomeIconView);
				 item.setId(tool.getId());
				 item.setOnAction(ev->onClickMenu(ev));
				 menu.getItems().add(item);
			 }else if("I".equalsIgnoreCase(tool.getToolType().getId()) || "F".equalsIgnoreCase(tool.getToolType().getId())){
				 
				 MenuItem item = new MenuItem(tool.getToolName());
				 item.setId(tool.getId());
				 item.setOnAction(ev->onClickMenu(ev));
				 menu.getItems().add(item);
			 }
			 } 
			
		 });
	    }
	    
	    
	    private void addCategory(Menu menu,List<Category> categories) {
		if(null != categories && !categories.isEmpty()) {
		    categories.forEach(cat->{
			    Menu menu2 = new Menu(cat.getCategoryName());

			addCategory(menu2, findChildCategery(cat));
			List<Tools> toos = tools.stream().filter(t->null != t.getCategory() &&t.getCategory().equals(cat)).collect(Collectors.toList());
			addTools(menu2,toos);
			    menu.getItems().add(menu2);
			
		    });
		    
		}
	    }
	    
	    
	    
	    private void addCategory(Category category, Menu menu) {
		Menu  menu2 = new Menu(category.getCategoryName());
	
		addCategory(menu2, findChildCategery(category));
		List<Tools> toos = tools.stream().filter(t->null != t.getCategory() &&t.getCategory().equals(category)).collect(Collectors.toList());
		addTools(menu2,toos);
		menu.getItems().add(menu2);
	    }
	    
	    
	    
	    private Category findRootParentCategery(Category category) {
		if(category.getParentCategory() == null) {
		    return category;
		}else {
		   return findRootParentCategery(category.getParentCategory());
		}
	    }

	    private List<Category> findRootParentCategery(List<Category> categories) {
		List<Category> rootCategories = new ArrayList<>();
		for(Category category : categories) {
		    rootCategories.add(findRootParentCategery(category));
		}
		return rootCategories;
	    } 
	    
	    private List<Category> findChildCategery(Category category) {
		return  categories.stream().filter(cat->cat.getParentCategory()==category)	.collect(Collectors.toList());
	    }
		    
	
	    private List<Category> getCategories() throws ServiceException{
		GetAllEntityService<Category> dbService = new GetAllEntityService<>();
		return dbService.getAllRecords(Category.class);
	}
	    
	    
	}

}
