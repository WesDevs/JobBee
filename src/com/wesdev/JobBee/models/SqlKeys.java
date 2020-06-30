package com.wesdev.JobBee.models;

public enum SqlKeys {

    TABLE_COMPANIES("companies"),
    TABLE_APPLICATIONS("applications"),
    TABLE_FOLLOWUPS("followUps"),
    CREATE_COMPANIES("CREATE table IF NOT EXISTS companies (_id text NOT NULL, name text NOT NULL," +
            " notes text, active integer)"),
    CREATE_APPLICATIONS("CREATE table IF NOT EXISTS applications (_id text NOT NULL, company text, " +
            "companyName text, jobTitle text, applicationDate text, postedDate text, followUp text, interviewDate text, posting_url text, responseType int, reminderDate text)"),
    CREATE_FOLLOWUPS("CREATE table IF NOT EXISTS followUps (application text, contactName text, " +
            "contactEmail text, FollowUp1 text, FollowUp2 text, FollowUp3 text, FollowUp4 text, feedback text, lastUpdated text)"),

    NAME("name"),
    ID("_id"),
    NOTES("notes"),
    ACTIVE("active"),
    COMPANY("company"),
    COMPANY_NAME("companyName"),
    JOB_TITLE("jobTitle"),
    POSTED_DATE("postedDate"),
    FOLLOW_UP("followUp"),
    INTERVIEW_DATE("interviewDate"),
    POSTING_URL("posting_url"),
    RESPONSE_TYPE("responseType"),
    APPLICATION("application"),
    APPLICATION_DATE("applicationDate"),
    REMINDER_DATE("reminderDate"),
    CONTACT_NAME("contactName"),
    CONTACT_EMAIL("contactEmail"),
    CONTACT_METHOD("contactMethod"),
    FEEDBACK("feedback"),
    FOLLOWUP_1("followUp1"),
    FOLLOWUP_2("followUp2"),
    FOLLOWUP_3("followUp3"),
    FOLLOWUP_4("followUp4"),
    DOT("."),
    LAST_UPDATED("lastUpdated"),
    SELECT_ALL("SELECT * FROM ");


    private final String value;

    SqlKeys(String value) {
        this.value = value;
    }

    public String val() {
        return value;
    }
}