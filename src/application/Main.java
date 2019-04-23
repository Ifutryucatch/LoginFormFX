package application;
	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	

		private static Connection conn;

		static {
			try {
				//SQL DRIVER
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/user_sheme?useSSL=false", 
						"root", 
						"root"); 
			} catch (Exception ex) {
				System.out.println("Neuspela konekcija na bazu!");
				ex.printStackTrace();
				System.exit(0);
			}
		}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
			Scene scene = new Scene(root,400,400);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

	public static Connection getConn() {
		return conn;
	}


    @Override
    public void stop() throws Exception {
		try {
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
    }
}
