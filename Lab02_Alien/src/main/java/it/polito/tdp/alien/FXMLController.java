package it.polito.tdp.alien;

import java.net.URL;

import it.polito.tdp.alien.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.*;

public class FXMLController {
	/*jadb:nomrdb://[host:locally],[host:3306].../[database][?propertyName1][=propertyValue1][&property*/

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TextField txtFieldTraduzione;

    @FXML
    private Button btnTranslate;
    
    @FXML
    private Button btnReset;
    
    @FXML
    private TextArea TxtAreaTraduzioni;

	private Model model;
	
    @FXML
    void doTranslate(ActionEvent event) {
    	TxtAreaTraduzioni.clear();
    	String parolaAliena="";
    	String traduzione="";
    	List <String> tradMultipla = new LinkedList<String>();
    	String s = txtFieldTraduzione.getText().toLowerCase();
    	String split[] = s.split(" ");
    	if (s == null || s.length() == 0) {
			TxtAreaTraduzioni.setText("Inserire una o due parole.");
			return;
		}

    	if(split.length==2) {
    		parolaAliena = split[0];
			traduzione = split[1];
    	/*	try {
    			model.addParole(parolaAliena,traduzione);
    			TxtAreaTraduzioni.appendText("La parola aliena inserita nel database è: "+parolaAliena+" la sua traduzione è:" +traduzione+"\n");
    		}
    		catch(IllegalStateException ise){
    			TxtAreaTraduzioni.appendText(ise.getMessage());
    		}*/
			try {
				model.addParoleMultiple(parolaAliena, traduzione);
				TxtAreaTraduzioni.appendText("La parola aliena è: "+parolaAliena+" e la sua traduzione è: "+traduzione+"\n");
			}
			catch(IllegalStateException ise) {
				TxtAreaTraduzioni.appendText(ise.getMessage());
			}
    		txtFieldTraduzione.clear();
    	}
    	if(split.length==1) {
    		parolaAliena=split[0];
    		/*try {
    			traduzione = model.translateWord(parolaAliena);
    			
    		}
    		
    		catch(IllegalStateException ise){
    			TxtAreaTraduzioni.appendText(ise.getMessage());
    		}*/
    		
    		try {
    			System.out.println(model.traduzioniMultipleMetodo(parolaAliena));
    			TxtAreaTraduzioni.appendText(model.traduzioniMultipleMetodo(parolaAliena).toString());
    		}
    		catch (IllegalStateException ise) {
    			TxtAreaTraduzioni.appendText(ise.getMessage());
    		}
    		
    		txtFieldTraduzione.clear();
    }
   }
    
    @FXML
    void doReset(ActionEvent event) {
    	model.clearAll();
    }

    @FXML
    void initialize() {
    	 assert txtFieldTraduzione != null : "fx:id=\"txtFieldTraduzione\" was not injected: check your FXML file 'Scene.fxml'.";
         assert btnTranslate != null : "fx:id=\"btnTranslate\" was not injected: check your FXML file 'Scene.fxml'.";
         assert TxtAreaTraduzioni != null : "fx:id=\"TxtAreaTraduzioni\" was not injected: check your FXML file 'Scene.fxml'.";
         assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }


	public void setModel(Model model) {
		this.model = model;
	}
}