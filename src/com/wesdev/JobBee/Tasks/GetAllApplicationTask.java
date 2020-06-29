package com.wesdev.JobBee.Tasks;

import com.wesdev.JobBee.models.Application;
import com.wesdev.JobBee.models.Datasource;
import com.wesdev.JobBee.models.SqlKeys;
import com.wesdev.JobBee.utils.SqlHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class GetAllApplicationTask extends Task {
    @Override
    protected ObservableList<Application> call() {
        return FXCollections.observableArrayList
                (Datasource.getInstance().retrieveAllApplications(SqlHelper.ORDERED_BY_ASC, SqlKeys.JOB_TITLE.val()));
    }
}
