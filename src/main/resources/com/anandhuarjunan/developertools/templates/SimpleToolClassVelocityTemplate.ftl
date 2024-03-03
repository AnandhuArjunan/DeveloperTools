package ${packageName};

import java.util.stream.Stream;
import org.kordamp.ikonli.material.Material;

import com.anandhuarjunan.developertools.annotations.Node;
import com.anandhuarjunan.developertools.annotations.Tool;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

@Tool(description = "${iconDescription}", name = "${iconName}", category = { "" })

public class ${className} {

	@Node
	public Parent node() {
		return new BorderPane(new Label("hello world !"));
	}
			
	
}
