package com.wesdev.JobBee.Tasks;

import com.wesdev.JobBee.models.Company;
import com.wesdev.JobBee.models.Datasource;
import com.wesdev.JobBee.models.SqlKeys;
import com.wesdev.JobBee.utils.SqlHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class GetAllCompaniesTask extends Task {
    @Override
    protected ObservableList<Company> call() {
        return FXCollections.observableArrayList
                (Datasource.getInstance().retrieveAllCompanies(SqlHelper.ORDERED_BY_ASC, SqlKeys.NAME.val()));
    }
}
