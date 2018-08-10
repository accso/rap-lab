package de.h_da.library.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;

public class StartDialogCore {

    private ObservableList<Pair<String, Parent>> userCases = FXCollections.observableList(new ArrayList<>());

    public void loadView(String title, String path) throws IOException {
        FXMLLoader loader = new FXMLLoader(StartDialogCore.class.getResource(
                path));
        userCases.add(new Pair<>(title, (Parent)loader.load()));
        ((QuasarController)loader.getController()).init();
    }

    public ObservableList<Pair<String, Parent>> getUserCases() {
        return userCases;
    }
}
