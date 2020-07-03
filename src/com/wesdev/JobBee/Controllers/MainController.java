package com.wesdev.JobBee.Controllers;

import com.wesdev.JobBee.Tasks.*;
import com.wesdev.JobBee.models.*;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.time.LocalDate;

public class MainController {

    @FXML
    private TableView companyTable;

    @FXML
    private TableView applicationsTable;

    @FXML
    private TableView followUpsTable;

    @FXML
    private TableView combinedViewTable;

    @FXML
    private VBox appViewStack;

    @FXML
    private VBox addEntryStack;

    @FXML
    private VBox statStack;

    @FXML
    private VBox dataViewStack;

    @FXML
    private VBox updateAppForm;

    @FXML
    private Button appButton;

    @FXML
    private Button addEntryButton;

    @FXML
    private Button statsButton;

    @FXML
    private Button dataViewButton;

    @FXML
    private Button UAsubmit;

    @FXML
    private StackPane mainStackPane;

    @FXML
    private ListView<ApplicationViewData> applicationListView;

    @FXML
    private CheckBox toggleActiveApps;

    @FXML
    private TextArea UAfollowUp1;

    @FXML
    private TextArea UAfollowUp2;

    @FXML
    private TextArea UAfollowUp3;

    @FXML
    private TextArea UAfollowUp4;

    @FXML
    private TextArea UAfeedback;

    @FXML
    private TextField UAcontactPerson;

    @FXML
    private TextField UAcontactEmail;

    @FXML
    private void initialize() {
        hideAllPanes();
        appViewStack.setVisible(true);

        appButton.setUserData(appViewStack);
        statsButton.setUserData(statStack);
        dataViewButton.setUserData(dataViewStack);
        addEntryButton.setUserData(addEntryStack);
        listCombinedView();

        applicationListView.setCellFactory(new ApplicationCellFactory());

        showApplications();
    }

    @FXML
    private void showApps(Event e) {
        showApplications();
        hideAllPanes();
        ((VBox)((Button)e.getSource()).getUserData()).setVisible(true);
    }

    @FXML
    private void toggleViews(Event e) {
        hideAllPanes();
        ((VBox)((Button)e.getSource()).getUserData()).setVisible(true);

        if (((VBox)((Button)e.getSource()).getUserData()).equals(dataViewStack)) {
            listCombinedView();
        }
    }

    @FXML
    public void hideAllPanes() {
        mainStackPane.getChildren().forEach(s -> s.setVisible(false));
        addEntryButton.getUserData();
    }

    @FXML
    public void hoverOn(Event e) {
        ((StackPane)e.getSource()).setEffect(new Glow(0.6));
    }

    @FXML
    public void hoverOff(Event e) {
        ((StackPane)e.getSource()).setEffect(new Glow(0.0));
    }

    @FXML
    public void listAllCompanies() {
        Task<ObservableList<Company>> task = new GetAllCompaniesTask();
        companyTable.itemsProperty().bind(task.valueProperty());

        task.setOnSucceeded(e -> {
            applicationsTable.setVisible(false);
            followUpsTable.setVisible(false);
            combinedViewTable.setVisible(false);
            companyTable.setVisible(true);
        });


        new Thread(task).start();
    }

    @FXML
    public void listAllApplications() {
        Task<ObservableList<Application>> task = new GetAllApplicationTask();
        applicationsTable.itemsProperty().bind(task.valueProperty());

        task.setOnSucceeded(e -> {
            companyTable.setVisible(false);
            applicationsTable.setVisible(true);
            combinedViewTable.setVisible(false);
            followUpsTable.setVisible(false);
        });

        new Thread(task).start();
    }

    @FXML
    public void listAllFollowUps() {
        Task<ObservableList<FollowUp>> task = new GetAllFollowUpsTask();
        followUpsTable.itemsProperty().bind(task.valueProperty());

        task.setOnSucceeded(e -> {
            companyTable.setVisible(false);
            applicationsTable.setVisible(false);
            combinedViewTable.setVisible(false);
            followUpsTable.setVisible(true);
        });

        new Thread(task).start();
    }

    @FXML
    public void listCombinedView() {
        Task<ObservableList<FullApplication>> task = new GetCombinedViewTask();
        combinedViewTable.itemsProperty().bind(task.valueProperty());

        task.setOnSucceeded(e -> {
            companyTable.setVisible(false);
            applicationsTable.setVisible(false);
            followUpsTable.setVisible(false);
            combinedViewTable.setVisible(true);

        });

        new Thread(task).start();
    }

    @FXML
    public void showApplications() {

        Task<ObservableList<ApplicationViewData>> task;

        if (toggleActiveApps.isSelected()) {
            task = new GetApplicationsViewTask(true);
        } else {
            task = new GetApplicationsViewTask(false);
        }

        applicationListView.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();
    }

    @FXML
    public void updateApplication() {
        System.out.println(applicationListView.getSelectionModel().getSelectedItems().get(0).getApplicationId());
        hideAllPanes();
        updateAppForm.setVisible(true);

        FollowUp followUp = Datasource.getInstance()
                .retrieveFollowUp(applicationListView.getSelectionModel().getSelectedItems().get(0).getApplicationId());

        UAcontactPerson.setText(followUp.getContactName());
        UAcontactEmail.setText(followUp.getContactEmail());
        UAfollowUp1.setText(followUp.getFollowUp1());
        UAfollowUp2.setText(followUp.getFollowUp2());
        UAfollowUp3.setText(followUp.getFollowUp3());
        UAfollowUp4.setText(followUp.getFollowUp4());
        UAfeedback.setText(followUp.getFeedback());
    }

    @FXML
    public void submitApplicationUpdate() {

        String UAf1 =  UAfollowUp1.getText();
        String UAf2 =  UAfollowUp2.getText();
        String UAf3 =  UAfollowUp3.getText();
        String UAf4 =  UAfollowUp4.getText();
        String UAfb = UAfeedback.getText();
        String UAcp = UAcontactPerson.getText();
        String UAce = UAcontactEmail.getText();
        String id = applicationListView.getSelectionModel().getSelectedItems().get(0).getApplicationId();

        boolean successUpdate = Datasource.getInstance().updateFollowUp(UAf1, UAf2, UAf3, UAf4, UAfb, UAcp, UAce, id);


    }
}
