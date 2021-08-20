package com.ieireference.system.drawing.swing;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.ieireference.system.io.JarUtils;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

/**
 * 
 * A {@link JWebBrowser} provides a component with navigation in the web, with components of JavaFX, is possible now access the web in Swing and contains simple browser controls.
 * */
public final class JWebBrowser extends JFXPanel {

	/*
     * ===========================================================================
     * 								Class Controller
     * ===========================================================================
     * */
	
	@FXML
    private WebView fxWebBrowser;

    @FXML
    private MenuItem fxMClose;

    @FXML
    private MenuItem fxMNavigate;

    @FXML
    private MenuItem fxMReload;

    @FXML
    private MenuItem fxMAbout;

    @FXML
    private Menu fxTMenu;

    @FXML
    private TextField fxURL;

    @FXML
    private Menu fxInMenu;

    @FXML
    private ProgressIndicator fxProgress;
    
    @FXML
    void About(ActionEvent event) {

    }

    @FXML
    void Close(ActionEvent event) {
    	fxWebBrowser.getEngine().loadContent("", "text/html");
    	
    }

    @FXML
    void Navigate(ActionEvent event) {
    	Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				TextInputDialog dialog = new TextInputDialog(urlRecent);
				dialog.setTitle("Navigate to...");
				dialog.setContentText("Enter the url to navigate: ");
				dialog.showAndWait();
				String result = dialog.getResult();
				
				urlRecent = result;
				
				try {
					new URL(result).toURI();
					
					fxProgress.setVisible(true);
					fxURL.setDisable(true);
					fxWebBrowser.getEngine().load(result);
					
					
				} catch (MalformedURLException | URISyntaxException e) {
					// TODO Auto-generated catch block
					Alert alert = new Alert(AlertType.ERROR, "This is not a valid page.", ButtonType.OK);
					alert.showAndWait();
				}
				
			}
		});
    	
    }

    @FXML
    void Reload(ActionEvent event) {
    	fxProgress.setVisible(true);
		fxURL.setDisable(true);
    	fxWebBrowser.getEngine().reload();
    }
    
    @FXML
    void fxURLEnter(KeyEvent event) {
    	if(event.getCode() == KeyCode.ENTER) {
    		urlRecent = fxURL.getText();
			
			try {
				new URL(fxURL.getText()).toURI();
				
				fxProgress.setVisible(true);
				fxURL.setDisable(true);
				fxWebBrowser.getEngine().load(fxURL.getText());
				
				
			} catch (MalformedURLException | URISyntaxException e) {
				// TODO Auto-generated catch block
				Alert alert = new Alert(AlertType.ERROR, "This is not a valid page.", ButtonType.OK);
				alert.showAndWait();
			}
    	}
    }
    
    @FXML
    void initialize() {
    	
    	
        fxWebBrowser.getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {

			@Override
			public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
				// TODO Auto-generated method stub
				if(newValue == Worker.State.SUCCEEDED) {
					fxProgress.setVisible(false);
					fxURL.setDisable(false);
					fxURL.setText(fxWebBrowser.getEngine().getLocation());
				}
				else if(newValue == State.FAILED) {
					fxProgress.setVisible(false);
					fxURL.setDisable(false);
					fxURL.setText(fxWebBrowser.getEngine().getLocation());
					
					try {
						List<String> pageError = JarUtils.ReadAllLinesClassPath(JWebBrowser.class.getResourceAsStream("/com/ieireference/resources/components/NotLoaded.html"), Charset.defaultCharset());
						
						String ln = "";
						
						List<String> result = new ArrayList<String>();
					    Throwable throwable = fxWebBrowser.getEngine().getLoadWorker().getException();
						while (throwable  != null) {
					        result.add(throwable.getMessage());
					        throwable = throwable.getCause();
					    }
						
						for(String item : pageError) {
							if(item.contains("{0}")) {
								item = item.replace("{0}", String.join("", result));
								ln += item;
							} else ln += item;
							
						}
						
						fxWebBrowser.getEngine().loadContent(ln, "text/html");
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});

    }
    
    /*
     * ===========================================================================
     * 									Class
     * ===========================================================================
     * */
    
	private URL fxml = JWebBrowser.class.getResource("/com/ieireference/resources/components/WebBrowser.fxml");
	private FXMLLoader loader = new FXMLLoader(fxml);

	private static final long serialVersionUID = -6461205686231297271L;
	
	private String urlRecent = "";
	
	/**
	 * Constructs the class internally for not use {@code JWebBrowser.JWebBrowser()}, this constructor is used to create the component and if use can make a loop.
	 * 
	 * @param obj This is not used, is only a way of instantiates the class as a controller and not as component.
	 * */
	protected JWebBrowser(Object obj) {}
	
	/**
	 * Constructs the class making the browser.
	 * */
	public JWebBrowser() {
		
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				loader.setController(new JWebBrowser(new Object()));
				loader.setRoot(new BorderPane());
		
				Parent toLoad;
				try {
					toLoad = loader.load();
					setScene(new Scene(toLoad));
					setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
	}
	
}
