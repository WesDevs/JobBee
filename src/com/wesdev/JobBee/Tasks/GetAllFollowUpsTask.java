package com.wesdev.JobBee.Tasks;

import com.wesdev.JobBee.models.Datasource;
import com.wesdev.JobBee.models.FollowUp;
import com.wesdev.JobBee.models.SqlKeys;
import com.wesdev.JobBee.utils.SqlHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class GetAllFollowUpsTask extends Task {
    @Override
    protected ObservableList<FollowUp> call() {
        return FXCollections.observableArrayList
                (Datasource.getInstance().retrieveAllFollowUps(SqlHelper.ORDERED_BY_ASC, SqlKeys.CONTACT_NAME.val()));
    }
}