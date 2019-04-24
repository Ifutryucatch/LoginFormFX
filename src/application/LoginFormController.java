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

	@FXML
	private void logInUser(ActionEvent event) {
		//GETING TEXT FROM TEXT FIELD THEN PARSING TO STRING BECAUSE WE MUST HAVE STRING FOR DAO WICH COMUNICATE WITH DATA BASE
		String username = tfUsername.getText().toString();
		String password = tfPassword.getText().toString();
		
		boolean error = false;
		
		//CHECKING IF USERNAME FROM TEXT FILED EXIST IN DATA BASE
		if(!username.equals(UserDAO.getUserUsername(Main.getConn(), username).getUsername())) {
			a.setTitle("Error!");
			a.setHeaderText("Invalid username");
			a.showAndWait();
			error = true;
		}
		
		//CHECKING IF PASSWORD FROM TEXT FILED EXIST IN DATA BASE
		if(!password.equals(UserDAO.getUserPassword(Main.getConn(), password).getPassword())) {
			a.setTitle("Error!");
			a.setHeaderText("Invalid password");
			a.showAndWait();
			error = true;
		}
		
		//GETING ROLE AND THEN WE DECIDE WICH WINDOW WILL WE OPEN
		String role = UserDAO.getUserPassword(Main.getConn(), tfPassword.getText().toString()).getRole();
		if(role.equals("admin")) {
			newWindowAdmin(event);
		}
		
		if(role.equals("user")) {
			newWindowUser(event);
		}
		
		if(error == false) {
			a.setTitle("Success!");
			a.setHeaderText("Successfully loged in!");
			a.showAndWait();
			clear();
		}
		
		if(error == true) {
			a.setTitle("Error!");
			a.setHeaderText("Try again!");
			a.showAndWait();
			clear();
		}
	}

	//CREATING NEW ADMIN WINDOW
	private void newWindowAdmin(ActionEvent event){
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
	
	//CREATING NEW USER WINDOW
	private void newWindowUser(ActionEvent event){
		try {
			FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("User.fxml"));
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
