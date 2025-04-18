package com.mil.tran.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.mil.tran.JdbcDao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginController implements Initializable {
    @FXML
    private Button actionLogin;

    @FXML
    private TextField textUsername;

    @FXML
    private TextField textPassword;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void actionLogin(ActionEvent event) {
        Window owner = textUsername.getScene().getWindow();
        System.out.println(textUsername.getText());
        System.out.println(textPassword.getText());

        if (textUsername.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Please enter a valid username", "Form error!");
            return;
        }
        if (textPassword.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Please enter a valid password", "Form error!");
            return;
        }

        String username = textUsername.getText();
        String password = textPassword.getText();

        JdbcDao jdbcDao = new JdbcDao();
        boolean flag = jdbcDao.validate(username, password);
        if (!flag) {
            infoBox("Please enter the correct username and password", "Failed", null);

        } else {
            infoBox("Login successful", "Success", null);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mil/tran/Dashboard.fxml"));
                Parent root = fxmlLoader.load();

                Stage stage = new Stage();
                stage.setTitle("Dashboard");
                stage.setScene(new Scene(root));
                DashboardController controller = (DashboardController) fxmlLoader.getController();
                controller.setUsername(username);
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // if wrong username or password, show alert
    public static void infoBox(String infoMessage, String title, String headerText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();

    }

    public static void showAlert(Alert.AlertType alertType, Window owner, String message,
            String title) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.initOwner(owner);
        alert.show();
    }
}
