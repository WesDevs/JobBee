package com.wesdev.JobBee.models;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FullApplication {

    private SimpleStringProperty name;
    private SimpleBooleanProperty active;
    private SimpleStringProperty jobTitle;
    private SimpleStringProperty applicationDate;
    private SimpleStringProperty postedDate;
    private SimpleStringProperty interviewDate;
    private SimpleStringProperty postingUrl;
    private SimpleIntegerProperty responseType;
    private SimpleStringProperty note;
    private SimpleStringProperty contactName;
    private SimpleStringProperty contactEmail;
    private SimpleStringProperty followUp1;
    private SimpleStringProperty followUp2;
    private SimpleStringProperty followUp3;
    private SimpleStringProperty followUp4;
    private SimpleStringProperty feedback;

    public FullApplication() {
        this.name = new SimpleStringProperty();
        this.active = new SimpleBooleanProperty();
        this.jobTitle = new SimpleStringProperty();
        this.applicationDate = new SimpleStringProperty();
        this.postedDate = new SimpleStringProperty();
        this.interviewDate = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.postingUrl = new SimpleStringProperty();
        this.responseType = new SimpleIntegerProperty();
        this.note = new SimpleStringProperty();
        this.contactName = new SimpleStringProperty();
        this.contactEmail = new SimpleStringProperty();
        this.followUp1 = new SimpleStringProperty();
        this.followUp2 = new SimpleStringProperty();
        this.followUp3 = new SimpleStringProperty();
        this.followUp4 = new SimpleStringProperty();
        this.feedback = new SimpleStringProperty();
    }

    public SimpleStringProperty nameVal() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getName() {
        return this.name.get();
    }

    public SimpleBooleanProperty activeVal() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active.set(active);
    }

    public Boolean isActive() {
        return this.active.get();
    }

    public SimpleStringProperty noteVal() {
        return note;
    }

    public void setNote(String note) {
        this.note.set(note);
    }

    public String getNote() {
        return this.note.get();
    }

    public SimpleStringProperty jobTitleVal() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle.set(jobTitle);
    }

    public String getJobTitle() {
        return jobTitle.get();
    }

    public SimpleStringProperty getAppDateVal() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate.set(applicationDate);
    }

    public String getApplicationDate() {
        return this.applicationDate.get();
    }

    public SimpleStringProperty getPostDateVal() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate.set(postedDate);
    }

    public String getPostedDate() {
        return this.postedDate.get();
    }

    public SimpleStringProperty postingUrlVal() {
        return postingUrl;
    }

    public void setPostingUrl(String postingUrl) {
        this.postingUrl.set(postingUrl);
    }

    public String getPostingUrl() {
        return postingUrl.get();
    }

    public SimpleStringProperty interviewDateVal() {
        return interviewDate;
    }

    public void setInterviewDate(String interviewDate) {
        this.interviewDate.set(interviewDate);
    }

    public String getInterviewDate() {
        return interviewDate.get();
    }

    public SimpleIntegerProperty responseVal() {
        return responseType;
    }

    public void setResponseType(int responseType) {
        this.responseType.set(responseType);
    }

    public int getResponseType() {
        return responseType.get();
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
