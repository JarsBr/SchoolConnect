module Projeto {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires java.desktop;
	requires mysql.connector.java;
	
	opens application to javafx.graphics, javafx.fxml;
}
