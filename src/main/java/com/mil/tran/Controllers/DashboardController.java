package com.mil.tran.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class DashboardController implements Initializable {

    @FXML
    private Label labelUsername;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setUsername(String username) {
        labelUsername.setText(username);
    }

}
