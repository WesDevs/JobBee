package com.wesdev.JobBee;

import com.wesdev.JobBee.models.ApplicationViewData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

public class ApplicationListCell extends ListCell<ApplicationViewData> {

    @FXML
    private Label companyName;
    @FXML
    private Label activeStatus;
    @FXML
    private Label jobTitle;
    @FXML
    private Label nextFollowUpDate;
    @FXML
    private Label dateApplied;
    @FXML
    private Label contactPerson;
    @FXML
    private Label contactEmail;
    @FXML
    private ImageView imageView;

    public ApplicationListCell() {
        loadFXML();
    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ApplicationCustomCell.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void updateItem(ApplicationViewData item, boolean empty) {
        super.updateItem(item, empty);

        if(empty) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
        else {
            companyName.setText(item.getCompanyName().toUpperCase());
            activeStatus.setText(item.getActiveStatus() == 1 ? "Active" : "Inactive");
            jobTitle.setText("Position: " + item.getJobTitle().toUpperCase());
            nextFollowUpDate.setText("Follow Up On: " + item.getNextFollowUpDate());
            contactPerson.setText("Contact: " + item.getContactPerson());
            contactEmail.setText(item.getContactEmail());
            dateApplied.setText("Applied on: " + item.getDateApplied());

            if (LocalDate.parse(item.getNextFollowUpDate()).isEqual(LocalDate.now())) {
                InputStream inStream = getClass().getResourceAsStream("assets/alert.png");
                Image imageObject = new Image(inStream);
                imageView.setImage(imageObject);
            } else {
                imageView.setImage(null);
            }

            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}
