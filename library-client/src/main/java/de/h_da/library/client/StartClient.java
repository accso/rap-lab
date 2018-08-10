package de.h_da.library.client;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;

public class StartClient extends Application {

    private StartDialogCore dialogCore = new StartDialogCore();
    private AnchorPane content = new AnchorPane();

    public static void main(String[] args) {
        launch(args);
    }

    protected void loadUseCases(){
        try {
            dialogCore.loadView("Use case 1", "/de/h_da/library/client/UseCase1.fxml");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library App");

        loadUseCases();

        SplitPane root = new SplitPane();
        Scene scene = new Scene(root, 1024, 768);
        scene.setRoot(root);
        primaryStage.setScene(scene);

        root.setOrientation(Orientation.HORIZONTAL);
        root.setDividerPositions(0.2f);
        root.getItems().addAll(createListView(), content);


        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        ServerFacade.getInstance().closeContext();
    }

    private ListView<Pair<String, Parent>> createListView() {
        ListView<Pair<String, Parent>> list = new ListView<>();
        list.setItems(dialogCore.getUserCases());

        list.getSelectionModel().selectedItemProperty().addListener((ob, oldVal, newVal) -> {
            content.getChildren().clear();
            if (newVal != null) {
                content.getChildren().add(newVal.getValue());
                AnchorPane.setBottomAnchor(newVal.getValue(), 0.0);
                AnchorPane.setRightAnchor(newVal.getValue(), 0.0);
                AnchorPane.setTopAnchor(newVal.getValue(), 0.0);
                AnchorPane.setLeftAnchor(newVal.getValue(), 0.0);
            }
        });

        list.setCellFactory(new Callback<ListView<Pair<String, Parent>>, ListCell<Pair<String, Parent>>>() {
            @Override
            public ListCell<Pair<String, Parent>> call(ListView<Pair<String, Parent>> param) {
                return new ListCell<Pair<String, Parent>>(){

                    @Override
                    protected void updateItem(Pair<String, Parent> item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item != null){
                            setText(item.getKey());
                        }
                    }
                };
            }
        });
        list.getSelectionModel().select(0);
        return list;
    }
}
