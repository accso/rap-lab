package de.h_da.library.client.component1;

import de.h_da.library.client.QuasarController;
import de.h_da.library.client.ServerFacade;
import de.h_da.library.component1.entity.Entity1;
import de.h_da.library.component1.type.DataType1;
import de.h_da.library.component1.usecase.UseCase1Remote;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class UseCase1QuasarController implements QuasarController {

    @FXML
    private ComboBox<DataType1> attribute2Combo;

    @FXML
    private Button createButton;

    @FXML
    private TextField attribute1TextField;

    @FXML
    private ListView<Entity1> entity1ListView;

    private UseCase1Remote useCase1;

    ObservableList<Entity1> entity1List = FXCollections.observableList(new ArrayList<>());

    @Override
    public void init() {
        useCase1 =  ServerFacade.getInstance().lookup("UseCase1Impl", UseCase1Remote.class);
        entity1List.addAll(useCase1.useCaseMethod1());
        entity1ListView.setItems(entity1List);

        attribute2Combo.setItems(FXCollections.observableArrayList(DataType1.values()));
    }

    @FXML
    public void createEntity1(ActionEvent event) {
        Entity1 newEntity = new Entity1();

        newEntity.setAttribute1(attribute1TextField.getText());
        attribute1TextField.setText("");
        newEntity.setAttribute2(attribute2Combo.getSelectionModel().getSelectedItem());
        attribute2Combo.getSelectionModel().clearSelection();

        useCase1.createEntity1(newEntity);

        entity1List.clear();
        entity1List.addAll(useCase1.useCaseMethod1());
    }

}
