package com.mil.tran.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DashboardController implements Initializable {
    @FXML
    private Button btnManageTable;

    @FXML
    private Label labelUsername;

    Scene fxmlFile;
    Parent root;
    Stage window;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setUsername(String username) {
        labelUsername.setText(username);
    }

    @FXML
    private void manageTable(ActionEvent event) {
        try {
            openModalWindow("/com/mil/tran/fxml/Tables.fxml", "Manage Tables");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void openModalWindow(String resource, String title) throws IOException {
        root = FXMLLoader.load(getClass().getResource(resource));
        fxmlFile = new Scene(root);
        window = new Stage();
        window.setTitle(title);
        window.setScene(fxmlFile);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setAlwaysOnTop(true);
        window.setIconified(false);
        // window.initStyle(StageStyle.UNDECORATED);
        window.showAndWait();
    };

}
