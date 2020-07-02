package com.wesdev.JobBee.Controllers;

import com.wesdev.JobBee.models.Datasource;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.wesdev.JobBee.utils.Validation.*;

public class ApplicationFormController {

    @FXML
    private TextField companyNameField;

    @FXML
    private TextField postingUrl;

    @FXML
    private TextField notes;

    @FXML
    private TextField jobTitle;

    @FXML
    private DatePicker postedDate;

    @FXML
    private DatePicker applicationDate;

    @FXML
    private TextField contactPerson;

    @FXML
    private TextField contactEmail;

    @FXML
    private VBox errorOverlay;

    @FXML
    private VBox successOverlay;

    @FXML
    private Text validationMessage;

    private static List<TextField> textFields = new ArrayList<>();

    @FXML
    private void initialize() {

        postedDate.setDayCellFactory(d ->
                new DateCell() {
                    @Override
                    public void updateItem(LocalDate localDate, boolean b) {
                        super.updateItem(localDate, b);
                        setDisable(localDate.isAfter(LocalDate.now()));
                    }
                });


        textFields.add(companyNameField);
        textFields.add(postingUrl);
        textFields.add(jobTitle);
        textFields.add(contactPerson);
        textFields.add(contactEmail);
    }

    @FXML
    public void addApplication() {

        if (validateForm()) {

            //Required Fields
            String companyName = companyNameField.getText();
            String postUrl = postingUrl.getText();
            String position = jobTitle.getText();
            String postDate = postedDate.getValue().toString();
            String appDate = applicationDate.getValue().toString();

            //Optional Fields
            String note = notes.getText();
            String contactName = contactPerson.getText();
            String email = contactEmail.getText();
            String remindDate = applicationDate.getValue().plusWeeks(1).toString();

            Datasource.getInstance().insertFullApplication(companyName, position, appDate, postDate, postUrl, note, remindDate, contactName, email);
        }
    }


    private boolean validateTextField(TextField tf, String regex, boolean required) {

        if (tf.getText() == null) return false;

        if (required) {
            if (!tf.getText().isEmpty()) {
                Pattern p = Pattern.compile(regex);
                Matcher m;
                m = p.matcher(tf.getCharacters());

                return m.matches();
            }
        } else {
            if (!tf.getText().isEmpty()) {
                Pattern p = Pattern.compile(regex);
                Matcher m;
                m = p.matcher(tf.getCharacters());

                return m.matches();
            } else {
                return true;
            }
        }
        return false;
    }

    private boolean validateDateField(DatePicker applied, DatePicker posted) {

        if (applied.getValue() == null || postedDate.getValue() == null) return false;

        return applied.getValue().isAfter(posted.getValue());

    }

    @FXML
    public void resetTextValid(Event e) {
        if (((TextField) e.getSource()).getStyleClass().contains("invalid-input")) {
            ((TextField) e.getSource()).getStyleClass().remove("invalid-input");
        }
    }

    @FXML
    public void resetDateValid(Event e) {
            postedDate.getStyleClass().remove("invalid-input");
            applicationDate.getStyleClass().remove("invalid-input");
    }

    private boolean validateForm() {

        textFields.forEach(tf -> { tf.getStyleClass().remove("invalid-input"); });
        applicationDate.getStyleClass().remove("invalid-input");
        postedDate.getStyleClass().remove("invalid-input");

        boolean validateCompanyName = validateTextField(companyNameField, VALIDATE_NAME_TITLE.val(), true);
        boolean validateJobTitle = validateTextField(jobTitle, VALIDATE_NAME_TITLE.val(), true);
        boolean validateURL = validateTextField(postingUrl, VALIDATE_URL.val(), true);
        boolean validateDates = validateDateField(applicationDate, postedDate);

        boolean validateEmail = validateTextField(contactEmail, VALIDATE_EMAIL.val(), false);
        boolean validateContactName = validateTextField(contactPerson, VALIDATE_NAME_TITLE.val(), false);

        StringBuilder sb = new StringBuilder();

        if (!validateCompanyName) {
            companyNameField.getStyleClass().add("invalid-input");
            sb.append("Company Name field: value is missing or includes invalid characters \n\n");
        }
        if (!validateJobTitle) {
            jobTitle.getStyleClass().add("invalid-input");
            sb.append("Job Title field: value is missing or includes invalid characters \n\n");
        }

        if (!validateDates) {
            applicationDate.getStyleClass().add("invalid-input");
            postedDate.getStyleClass().add("invalid-input");
            sb.append("Date field: value is missing or posted date is later than applied date \n\n");
        }
        if (!validateURL) {
            postingUrl.getStyleClass().add("invalid-input");
            sb.append("Posting URL field: value is missing or not a valid url \n\n");
        }
        if (!validateEmail) {
            contactEmail.getStyleClass().add("invalid-input");
            sb.append("Contact Email field: includes invalid email address \n\n");
        }
        if (!validateContactName) {
            contactPerson.getStyleClass().add("invalid-input");
            sb.append("Contact Name field: includes invalid characters \n\n");
        }

        if (validateCompanyName && validateJobTitle && validateURL
                && validateEmail && validateContactName && validateDates) {

            successOverlay.setVisible(true);

            return true;
        } else {
            errorOverlay.setVisible(true);
            validationMessage.setText(sb.toString());
            return false;
        }
    }

    @FXML
    public void closeOverlay() {
        successOverlay.setVisible(false);
        errorOverlay.setVisible(false);
    }
}
