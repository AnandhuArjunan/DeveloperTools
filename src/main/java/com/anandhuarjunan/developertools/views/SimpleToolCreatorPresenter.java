package com.anandhuarjunan.developertools.views;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;


import com.anandhuarjunan.developertools.MainApp;
import com.anandhuarjunan.developertools.annotations.Node;
import com.anandhuarjunan.developertools.helper.ToolLoader;
import com.anandhuarjunan.jfxawesome.exception.JFXException;
import com.anandhuarjunan.jfxawesome.injection.Injector;
import com.anandhuarjunan.jfxawesome.utils.AnnotationUtils;
import com.mchange.v2.util.ComparatorUtils;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class SimpleToolCreatorPresenter implements Initializable{


	  @FXML
	    private MFXTextField classname;

	    @FXML
	    private MFXCheckbox closable;

	    @FXML
	    private TextArea code;

	    @FXML
	    private Button compile;

	    @FXML
	    private Button compile1;

	    @FXML
	    private TextArea log;

	    @FXML
	    private MFXCheckbox multiinstance;

	    @FXML
	    private MFXTextField packageName;

	    @FXML
	    private MFXTextField toolCategory;

	    @FXML
	    private MFXTextField toolDescription;

	    @FXML
	    private MFXTextField toolName;

    
	private static final String COMPILED = "/compiled/";

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		addtemplate();
		
		ChangeListener<String> textChangeListener = (observable, oldValue, newValue) -> {addtemplate();};

		// Attach the listener to each text field
		classname.textProperty().addListener(textChangeListener);
		packageName.textProperty().addListener(textChangeListener);
		toolDescription.textProperty().addListener(textChangeListener);
		toolName.textProperty().addListener(textChangeListener);

		
		
		compile.setOnAction(ev->{
			try {
				log.clear();
				File root = Files.createTempDirectory("java").toFile();
				File sourceFile = new File(root, "test/Test.java");
				sourceFile.getParentFile().mkdirs();
				Files.write(sourceFile.toPath(), code.getText().getBytes(StandardCharsets.UTF_8));

				// Compile source file.
				JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
				log.setText(log.getText()+" Beginning Compilation.....");
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

				int compilationResult = compiler.run(null, null, outputStream, sourceFile.getPath());
				
				log.setText(log.getText() + "\n" + outputStream.toString());
				
				// Check the compilation result for success or failure
				if (compilationResult != 0) {
				    log.setText(log.getText() + "\nCompilation failed");
				}else {
					 log.setText(log.getText() + "\nCompilation Success");
				}

				// Load and instantiate compiled class.
				URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
				Class<?> cls = Class.forName("test.Test", true, classLoader); // Should print "hello".
				Object instance = cls.getDeclaredConstructor().newInstance(); 
				
				Method[] methods = cls.getDeclaredMethods();
				if(methods == null || methods.length == 0) {
					throw new JFXException("No methods found in "+cls.getName());
				}
				// can assume that it is just node returning impl
				for(Method m :methods ) {
					if(m.isAnnotationPresent(Node.class)) {
						((ToolLoader)Injector.fetchFromGlobalInjectionContext("toolLoader")).addTab("Test", (javafx.scene.Node)AnnotationUtils.invokeAnnotedMethodAndGetReturn(Node.class, cls, instance), false, null);
					}
					
				}
				
				InputStream classFileStream = classLoader.getResourceAsStream("test/Test.class");

				// Store the class file to a desired location
				File destinationFile = new File(COMPILED+"/Test.class");
				destinationFile.createNewFile();
				Files.copy(classFileStream, destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			}
			catch(Exception e) {
				log.setText(log.getText()+"\n"+ExceptionUtils.getStackTrace(e));
			}
		});
		
	}
	
	
	private void addtemplate() {

		try {
			// Create a configuration instance
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
			cfg.setClassForTemplateLoading(MainApp.class, "templates");

			// Load the template
			freemarker.template.Template template = cfg.getTemplate("SimpleToolClassVelocityTemplate.ftl");

			// Create the data-model
			Map<String, Object> data = new HashMap<>();
			data.put("packageName", packageName.getText());
			data.put("className", classname.getText());
			data.put("iconDescription", toolDescription.getText());

			data.put("iconName", toolName.getText());


			StringWriter stringWriter = new StringWriter();

				template.process(data, stringWriter);
				
				String generatedCode = stringWriter.toString();
				
				
				code.setText(generatedCode);
				
			} catch (Exception e) {
				log.setText("Could not load the templated source code !"+e.getMessage());
			}
			
	
	}
	
	}


