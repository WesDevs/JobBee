package com.wesdev.JobBee;

import com.wesdev.JobBee.models.ApplicationViewData;
import com.wesdev.JobBee.models.Datasource;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        ObservableList<ApplicationViewData> data = FXCollections.observableArrayList();
        data.addAll(new ApplicationViewData("com1"), new ApplicationViewData("com2"), new ApplicationViewData("com3"));

        final ListView<ApplicationViewData> lv = new ListView<ApplicationViewData>(data);
        lv.setCellFactory(new Callback<ListView<ApplicationViewData>, ListCell<ApplicationViewData>>() {
            @Override
            public ListCell<ApplicationViewData> call(ListView<ApplicationViewData> applicationViewDataListView) {
                return new ApplicationListCell();
            }
        });

        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("JobBee - Time To Bee Employed!");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
        if (!Datasource.getInstance().open()) {
            System.out.println("Fatal Error:  Could not connect to database");
            Platform.exit();
        }

    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Datasource.getInstance().close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
