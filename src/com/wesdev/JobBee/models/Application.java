package com.wesdev.JobBee.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Application {
    private Company company;
    private SimpleStringProperty companyName;
    private SimpleStringProperty jobTitle;  //
    private SimpleStringProperty applicationDate; //
    private SimpleStringProperty postedDate; //
    private FollowUp followUp;
    private SimpleStringProperty interviewDate; //
    private SimpleStringProperty postingUrl; //
    private SimpleIntegerProperty responseType; //
    private SimpleStringProperty remindDate;

    public Application() {
        this.companyName = new SimpleStringProperty();
        this.jobTitle = new SimpleStringProperty();
        this.applicationDate = new SimpleStringProperty();
        this.postedDate = new SimpleStringProperty();
        this.interviewDate = new SimpleStringProperty();
        this.postingUrl = new SimpleStringProperty();
        this.responseType = new SimpleIntegerProperty();
        this.remindDate = new SimpleStringProperty();

    }

    public Application(Company company, SimpleStringProperty jobTitle,
                       SimpleStringProperty applicationDate, SimpleStringProperty postedDate, SimpleStringProperty postingUrl, SimpleStringProperty remindDate) {

        this.company = company;
        this.jobTitle = jobTitle;
        this.applicationDate = applicationDate;
        this.postedDate = postedDate;
        this.postingUrl = postingUrl;
        this.remindDate = remindDate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getCompanyName() {
        return companyName.get();
    }

    public SimpleStringProperty companyNameProperty() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
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

    public FollowUp getFollowUp() {
        return followUp;
    }

    public void setFollowUp(FollowUp followUp) {
        this.followUp = followUp;
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

    public SimpleStringProperty postingUrlVal() {
        return postingUrl;
    }

    public void setPostingUrl(String postingUrl) {
        this.postingUrl.set(postingUrl);
    }

    public String getPostingUrl() {
        return postingUrl.get();
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

    public SimpleStringProperty remindDateVal() {
        return remindDate;
    }

    public void setRemindDate(String remindDate) {
        this.remindDate.set(remindDate);
    }

    public String getRemindDate() {
        return remindDate.get();
    }
}
