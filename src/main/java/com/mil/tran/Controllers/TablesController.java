package com.mil.tran.Controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.mil.tran.JdbcDao;
import com.mil.tran.Tables;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TablesController implements Initializable {
    @FXML
    private TextField tfTableName;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private TableView<Tables> tableTables;

    @FXML
    private TableColumn<Tables, Integer> colID;

    @FXML
    private TableColumn<Tables, String> colName;

    JdbcDao jdbc;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jdbc = new JdbcDao();
        showTable();
    }

    public void showTable() {
        ObservableList<Tables> list = getTableList();
        colID.setCellValueFactory(new PropertyValueFactory<Tables, Integer>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Tables, String>("name"));
        tableTables.setItems(list);
    }

    private void insertRecord() {
        String name = tfTableName.getText();
        if (!name.isEmpty()) {
            String query = "INSERT INTO tbltables (name) VALUES ('" + name + "')";
            executeQuery(query);
            showTable();
            tfTableName.setText("");
        }
    }

    private ObservableList<Tables> getTableList() {
        ObservableList<Tables> tableList = FXCollections.observableArrayList();
        Connection connection = jdbc.getConnection();
        String query = "SELECT * FROM tbltables";
        Statement st;
        ResultSet rs;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Tables tables;
            while (rs.next()) {
                tables = new Tables(rs.getInt("id"), rs.getString("name"));
                tableList.add(tables);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return tableList;
    }

    private void executeQuery(String query) {
        Connection conn = jdbc.getConnection();
        Statement st;
        System.out.println(query);
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println("Error while inserting record");
            ex.printStackTrace();
        }
    }

    @FXML
    private void saveTable(ActionEvent event) {
        insertRecord();
    }
}
