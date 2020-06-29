package com.wesdev.JobBee.Tasks;

import com.wesdev.JobBee.models.ApplicationViewData;
import com.wesdev.JobBee.models.Datasource;
import com.wesdev.JobBee.models.FullApplication;
import com.wesdev.JobBee.models.SqlKeys;
import com.wesdev.JobBee.utils.SqlHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class GetApplicationsViewTask extends Task {

    boolean showInactive;

    public GetApplicationsViewTask(boolean showInactive) {
        this.showInactive = showInactive;
    }

    @Override
    protected ObservableList<ApplicationViewData> call() {
        return FXCollections.observableArrayList
                (Datasource.getInstance().retrieveAllApplicationView(this.showInactive));

    }
}
