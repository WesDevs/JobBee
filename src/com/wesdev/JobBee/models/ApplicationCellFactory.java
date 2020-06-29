package com.wesdev.JobBee.models;

import com.wesdev.JobBee.ApplicationListCell;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ApplicationCellFactory implements Callback<ListView<ApplicationViewData>, ListCell<ApplicationViewData>> {

    @Override
    public ListCell<ApplicationViewData> call(ListView<ApplicationViewData> param) {
        return new ApplicationListCell();
    }
}
