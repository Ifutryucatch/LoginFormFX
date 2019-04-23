package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.rollingpanda.dao.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginFormController implements Initializable{

	@FXML private TextField tfUsername;
	@FXML private TextField tfPassword;
	
	private Alert a = new Alert(Alert.AlertType.ERROR);
	private StringBuilder errorText = new StringBuilder();
	
	@FXML
	private void logInUser(ActionEvent event) {
		String username = tfUsername.getText().toString();
		String password = tfPassword.getText().toString();
		
		boolean error = false;
		
		if(username == UserDAO.getUserUsername(Main.getConn(), username).getUsername()) {
			errorText.append("Invalid username!");
			error = false;
		}
		
		else if(password == UserDAO.getUserPassword(Main.getConn(), password).getPassword()) {
			errorText.append("Invalid password!");
			error = false;
		}
		
		if(error == true) {
			a.setTitle("Error!");
			a.setHeaderText("Problems: \n");
			a.setContentText(errorText.toString());
			a.showAndWait();
		}
		
		else if(error == false) {
			a.setTitle("Success!");
			a.setHeaderText("Successfully loged in!");
			a.showAndWait();
			clear();
		}
	}
	
	@FXML
	private void newWindow(ActionEvent event){
		try {
			FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("AddUser.fxml"));
			Parent rootl = (Parent) fxmloader.load();
			Stage stage = new Stage();
			stage.setTitle("New windows");
			stage.setScene(new Scene(rootl));
			stage.show();
		
		}catch(Exception e) {
			System.err.println("Novi prozor");
		}
	}
	
	private void clear() {
		tfUsername.setText("");
		tfPassword.setText("");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
