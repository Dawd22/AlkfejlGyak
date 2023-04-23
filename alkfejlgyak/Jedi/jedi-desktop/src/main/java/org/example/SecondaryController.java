package org.example;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.dao.JediDAO;
import org.example.dao.JediDAOImpl;
import org.example.model.Jedi;

public class SecondaryController implements Initializable {
    private Jedi jedi;
    private JediDAO dao = new JediDAOImpl();
    @FXML
    public TextField nameTextField;
    @FXML
    public ComboBox RankCombo;

    @FXML
    public RadioButton Male_Radio;
    @FXML
    public RadioButton Female_Radio;
    @FXML
    public CheckBox councilmember_Check;

    public void setJedi(Jedi c) {
        this.jedi = c;
    }

    public void onClose(ActionEvent actionEvent) {
        App.loadFXML("/org/example/primary.fxml");
    }

    public void onSave(ActionEvent actionEvent) {
        jedi.setName(nameTextField.getText());
        jedi.setRank(RankCombo.getValue().toString());
        if (Male_Radio.isSelected()) jedi.setGender("Male");
        else if (Female_Radio.isSelected()) jedi.setGender("Female");
        if (councilmember_Check.isSelected()) jedi.setCouncilmember("Yes");
        else jedi.setCouncilmember("No");
        dao.save(jedi);
        App.loadFXML("/org/example/primary.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> tipus = FXCollections.observableArrayList("Padawan", "Knight", "Master");
        RankCombo.setItems(tipus); //értékek beállítása
        RankCombo.setValue("Knight"); //default érték


        ToggleGroup group = new ToggleGroup();
        Male_Radio.setToggleGroup(group);
        Male_Radio.setSelected(true);
        Female_Radio.setToggleGroup(group);

    }
}