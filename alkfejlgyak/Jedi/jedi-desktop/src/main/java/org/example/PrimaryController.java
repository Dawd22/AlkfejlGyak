package org.example;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import org.example.dao.JediDAO;
import org.example.dao.JediDAOImpl;
import javafx.application.Platform;
import org.example.model.Jedi;


public class PrimaryController implements Initializable {
    JediDAO dao = new JediDAOImpl();

    @FXML
    private TableView<Jedi> jediTable;
    @FXML
    private TableColumn<Jedi, String> nameColumn;
    @FXML
    private TableColumn<Jedi, String> rankColumn;

    @FXML
    private TableColumn<Jedi, String> genderColumn;

    @FXML
    private TableColumn<Jedi, String> councilmemberColumn;

    @FXML
    private TableColumn<Jedi, Void> actionsColumn;

    private void refreshTable() {
        jediTable.getItems().setAll(dao.findAll());
    }

    private void deleteJedi(Jedi c) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete contact: " + c.getName(), ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(buttonType -> {
            if(buttonType.equals(ButtonType.YES)){
                dao.delete(c);
            }
        });
    }

    @FXML
    public void onExit(){
        Platform.exit();
    }

    private void editJedi(Jedi c) {
        FXMLLoader fxmlLoader = App.loadFXML(("/org/example/secondary.fxml"));
        SecondaryController controller = fxmlLoader.getController();
        controller.setJedi(c);
    }

    @FXML
    public void onAddNewJedi(){ // kössük be az Edit/Add alá
        FXMLLoader fxmlLoader = App.loadFXML(("/org/example/secondary.fxml"));
        SecondaryController controller = fxmlLoader.getController();
        controller.setJedi(new Jedi()); // ennyi a különbség
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Jedi> result = dao.findAll();
        refreshTable();

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        councilmemberColumn.setCellValueFactory(new PropertyValueFactory<>("councilmember"));


        actionsColumn.setCellFactory(param -> new TableCell<>(){
            private final Button deleteBtn = new Button("Delete");
            private final Button editBtn = new Button("Edit");

            {
                deleteBtn.setOnAction(event -> {
                    Jedi c = getTableRow().getItem();
                    deleteJedi(c);
                    refreshTable();
                });

                editBtn.setOnAction(event -> {
                    Jedi c = getTableRow().getItem();
                    editJedi(c);
                    refreshTable();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                }
                else{
                    HBox container = new HBox();
                    container.getChildren().addAll(editBtn, deleteBtn);
                    container.setSpacing(10.0);
                    setGraphic(container);
                }
            }
        });
    }
}
