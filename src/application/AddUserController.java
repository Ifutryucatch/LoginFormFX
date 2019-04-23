package application;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.rollingpanda.dao.UserDAO;
import application.rollingpanda.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class AddUserController {
	
	@FXML private TextField tfUsername;
	@FXML private TextField tfPassword;
	
	private Alert a = new Alert(Alert.AlertType.ERROR);
//	private StringBuilder errorText = new StringBuilder();
	
	ObservableList<User> listOfUsers = FXCollections.observableArrayList();
	
	@FXML
	private void addUser(ActionEvent event) {
		boolean error = false;
		
		if(usernameCheck(tfUsername.getText().toString()) == true) {
			System.out.println("error1");
			error = true;
		}
		if(passwordChech(tfPassword.getText().toString()) == true) {
			System.out.println("error2");
			error = true;
		}
		if(error == true) {
			System.out.println("error3");
		}else {
			User user = new User();
			user.setUsername(tfUsername.getText());
			user.setPassword(tfPassword.getText());
			
			listOfUsers.add(user);
			UserDAO.addUser(Main.getConn(), user);
			clear();
			
			a.setTitle("Success!");
			a.setHeaderText("User added!");
			a.showAndWait();
		}
	}
	
	private boolean usernameCheck(String username) {
		boolean error = false;
		if(username.equals("") || username == null) {
			a.setTitle("Error!");
			a.setHeaderText("You must enter username");
			a.showAndWait();
		}
		
		Pattern p = Pattern.compile("[^A-Za-z0-9]");
	     Matcher m = p.matcher(username);
	     boolean b = m.find();
	     if (b == true) {
				a.setTitle("Error!");
				a.setHeaderText("You cant have special characters in username!");
				a.showAndWait();
				error = true;
	     }
	    
		if(username.length() < 4) {
			a.setTitle("Error!");
			a.setHeaderText("Your username must have atleast 4 characters!");
			a.showAndWait();
			error = true;
		}
		
		return error;
	}
	
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
		if(password.length() < 8) {
			a.setTitle("Error!");
			a.setHeaderText("Your password must have atleast 8 characters!");
			a.showAndWait();
			error = true;
		}
		
		return error;
		
	}
	
	private void clear() {
		tfUsername.setText("");
		tfPassword.setText("");
	}
	
}
