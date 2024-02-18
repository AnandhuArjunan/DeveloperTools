package com.anandhuarjunan.developertools.views;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import java.util.*;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.anandhuarjunan.developertools.database.entity.Plugins;
import com.anandhuarjunan.developertools.database.entity.Tools;
import com.anandhuarjunan.developertools.database.services.common.FindEntitiesByQuery;
import com.anandhuarjunan.developertools.database.services.common.FindEntityByFieldsService;
import com.anandhuarjunan.developertools.database.services.common.GetAllEntityService;
import com.anandhuarjunan.developertools.exceptions.ServiceException;
import com.anandhuarjunan.developertools.helper.ToolLoader;
import com.anandhuarjunan.developertools.utils.HibernateUtils;

import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXRectangleToggleNode;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.utils.ToggleButtonsUtil;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.application.Platform;
import javafx.stage.Popup;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import java.io.PrintWriter;
public class MainPresenter implements Initializable {


	@Inject
	private Stage stage;

	   @FXML
	    private BorderPane contentPane;

	    @FXML
	    private BorderPane root;

	    @FXML
	    private MenuItem theme;

	    @FXML
	    private Menu toolsMenu;

	    @FXML
	    private Tab twst;
	    

	    @FXML
	    private TabPane tabPane;
	    
	    private ToolLoader toolLoader = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			toolLoader = new ToolLoader(tabPane);
			loadToolsInMenu();
			loadDashboard();
		}catch (ServiceException e) {
			
		}

		
	}

	private void loadDashboard() {
		DashboardView dashboardView = new DashboardView();
		toolLoader.addTab("Dashboard", dashboardView.getView(),false,null);
		
	}

	private void loadToolsInMenu() throws ServiceException {
	    List<Plugins> pluginsList = getAllPluginsWithTools();

	    pluginsList.forEach(plugin -> {
	        Menu pluginMenu = new Menu(plugin.getName());
	        List<Tools> toolsList;
			try {
				toolsList = getToolsList(plugin);
				  if (toolsList != null && !toolsList.isEmpty()) {
			            toolsList.forEach(tool -> {
			                String categories = tool.getToolCategory();

			                if (categories != null && !categories.isEmpty()) {
			                    String[] categoryArray = categories.split(",");
			                    Arrays.stream(categoryArray)
			                            .map(category -> createSubMenu(pluginMenu, category))
			                            .forEach(categoryMenu -> {
			                                MenuItem menuItem = createMenuItem(tool);
			                                categoryMenu.getItems().add(menuItem);
			                                menuItem.setOnAction(ev -> toolLoader.loadTool(tool));
			                            });
			                } else {
			                    MenuItem menuItem = createMenuItem(tool);
			                    pluginMenu.getItems().add(menuItem);
			                    menuItem.setOnAction(ev -> toolLoader.loadTool(tool));
			                }
			            });
			        }

			        toolsMenu.getItems().add(pluginMenu);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	      
	    });
	}

	private Menu createSubMenu(Menu parentMenu, String submenuName) {
	    return parentMenu.getItems().stream()
	            .filter(item -> item instanceof Menu && item.getText().equals(submenuName))
	            .map(item -> (Menu) item)
	            .findFirst()
	            .orElseGet(() -> new Menu(submenuName));
	}

	private MenuItem createMenuItem(Tools tool) {
	    MenuItem menuItem = new MenuItem(tool.getToolName());
	    menuItem.setUserData(tool);
	    return menuItem;
	}
	private List<Plugins> getAllPluginsWithTools() throws ServiceException {
		
        return new GetAllEntityService<Plugins>().getAllRecords(Plugins.class);
    }


	private List<Tools> getToolsList(Plugins plugin) throws ServiceException {
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        String hql = "FROM Tools t WHERE t.toolPlgin = :plugin";
	        Query<Tools> query = session.createQuery(hql, Tools.class);
	        query.setParameter("plugin", plugin);
	        return query.list();
	    } catch (Exception e) {
	        throw new ServiceException("Failed to get tools list");
	    }
	}

	

}
