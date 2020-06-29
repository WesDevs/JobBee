package com.wesdev.JobBee.Tasks;

import com.wesdev.JobBee.models.Datasource;
import com.wesdev.JobBee.models.FullApplication;
import com.wesdev.JobBee.models.SqlKeys;
import com.wesdev.JobBee.utils.SqlHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class GetCombinedViewTask extends Task {
    @Override
    protected ObservableList<FullApplication> call() {
        return FXCollections.observableArrayList
                (Datasource.getInstance().retrieveCombinedView(SqlHelper.ORDERED_BY_ASC, SqlKeys.NAME.val()));
    }
}