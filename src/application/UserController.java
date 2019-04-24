package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.rollingpanda.dao.UserDAO;
import application.rollingpanda.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserController implements Initializable{
	
	@FXML private TableView<User> table;
	@FXML private TableColumn<User, String> usernameC;
	@FXML private TableColumn<User, String> roleC;
	
	ObservableList<User> listOfUsers = FXCollections.observableArrayList(UserDAO.getAll(Main.getConn()));

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		usernameC.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		roleC.setCellValueFactory(new PropertyValueFactory<User, String>("role"));

		table.setItems(listOfUsers);
		
	}
	
}
