package com.wesdev.JobBee.models;

import com.wesdev.JobBee.utils.SqlHelper;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.wesdev.JobBee.models.SqlKeys.*;

public class Datasource {

    public static final String DB_NAME = "jobMe.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;

    public static final String QUERY_ALL_COMPANIES = SELECT_ALL.val() + TABLE_COMPANIES.val();
    public static final String QUERY_ALL_APPLICATIONS = SELECT_ALL.val() + TABLE_APPLICATIONS.val();
    public static final String QUERY_ALL_FOLLOWUPS = SELECT_ALL.val() + TABLE_FOLLOWUPS.val();
    public static final String QUERY_COMBINED_VIEW = "SELECT "
            + TABLE_COMPANIES.val() + DOT.val() + NAME.val() + ", "
            + TABLE_COMPANIES.val() + DOT.val() + ACTIVE.val() + ", "
            + TABLE_APPLICATIONS.val() + DOT.val() + JOB_TITLE.val() + ", "
            + TABLE_APPLICATIONS.val() + DOT.val() + POSTED_DATE.val() + ", "
            + TABLE_APPLICATIONS.val() + DOT.val() + APPLICATION_DATE.val() + ", "
            + TABLE_APPLICATIONS.val() + DOT.val() + POSTING_URL.val() + ", "
            + TABLE_APPLICATIONS.val() + DOT.val() + INTERVIEW_DATE.val() + ", "
            + TABLE_APPLICATIONS.val() + DOT.val() + RESPONSE_TYPE.val() + ", "
            + TABLE_COMPANIES.val() + DOT.val() + NOTES.val() + ", "
            + TABLE_FOLLOWUPS.val() + DOT.val() + CONTACT_NAME.val() + ", "
            + TABLE_FOLLOWUPS.val() + DOT.val() + CONTACT_EMAIL.val() + ", "
            + TABLE_FOLLOWUPS.val() + DOT.val() + FOLLOWUP_1.val() + ", "
            + TABLE_FOLLOWUPS.val() + DOT.val() + FOLLOWUP_2.val() + ", "
            + TABLE_FOLLOWUPS.val() + DOT.val() + FOLLOWUP_3.val() + ", "
            + TABLE_FOLLOWUPS.val() + DOT.val() + FOLLOWUP_4.val() + ", "
            + TABLE_FOLLOWUPS.val() + DOT.val() + FEEDBACK.val() + ", "
            + TABLE_APPLICATIONS.val() + DOT.val() + ID.val() + ", "
            + TABLE_FOLLOWUPS.val() + DOT.val() + LAST_UPDATED.val()
            + " FROM " + TABLE_APPLICATIONS.val() + " JOIN " + TABLE_COMPANIES.val()
            + " ON " + TABLE_COMPANIES.val() + DOT.val() + ID.val() + " = "
            + TABLE_APPLICATIONS.val() + DOT.val() + COMPANY.val() + " JOIN "
            + TABLE_FOLLOWUPS.val() + " ON " + TABLE_FOLLOWUPS.val() + DOT.val() + APPLICATION.val() + " = "
            + TABLE_APPLICATIONS.val() + DOT.val() + ID.val();

    public static final String QUERY_COMPANIES = "SELECT " + ID.val() + " FROM "
            + TABLE_COMPANIES.val() + " WHERE " + NAME.val() + " = ?";
    public static final String QUERY_FOLLOWUP = SELECT_ALL.val()
            + TABLE_FOLLOWUPS.val() + " WHERE " + APPLICATION.val() + " = ?";

    public static final String INSERT_COMPANIES = "INSERT INTO " + TABLE_COMPANIES.val()
            + " ( " + ID.val() + ", " + NAME.val() + ", " + NOTES.val() + ", " + ACTIVE.val() + " ) VALUES(?, ?, ?, ?)";

    public static final String INSERT_APPLICATIONS = "INSERT INTO " + TABLE_APPLICATIONS.val()
            + " ( " + ID.val() + ", " + COMPANY.val() + ", " + COMPANY_NAME.val() + ", "
            + JOB_TITLE.val() + ", " + APPLICATION_DATE.val() + ", " + POSTED_DATE.val()
            + ", " + REMINDER_DATE.val() + ", " + RESPONSE_TYPE.val()
            + ", " + POSTING_URL.val() + " ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String INSERT_FOLLOWUPS = "INSERT INTO " + TABLE_FOLLOWUPS.val()
            + " ( " + APPLICATION.val() + ", " + CONTACT_NAME.val() + ", "
            + CONTACT_EMAIL.val() + ", " + FEEDBACK.val() + ", " + FOLLOWUP_1.val()
            + ", " + FOLLOWUP_2.val() + ", " + FOLLOWUP_3.val() + ", " + FOLLOWUP_4.val()
            + " ) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";



    private static final String CREATE_COMPANIES_TABLE = CREATE_COMPANIES.val();
    private static final String CREATE_APPLICATIONS_TABLE = CREATE_APPLICATIONS.val();
    private static final String CREATE_FOLLOWUPS_TABLE = CREATE_FOLLOWUPS.val();

    private static Datasource instance = new Datasource();

    private Datasource() {
    }

    private PreparedStatement queryCompanies;
    private PreparedStatement insertIntoCompanies;
    private PreparedStatement insertIntoApplications;
    private PreparedStatement insertIntoFollowUps;
    private PreparedStatement queryFollowUp;

    private Connection conn;

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            setUp();
            queryCompanies = conn.prepareStatement(QUERY_COMPANIES);
            insertIntoCompanies = conn.prepareStatement(INSERT_COMPANIES);
            insertIntoApplications = conn.prepareStatement(INSERT_APPLICATIONS);
            insertIntoFollowUps = conn.prepareStatement(INSERT_FOLLOWUPS);
            queryFollowUp = conn.prepareStatement(QUERY_FOLLOWUP);

            return true;
        } catch (SQLException e) {
            System.out.println("Could not perform open method: " + e.getMessage());
            return false;
        }


    }

    public boolean close() {
        try {
            if (queryCompanies != null) {
                queryCompanies.close();
            }
            if (insertIntoCompanies != null) {
                insertIntoCompanies.close();
            }
            if (insertIntoApplications != null) {
                insertIntoApplications.close();
            }
            if (insertIntoFollowUps != null) {
                insertIntoFollowUps.close();
            }
            if (queryFollowUp != null) {
                queryFollowUp.close();
            }
            if (conn != null) {
                conn.close();
            }

            return true;
        } catch (SQLException e) {
            System.out.println("Unable to close");
        }
        return false;
    }


    public static Datasource getInstance() {
        return instance;
    }

    private void setUp() throws SQLException {

        try (Statement statement = conn.createStatement()) {
            statement.addBatch(CREATE_COMPANIES_TABLE);
            statement.addBatch(CREATE_APPLICATIONS_TABLE);
            statement.addBatch(CREATE_FOLLOWUPS_TABLE);
            statement.executeBatch();
        }
    }

    public List<Company> retrieveAllCompanies(int sortOrder, String orderedByCategory) {
        StringBuilder sb = new StringBuilder(QUERY_ALL_COMPANIES);
        SqlHelper.getInstance().sortBy(sb, sortOrder, orderedByCategory);

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<Company> companies = new ArrayList<>();
            while (results.next()) {
                Company company = new Company();
                company.setName(results.getString(2));
                company.setNote(results.getString(3));
                company.setActive(results.getInt(4) == 1);

                companies.add(company);
            }
            return companies;


        } catch (SQLException e) {
            System.out.println("Unable to retrieve all companies: " + e.getMessage());
            return null;
        }
    }

    public List<Application> retrieveAllApplications(int sortOrder, String orderedByCategory) {
        StringBuilder sb = new StringBuilder(QUERY_ALL_APPLICATIONS);
        SqlHelper.getInstance().sortBy(sb, sortOrder, orderedByCategory);

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<Application> applications = new ArrayList<>();
            while (results.next()) {
                Application app = new Application();
                app.setCompanyName(results.getString(3));
                app.setJobTitle(results.getString(4));
                app.setApplicationDate(results.getString(5));
                app.setPostedDate(results.getString(6));
                app.setInterviewDate(results.getString(8));
                app.setPostingUrl(results.getString(9));

                applications.add(app);
            }
            return applications;


        } catch (SQLException e) {
            System.out.println("Unable to retrieve all job applications: " + e.getMessage());
            return null;
        }
    }

    public List<FollowUp> retrieveAllFollowUps(int sortOrder, String orderedByCategory) {
        StringBuilder sb = new StringBuilder(QUERY_ALL_FOLLOWUPS);
        SqlHelper.getInstance().sortBy(sb, sortOrder, orderedByCategory);

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<FollowUp> followUps = new ArrayList<>();
            while (results.next()) {
                FollowUp followUp = new FollowUp();
                followUp.setContactName(results.getString(2));
                followUp.setContactEmail(results.getString(3));
                followUp.setFollowUp1(results.getString(4));
                followUp.setFollowUp2(results.getString(5));
                followUp.setFollowUp3(results.getString(6));
                followUp.setFollowUp4(results.getString(7));
                followUp.setFeedback(results.getString(8));

                followUps.add(followUp);
            }
            return followUps;


        } catch (SQLException e) {
            System.out.println("Unable to retrieve all job applications: " + e.getMessage());
            return null;
        }
    }

    public List<FullApplication> retrieveCombinedView(int sortOrder, String orderedByCategory) {
        StringBuilder sb = new StringBuilder(QUERY_COMBINED_VIEW);
        SqlHelper.getInstance().sortBy(sb, sortOrder, orderedByCategory);

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<FullApplication> allApplications = new ArrayList<>();
            while (results.next()) {
                FullApplication fa = new FullApplication();

                fa.setName(results.getString(1));
                fa.setActive(results.getInt(2) == 1);
                fa.setJobTitle(results.getString(3));
                fa.setPostedDate(results.getString(4));
                fa.setApplicationDate(results.getString(5));
                fa.setPostingUrl(results.getString(6));
                fa.setInterviewDate(results.getString(7));
                fa.setResponseType(results.getInt(8));
                fa.setNote(results.getString(9));
                fa.setContactName(results.getString(10));
                fa.setContactEmail(results.getString(11));
                fa.setFollowUp1(results.getString(12));
                fa.setFollowUp2(results.getString(13));
                fa.setFollowUp3(results.getString(14));
                fa.setFollowUp4(results.getString(15));
                fa.setFeedback(results.getString(16));

                allApplications.add(fa);
            }
            return allApplications;


        } catch (SQLException e) {
            System.out.println("Unable to retrieve full job applications: " + e.getMessage());
            return null;
        }
    }

    public List<ApplicationViewData> retrieveAllApplicationView(boolean showInactive) {
        StringBuilder sb = new StringBuilder(QUERY_COMBINED_VIEW);

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<ApplicationViewData> allApplications = new ArrayList<>();
            while (results.next()) {
                ApplicationViewData avd = new ApplicationViewData();
                avd.setCompanyName(results.getString(1));
                avd.setActiveStatus(results.getInt(2));
                avd.setJobTitle(results.getString(3));
                avd.setDateApplied(results.getString(5));
                avd.setContactPerson(results.getString(10));
                avd.setContactEmail(results.getString(11));
                avd.setApplicationId(results.getString(17));
                avd.setLastUpdated(results.getString(18));

                if (avd.getDateApplied() != null) {
                    long daysSinceApplied = ChronoUnit.DAYS.between(LocalDate.parse(avd.getDateApplied()), LocalDate.now());
                    LocalDate appliedDate = LocalDate.parse(avd.getDateApplied());

                    if (daysSinceApplied <= 7) {
                        avd.setNextFollowUpDate(appliedDate.plusWeeks(1).toString());
                    } else if (daysSinceApplied <= 14) {
                        avd.setNextFollowUpDate(appliedDate.plusWeeks(2).toString());
                    } else if (daysSinceApplied <= 21) {
                        avd.setNextFollowUpDate(appliedDate.plusWeeks(3).toString());
                    } else {
                        avd.setNextFollowUpDate(appliedDate.plusWeeks(4).toString());
                    }

                    if (showInactive) {
                        allApplications.add(avd);
                    } else {
                        if (avd.getActiveStatus() > 0) {
                            allApplications.add(avd);
                        }
                    }
                }
            }

            allApplications.sort((a, b) -> {
                return LocalDate.parse(a.getNextFollowUpDate()).compareTo(LocalDate.parse(b.getNextFollowUpDate()));
            });

            return allApplications;


        } catch (SQLException e) {
            System.out.println("Unable to retrieve application data: " + e.getMessage());
            return null;
        }
    }


    private String insertCompanies(String companyName, String notes) throws SQLException {

        queryCompanies.setString(1, companyName.toLowerCase());
        ResultSet results = queryCompanies.executeQuery();
        if (results.next()) {
            return results.getString(1);
        } else {
            String id = UUID.randomUUID().toString();
            insertIntoCompanies.setString(1, id);
            insertIntoCompanies.setString(2, companyName.toLowerCase());
            insertIntoCompanies.setString(3, notes == null ? "" : notes);
            insertIntoCompanies.setBoolean(4, true);

            int affectedRows = insertIntoCompanies.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Unable to insert company");
            } else {
                return id;
            }
        }
    }


    private String insertIntoApplication(String companyName, String jobTitle, String dateApplied, String postedDate,
                                         String postingUrl, String reminderDate, String companyId) throws SQLException {

        String uid = UUID.randomUUID().toString();

        insertIntoApplications.setString(1, uid);
        insertIntoApplications.setString(2, companyId);
        insertIntoApplications.setString(3, companyName);
        insertIntoApplications.setString(4, jobTitle);
        insertIntoApplications.setString(5, dateApplied);
        insertIntoApplications.setString(6, postedDate);
        insertIntoApplications.setString(7, reminderDate);
        insertIntoApplications.setInt(8, 0);
        insertIntoApplications.setString(9, postingUrl);

        int affectedRows = insertIntoApplications.executeUpdate();
        if (affectedRows != 1) {
            throw new SQLException("Unable to insert application");
        } else {
            return uid;
        }
    }

    private boolean insertIntoFollowUps(String applicationId, String contactName, String contactEmail, String followUp1, String followUp2,
                                        String followUp3, String followUp4, String feedback) throws SQLException {

        insertIntoFollowUps.setString(1, applicationId);
        insertIntoFollowUps.setString(2, contactName);
        insertIntoFollowUps.setString(3, contactEmail);
        insertIntoFollowUps.setString(4, feedback);
        insertIntoFollowUps.setString(5, followUp1);
        insertIntoFollowUps.setString(6, followUp2);
        insertIntoFollowUps.setString(7, followUp3);
        insertIntoFollowUps.setString(8, followUp4);

        int affectedRows = insertIntoFollowUps.executeUpdate();
        if (affectedRows != 1) {
            throw new SQLException("Unable to insert application");
        } else {
            return true;
        }
    }

    public void insertFullApplication(String companyName, String jobTitle, String dateApplied, String postedDate,
                                      String postingUrl, String notes, String reminderDate, String contactName, String contactEmail) {

        try {
            conn.setAutoCommit(false);
            String companyId = insertCompanies(companyName, notes);
            String applicationId = insertIntoApplication(companyName, jobTitle, dateApplied, postedDate, postingUrl,
                    reminderDate, companyId);
            boolean insertIntoFollowUps = insertIntoFollowUps(applicationId, contactName, contactEmail,
                    null, null, null, null, null);


            if (!insertIntoFollowUps) {
                throw new SQLException("Unable to insert full application");
            } else {
                conn.commit();
            }

        } catch (Exception e) {
            System.out.println("Insert full application exception: " + e.getMessage());
            try {
                System.out.println("Performing roll back.");
                conn.rollback();
            } catch (SQLException ex) {
                System.out.println("Issue with rolling back: " + ex.getMessage());
            }
        } finally {
            System.out.println("RESETTING auto commit to true");
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Failed to reset auto commit to true: " + e.getMessage());
            }
        }
    }

    public FollowUp retrieveFollowUp(String applicationId) {
        try {
            queryFollowUp.setString(1, applicationId);
            ResultSet results = queryFollowUp.executeQuery();
            FollowUp followUp = new FollowUp();

            if (results.next()) {
                followUp.setContactName(results.getString(2));
                followUp.setContactEmail(results.getString(3));
                followUp.setFollowUp1(results.getString(4));
                followUp.setFollowUp2(results.getString(5));
                followUp.setFollowUp3(results.getString(6));
                followUp.setFollowUp4(results.getString(7));
                followUp.setFeedback(results.getString(8));
            }

            return followUp;

        } catch (SQLException e) {
            System.out.println("Unable to retrieve follow up: " + e.getMessage());
        }

        return null;
    }

    public boolean updateFollowUp(String UAf1, String UAf2, String UAf3, String UAf4, String UAfb, String UAcp, String UAce, String UAupdatedDate) {

        return false;
    }

}
