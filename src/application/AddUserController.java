package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.rollingpanda.dao.UserDAO;
import application.rollingpanda.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AddUserController implements Initializable{
	
	@FXML private TextField tfUsername;
	@FXML private TextField tfPassword;
	@FXML private TextField tfRole;
	@FXML private ListView<User> list;
	
	private Alert a = new Alert(Alert.AlertType.ERROR);
//	private StringBuilder errorText = new StringBuilder();
	
	//FILLING UP LIST WITH DATAS FROM SQL
	ObservableList<User> listOfUsers = FXCollections.observableArrayList(UserDAO.getAll(Main.getConn()));
	
	//CREATING NEW USER
	@FXML
	private void addUser(ActionEvent event) {
		boolean error = false;
		
		//GETING USERNAME FORM TEXT FILED AND PARSING TO STRING
		if(usernameCheck(tfUsername.getText().toString()) == true) {
			System.out.println("error1");
			error = true;
		}
		//GETING PASSWORD FORM TEXT FILED AND PARSING TO STRING
		if(passwordChech(tfPassword.getText().toString()) == true) {
			System.out.println("error2");
			error = true;
		}
		//GETING ROLE FORM TEXT FILED AND PARSING TO STRING
		if(roleChech(tfRole.getText().toString()) == true) {
			System.out.println("error3");
		}
		//IF WE GET ERROR IN ANY OF CHECKS
		if(error == true) {
			System.out.println("error4");
			
		//IF WE DONT GET ERROR THEN CREATE NEW USER
		}else {
			User user = new User();
			user.setUsername(tfUsername.getText());
			user.setPassword(tfPassword.getText());
			user.setRole(tfRole.getText());
			
			//ADDING USER TO LIST
			listOfUsers.add(user);
			UserDAO.addUser(Main.getConn(), user);
			clear();
			
			a.setTitle("Success!");
			a.setHeaderText("User added!");
			a.showAndWait();
		}
	}
	
	//CHECK ROLE
	private boolean roleChech(String role) {
		boolean error = false;
		
		if(role.equals("") || role == null) {
			a.setTitle("Error!");
			a.setHeaderText("You must enter role");
			a.showAndWait();
			error = true;
		}
		//PARSING ALL CASE TO LOWER CASE AND THEN COMPERE IT WITH STRINGS
		if(role.toLowerCase().equals("admin")) {
			error = false;
		}
		if(role.toLowerCase().equals("user")) {
			error = false;
		}
		
		return error;
	}
	
	//USERNAME CHECK
	private boolean usernameCheck(String username) {
		boolean error = false;
		if(username.equals("") || username == null) {
			a.setTitle("Error!");
			a.setHeaderText("You must enter username");
			a.showAndWait();
		}
		
		//THIS WILL CHECK IF USERNAME HAVE SPECIAL CHARACTERS
		Pattern p = Pattern.compile("[^A-Za-z0-9]");
	     Matcher m = p.matcher(username);
	     boolean b = m.find();
	     if (b == true) {
				a.setTitle("Error!");
				a.setHeaderText("You cant have special characters in username!");
				a.showAndWait();
				error = true;
	     }
	    
	     //CHECK FOR USERNAME LENNGHT
		if(username.length() < 4) {
			a.setTitle("Error!");
			a.setHeaderText("Your username must have atleast 4 characters!");
			a.showAndWait();
			error = true;
		}
		
		return error;
	}
	
	//PASSWORD CHECK
	private boolean passwordChech(String password) {
		boolean error = false;
		if(password.equals("") || password == null) {
			a.setTitle("Error!");
			a.setHeaderText("You must enter password!");
			a.showAndWait();
			error = true;
		}
		
		
//		for(char e : password.toCharArray()) {
//			if(!Character.isUpperCase(e)) {
//				System.out.println("error");
//				error = true;
//			}
//		}
//		
		//PASSWORD MUST HAVE 8 CHARS
		if(password.length() < 8) {
			a.setTitle("Error!");
			a.setHeaderText("Your password must have atleast 8 characters!");
			a.showAndWait();
			error = true;
		}
		
		return error;
		
	}
	
	@FXML
	public void delete(ActionEvent event) {
		UserDAO.delete(Main.getConn(), list.getItems().remove(list.getSelectionModel().getSelectedItem()));
	}
	
	private void clear() {
		tfUsername.setText("");
		tfPassword.setText("");
		tfRole.setText("");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//INIT LIST OF USERS
		list.setItems(listOfUsers);
		
	}
	
}
