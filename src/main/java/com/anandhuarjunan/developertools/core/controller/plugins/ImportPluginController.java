/*
 * 
 */
package com.anandhuarjunan.developertools.core.controller.plugins;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.zeroturnaround.zip.ZipUtil;

import com.anandhuarjunan.developertools.core.app.headerpanel.message.NotifyMessage;
import com.anandhuarjunan.developertools.core.database.persistables.Category;
import com.anandhuarjunan.developertools.core.database.persistables.FxmlLoaderTools;
import com.anandhuarjunan.developertools.core.database.persistables.ToolTypes;
import com.anandhuarjunan.developertools.core.database.persistables.Tools;
import com.anandhuarjunan.developertools.core.database.persistables.app.FXMLAddOn;
import com.anandhuarjunan.developertools.core.database.services.common.FindEntityService;
import com.anandhuarjunan.developertools.core.database.services.common.GetAllEntityService;
import com.anandhuarjunan.developertools.core.database.services.common.PersistEntity;
import com.anandhuarjunan.developertools.core.exceptions.InternalError;
import com.anandhuarjunan.developertools.core.exceptions.ServiceException;
import com.anandhuarjunan.developertools.core.exceptions.ValidateError;
import com.anandhuarjunan.developertools.core.utils.FileUtils;
import com.anandhuarjunan.developertools.core.utils.FxComponentUtils;
import com.anandhuarjunan.developertools.core.utils.HibernateUtils;
import com.anandhuarjunan.developertools.core.utils.PropertyUtils;
import com.anandhuarjunan.developertools.core.utils.StringUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.FileChooser;

public class ImportPluginController {


	    @FXML
	    private TextField filelocation1;

	    @FXML
	    private Button browse1;

	    @FXML
	    private TreeView<Category> categoryShow;

	    @FXML
	    private Button importButton;
	    
	    private List<Category> categories = null;
    
	    private File loadedAddon = null;
	    
	    public static final String FXML_ADD_ON_LOCATION = "plugins/fxml/";
	    
	    public static final String PROPERTY_FILE_NAME = "DT_PROPERTIES.properties";

    
	    private Properties properties  = null;
	    
	    
    @FXML
    void initialize() {
    	categories = getCategories();
    	//category.getItems().addAll(FXCollections.observableArrayList(categories));
    	//generateBtn.setOnAction(ev->importPlugin());
    	//browse.setOnAction(ev->choosePlugin(ev));
    	//pluginName.textProperty().addListener(listener->pluginID.setText(pluginName.getText().toUpperCase()));
    	
    	
    	fillCategoryView();
    	
    	browse1.setOnAction(this::loadDtetFile);
    	importButton.setOnAction(this::startImport);
    }



    void loadDtetFile(ActionEvent  actionEvent){
    	FileChooser chooser = FxComponentUtils.getFileChooser("FXML - Add On", "*.dtet");
    	loadedAddon = chooser.showOpenDialog(((Node)actionEvent.getSource()).getScene().getWindow());
    }
    
    
    
    void startImport(ActionEvent  actionEvent){
    	Session session = HibernateUtils.getSessionFactory().openSession();

    	try {
	    	validateFile();
	    	byte[] bytes = ZipUtil.unpackEntry(loadedAddon, PROPERTY_FILE_NAME);
	    	String content = new String(bytes,StandardCharsets.UTF_8);
	    	properties =  PropertyUtils.parsePropertiesString(content);
	    	session.getTransaction().begin();
	    	storePluginInfo(session);
	    	copyFileToPluginsFolder();
	    	session.getTransaction().commit();

	    	NotifyMessage.notifyInfo("Successfully Imported, Restart the System to Enable", 1);
    	}
    	catch(Exception e) {
	    	session.getTransaction().rollback();

    		e.printStackTrace();
	    	NotifyMessage.notifyError("Failed to Import the File !", 1);

    	}
    	finally{
    		session.close();
    	}
    }
    


    private void storePluginInfo(Session session) throws IOException, ServiceException {
    	
    	
    	
    	
    
    	
		byte[] bytes = ZipUtil.unpackEntry(loadedAddon, PROPERTY_FILE_NAME);
    	String content = new String(bytes,StandardCharsets.UTF_8);
    	Properties properties =  PropertyUtils.parsePropertiesString(content);
    	
    	
    	boolean isClosable = null != properties.getProperty("CLOSABLE") && properties.getProperty("CLOSABLE").equalsIgnoreCase("Y");
    	
    	Tools tools = new Tools();
		tools.setClosable(isClosable);
		tools.setDescription(properties.getProperty("CLOSABLE"));
		tools.setCategory(categoryShow.getSelectionModel().getSelectedItem().getValue());
		tools.setMultiTabSupport(null != properties.getProperty("MULTI_TAB") && properties.getProperty("MULTI_TAB").equalsIgnoreCase("Y"));
		tools.setVisible(null != properties.getProperty("SHOW_IN_MENU") && properties.getProperty("SHOW_IN_MENU").equalsIgnoreCase("Y"));
		FindEntityService<ToolTypes> dbServiceq = new FindEntityService<>();

	
		tools.setToolType(dbServiceq.findEntity(ToolTypes.class, "F").getResult());
		
		String id = StringUtils.appendWords("_",properties.getProperty("COMPANY_ID"),properties.getProperty("NAME"),properties.getProperty("VERSION") );
		tools.setId(id);		
		tools.setToolName(properties.getProperty("NAME"));
		
		FxmlLoaderTools fxmlLoaderTools = new FxmlLoaderTools();
		fxmlLoaderTools.setTools(tools);
		
	
		fxmlLoaderTools.setFxmlPath(properties.getProperty("FXML_RELATIVE_LOC"));
		fxmlLoaderTools.setStylesheets(properties.getProperty("CSS_RELATIVE_LOC"));

	
    	
    	FXMLAddOn addOn = new FXMLAddOn();
    	addOn.setFaoFileName(loadedAddon.getName());
    	addOn.setFaoFileLocation(FXML_ADD_ON_LOCATION+properties.getProperty("COMPANY_ID"));
    	addOn.setFaoVersion(properties.getProperty("VERSION"));
    	addOn.setFxmlTool(fxmlLoaderTools);
    	session.persist(addOn);
    	
    	
	}



	private void copyFileToPluginsFolder() throws IOException, InternalError {
		if(null != properties) {
			FileUtils.makeFolder(FXML_ADD_ON_LOCATION+properties.getProperty("COMPANY_ID"));
			File destination = new File(FXML_ADD_ON_LOCATION+properties.getProperty("COMPANY_ID")+File.separator+loadedAddon.getName());
			FileUtils.copyFile(loadedAddon, destination);
		}
		
	}



	private void validateFile() throws ValidateError{
		if(null != loadedAddon) {
			byte[] bytes = ZipUtil.unpackEntry(loadedAddon, PROPERTY_FILE_NAME);
			if(null == bytes || bytes.length == 0) {
				throw new ValidateError("Given File is not a Valid Add-On");
			}
		}
	}



	private void fillCategoryView() {
    	TreeItem<Category> treeView = new TreeItem<>();
    	Category category = new Category();
    	category.setCategoryName("Categories");
    	treeView.setValue(category);
    	categoryShow.setRoot(treeView);
    	List<Category> rootCategories = findRootParentCategery(categories).stream().distinct().collect(Collectors.toList());
		for(Category rootcategory : rootCategories) {
		    addCategory(rootcategory,treeView);
		}
    	FxComponentUtils.expandTreeView(treeView);

    	
    
	}


    private void addCategory(Category rootcategory, TreeItem<Category> treeView) {
    	TreeItem<Category> treeItem = new TreeItem<>(rootcategory);
    	List<Category> childs = findChildCategery(rootcategory);
    	if(null != childs && !childs.isEmpty()) {
    		for(Category category :childs) {
        		addCategory(category,treeItem);

    		}
    	}
    	treeView.getChildren().add(treeItem);
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




	private List<Category> getCategories(){
    	GetAllEntityService<Category> dbService = new GetAllEntityService<>();
    	try {
			return dbService.getAllRecords(Category.class);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
    	return null;
    }

/*
	private void choosePlugin(ActionEvent ev) {
		resultPane.setVisible(false);
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Java Archive", "*.dtpl"));
		File inputJar = fileChooser.showOpenDialog(((Node) ev.getSource()).getScene().getWindow());
		if (null != inputJar && inputJar.exists()) {
			filelocation.setText(FilenameUtils.separatorsToSystem(inputJar.getAbsolutePath()));
		}
	}




	private void importPlugin() {
		
		Tools tools = new Tools();
		tools.setClosable(true);
		tools.setDescription(pluginDesc.getText());
		tools.setCategory(category.getSelectionModel().getSelectedItem());
		tools.setMultiTabSupport(true);
		tools.setVisible(true);
		FindEntityService<ToolTypes> dbServiceq = new FindEntityService<>();

		try {
			tools.setToolType(dbServiceq.findEntity(ToolTypes.class, "F").getResult());
		} catch (ServiceException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String id = pluginID.getText();
		tools.setId(id);
		tools.setToolName( pluginName.getText());
		
		FxmlLoaderTools fxmlLoaderTools = new FxmlLoaderTools();
		fxmlLoaderTools.setTools(tools);
		try {
		byte[] bytes = ZipUtil.unpackEntry(new File(filelocation.getText()), "DTPLUGIN.properties");
    	String content = new String(bytes,StandardCharsets.UTF_8);
    	Properties properties  = PropertyUtils.parsePropertiesString(content);
		fxmlLoaderTools.setFxmlPath(properties.getProperty("FXML"));
		fxmlLoaderTools.setStylesheets(properties.getProperty("CSS"));

		PersistEntity<Tools> dbService = new PersistEntity<>();
    	dbService.persist(tools);
    	PersistEntity<FxmlLoaderTools> dbServiceex = new PersistEntity<>();
    	dbServiceex.persist(fxmlLoaderTools);
    	
    	File file =new File(filelocation.getText());
    	String tempLoc = "plugins"+File.separator+file.getName();
    	File dest = new File(tempLoc);
    	
    	
			FileUtils.copyFile(file, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		resultPane.setVisible(true);
		resultLabel.setText("Imported Successfully..");
		

	}
    */

}
