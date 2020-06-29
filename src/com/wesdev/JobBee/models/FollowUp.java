package com.wesdev.JobBee.models;

import javafx.beans.property.SimpleStringProperty;

public class FollowUp {
    private SimpleStringProperty contactName;
    private SimpleStringProperty contactEmail;
    private SimpleStringProperty followUp1;
    private SimpleStringProperty followUp2;
    private SimpleStringProperty followUp3;
    private SimpleStringProperty followUp4;
    private SimpleStringProperty feedback;

    public FollowUp() {
        this.contactName = new SimpleStringProperty();
        this.contactEmail = new SimpleStringProperty();
        this.followUp1 = new SimpleStringProperty();
        this.followUp2 = new SimpleStringProperty();
        this.followUp3 = new SimpleStringProperty();
        this.followUp4 = new SimpleStringProperty();
        this.feedback = new SimpleStringProperty();
    }

    public FollowUp(String contactName, String contactEmail) {
        this.contactName.set(contactName);
        this.contactEmail.set(contactEmail);
    }

    public String getContactName() {
        return contactName.get();
    }

    public void setContactName(String contactName) {
        this.contactName.set(contactName);
    }

    public SimpleStringProperty contactNameVal() {
        return this.contactName;
    }

    public String getContactEmail() {
        return contactEmail.get();
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail.set(contactEmail);
    }

    public SimpleStringProperty contactEmailVal() {
        return this.contactEmail;
    }

    public String getFollowUp1() {
        return followUp1.get();
    }

    public void setFollowUp1(String followUp1) {
        this.followUp1.set(followUp1);
    }

    public SimpleStringProperty followUp1Val() {
        return this.followUp1;
    }

    public String getFollowUp2() {
        return followUp2.get();
    }

    public void setFollowUp2(String followUp2) {
        this.followUp2.set(followUp2);
    }

    public SimpleStringProperty followUp2Val() {
        return this.followUp2;
    }

    public String getFollowUp3() {
        return followUp3.get();
    }

    public void setFollowUp3(String followUp3) {
        this.followUp3.set(followUp3);
    }

    public SimpleStringProperty followUp3Val() {
        return this.followUp3;
    }

    public String getFollowUp4() {
        return followUp4.get();
    }

    public void setFollowUp4(String followUp4) {
        this.followUp4.set(followUp4);
    }

    public SimpleStringProperty followUp4Val() {
        return this.followUp4;
    }

    public String getFeedback() {
        return feedback.get();
    }

    public void setFeedback(String feedback) {
        this.feedback.set(feedback);
    }

    public SimpleStringProperty feedbackVal() {
        return this.feedback;
    }
}
